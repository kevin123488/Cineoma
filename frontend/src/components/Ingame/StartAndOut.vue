<template>
    <div></div>

</template>

<script>
import { mapGetters, mapActions, mapState } from 'vuex'
const roomdataStore = "roomdataStore"
const memberStore = "memberStore"
// const ingameStore = "ingameStore"

import Stomp from 'webstomp-client'
import SockJS from 'sockjs-client'

export default {

  components: {
  },
  data() {
    return {
      absoluteTime: "",
      job: "",
      joinMembers: [],
      winJob: "",
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
    // ...mapGetters(ingameStore, [
    //   'isLogin',
    // ]),
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
    this.connect()
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


          // 게임 시작하고 초기데이터 세팅
          this.stompClient.subscribe(`/sendMafia/${this.roomNo}`, res => {
            console.log('구독으로 받은 게임시작 정보입니다.', res.body);
            const data = JSON.parse(res.body)

            if (data.progress  === "start") {
              this.absoluteTime = data.absoluteTime
              this.job = data.job // 내 직업
              this.joinUsers = data.joinUsers
              this.joinUsers.forEach(user => {
                user.isAlive = true
                user.voteNo = 1
                user.voted = []
              });
            }

          // 누구 나갔을때
          this.stompClient.subscribe(`/sendMafia/${this.roomNo}/gameOut}`, res => {
            const data = JSON.parse(res.body)
            const newJoinMembers = this.joinMembers.filter(member => member.id !== data.id)
            this.joinMembers = newJoinMembers
            if (data.winJob !== "") {
              this.winJob = data.winJob
              console.log('직업에 따라 게임결과창으로 넘어가기')
              // 모달 형식으로 결과 띄워주고 버튼누르면 router push로 로비창으로 보내주면 될듯
            }
          });
        },)

        error => {
          // 소켓 연결 실패
          console.log('소켓 연결 실패', error);
          this.connected = false;
        }}
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