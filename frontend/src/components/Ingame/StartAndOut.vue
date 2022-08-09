<template>
    <div></div>

</template>

<script>
import { mapGetters, mapActions, mapState } from 'vuex'
const roomdataStore = "roomdataStore"
const memberStore = "memberStore"

import Stomp from 'webstomp-client'
import SockJS from 'sockjs-client'

export default {

  components: {
  },
  data() {
    return {
      num : 1,
      user: {},
      usersInfo: [],
      nickName: '',
      message: '',
      recvList: [],
      startGame: false,
      ifStart: false,
      ifReady: false
    }
  },
  
  computed: {
    ...mapState(memberStore, ["userInfo"]),
    ...mapGetters(roomdataStore, [
      'roomNo',
      'roomTitle',
      'isCaptain'
    ]),
    ...mapGetters(memberStore, [
      'isLogin',
    ]),
    ...mapActions(roomdataStore, [
      'deleteRoom',
      'enterRoom',
      'saveRoomTitle',
      'saveIsCaptain',
    ])
  },

  created() {
    this.user = this.userInfo
    this.nickName = this.user.nickname
    // App.vue가 생성되면 소켓 연결을 시도합니다.
    this.connect()
    console.log(this.isCaptain)
    console.log(this.roomNo)
    console.log(this.roomTitle)
    // this.sendProfile()
  },

  methods: {
    // 소켓 연결
    connect() {
      const serverURL = "http://localhost:8080/roomSocket"
      let socket = new SockJS(serverURL);
      this.stompClient = Stomp.over(socket);
      console.log(`소켓 연결을 시도합니다. 서버 주소: ${serverURL}`)
      this.stompClient.connect(
        {},
        frame => {
          // 소켓 연결 성공
          this.connected = true;
          console.log('소켓 연결 성공', frame);

          // 채팅
          this.stompClient.subscribe(`/topic/sendChat/${this.roomNo}`, res => {
            console.log('구독으로 받은 채팅입니다.', res.body);
            this.recvList.push(JSON.parse(res.body))

          });

          // 프로필
          this.stompClient.subscribe(`/topic/sendProfile/${this.roomNo}`, res => {
            console.log('구독으로 받은 프로필입니다.', res.body);
            this.usersInfo.push(JSON.parse(res.body))

            // 방 나가기
            if (res.body.progress === 'out') {
              this.$route.push('lobby');
            }
          });

          // 레디
          this.stompClient.subscribe(`/topic/sendReady/${this.roomNo}`, res => {
            console.log('구독으로 받은 레디입니다.', res.body);
            const readyData = JSON.parse(res.body)
            this.ifStart = readyData.ifStart;

            if (readyData.startGame === true) {
              this.$route.push({ name: 'ingame', params: { id_pk: this.roomNo } });
            }
          });

          // 방 폭파
          this.stompClient.subscribe(`/topic/sendBreak/${this.roomNo}`, res => {
            console.log('방 폭파.', res.body);
            this.$route.push('lobby');
          });
        },

        error => {
          // 소켓 연결 실패
          console.log('소켓 연결 실패', error);
          this.connected = false;
        }
      );        
    },

    sendChat() {
      console.log("Send message:" + this.message);
      if (this.stompClient && this.stompClient.connected) {
        const msg = { 
          roomNo: this.roomNo,
          nickName: this.nickName,
          content: this.message
        };
        console.log(msg);
        this.stompClient.send('/receiveChat', JSON.stringify(msg), {});
        this.message = '';
      }
    },

    // 프로필
    sendProfile() {
      if (this.stompClient && this.stompClient.connected) {
        const msg = { 
          progress: 'in',
          roomNo: this.roomNo,
          id: this.user.id
        };
        console.log(msg);
        this.stompClient.send('/receiveProfile', JSON.stringify(msg), {});
      }
    },

    // 레디
    sendReady() {
        if (this.stompClient && this.stompClient.connected) {
        const msg = { 
          roomNo: this.roomNo,
          startGame: this.startGame,
          ifReady: this.ifReady,
          id: this.user.id
        };
        console.log(msg);
        this.stompClient.send('/receiveReady', JSON.stringify(msg), {});
      }
    },

    // 방 나가기
    sendOut() {
      if (this.stompClient && this.stompClient.connected) {
        const msg = { 
          progress: 'out',
          roomNo: this.roomNo,
          id: this.user.id
        };
        console.log(msg);
        this.stompClient.send('/receiveProfile', JSON.stringify(msg), {});
      }
    },

    // 방 폭파
    sendBreak() {
        if (this.stompClient && this.stompClient.connected) {
        const msg = { 
          roomNo: this.roomNo,
        };
        console.log(msg);
        this.stompClient.send('/receiveBreak', JSON.stringify(msg), {});
      }
    },

    sendMessage (e) {
      if(e.keyCode === 13 && this.nickName !== '' && this.message !== ''){
        this.sendChat()
        this.message = ''
      }
    },

    // 레디
    isReady() {
      const readyButton = document.getElementById("readyButton")
      if (this.ifReady) {
        readyButton.innerText = "준비완료"
      } else {
        readyButton.innerText = "준비"
      }
      this.ifReady = !this.ifReady
      this.sendReady()
    },

    // 게임시작
    startSignal() {
      this.startGame = true
      this.sendReady()
    }
  },
  mounted() {
  }
}
</script>

<style>

</style>