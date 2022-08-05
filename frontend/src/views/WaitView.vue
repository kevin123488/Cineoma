<template class="">
  <div class="w3-black wait-background" style="height: 1000px;">
    <div class="w3-main mx-5">

      <!-- Header -->
      <header id="portfolio">
        <div class="w3-container">
        <h1><b>{{ roomTitle }}</b></h1>
        <div class="w3-section w3-bottombar w3-padding-16">
          <a href="/lobby">
          <!-- <router-link :to="{ name: 'lobby' }" > -->
          <!-- 방나가기 /room/roomNum -->
          <!-- method: PUT -->
          <!-- 방 삭제 /room/roomNum -->
          <!-- method: DELETE -->
          
            <button v-if="isCaptain" v-on:click="tryDeleteRoom" class="w3-button w3-white w3-hide-small"><i class='fa fa-close'></i>방삭제</button>
            <button v-if="!isCaptain" v-on:click="tryLeaveRoom" class="w3-button w3-white w3-hide-small"><i class='fa fa-close'></i>방나가기</button>

          <!-- </router-link> -->
          </a>
        </div>
        </div>
      </header>
  
  <!-- First Photo Grid-->
  <div class="w3-row">

    <div class="w3-col m4">
      <div class="mx-5 my-5 w3-container w3-margin-bottom border border-secondary">
        <div class="w3-container w3-white">
          <p><b>유저닉네임</b></p>
          <p>간략한 유저 프로필 정보</p>
        </div>
      </div>
    </div>

    <div class="w3-col m4">
      <div class="mx-5 my-5 w3-container w3-margin-bottom border border-secondary">
        <div class="w3-container w3-white">
          <p><b>유저닉네임</b></p>
          <p>간략한 유저 프로필 정보</p>
        </div>
      </div>
    </div>

    <div class="w3-col m4">
      <div class="mx-5 my-5 w3-container w3-margin-bottom border border-secondary">
        <div class="w3-container w3-white">
          <p><b>유저닉네임</b></p>
          <p>간략한 유저 프로필 정보</p>
        </div>
      </div>
    </div>

  </div>

  
  <!-- Second Photo Grid-->
  <div class="w3-row">

    <div class="w3-col m4">
      <div class="mx-5 my-5 w3-container border border-secondary">
        <div class="w3-container w3-white">
          <p><b>유저닉네임</b></p>
          <p>간략한 유저 프로필 정보</p>
        </div>
      </div>
    </div>

    <div class="w3-col m4">
      <div class="mx-5 my-5 w3-container w3-margin-bottom border border-secondary">
        <div class="w3-container w3-white">
          <p><b>유저닉네임</b></p>
          <p>간략한 유저 프로필 정보</p>
        </div>
      </div>
    </div>

    <div class="w3-col m4">
      <div class="mx-5 my-5 w3-container w3-margin-bottom border border-secondary">
        <div class="w3-container w3-white">
          <p><b>유저닉네임</b></p>
          <p>간략한 유저 프로필 정보</p>
        </div>
      </div>
    </div>

  </div>
  <div>
    <div class="chatList">
      <div
        v-for="(item, idx) in recvList"
        :key="idx"
      >
        <h3>유저이름: {{ item.nickName }}</h3>
        <h3>내용: {{ item.content }}</h3>
      </div>
    </div>
    <div class="chatItem">
      <input
      v-model="message"
      type="text"
      @keyup="sendMessage"
      class="chatInput"
      >
      <button @click="sendMessage" id="chatButton">보내기</button>
    </div>
  </div>
  
  
  </div>
    <h1>대기방임</h1>
    <hr>
    <router-link :to="{ name: 'ingame', params: { id_pk: num } }" 
        class="w3-bar-item w3-button">게임시작</router-link>
  </div>
</template>

<script>
import { mapGetters, mapActions } from 'vuex'
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
      userList: [],
      nickName: 'a',
      message: '',
      recvList: []
    }
  },
  
  computed: {
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
    // App.vue가 생성되면 소켓 연결을 시도합니다.
    this.connect()
    console.log(this.isCaptain)
    console.log(this.roomNo)
  },

  methods: {
    tryDeleteRoom() {
      this.deleteRoom(this.roomNo)
    },
    tryLeaveRoom() {
    // roomInfo = { roomNo: int, password: { password: int } }
    const roomInfo = {
      roomNo: this.roomNo,
      password: 0,
    }
    this.enterRoom(roomInfo)
    },
    connect() {
      const serverURL = "https://i7e107.p.ssafy.io:8080"
      let socket = new SockJS(serverURL);
      this.stompClient = Stomp.over(socket);
      console.log(`소켓 연결을 시도합니다. 서버 주소: ${serverURL}`)
      this.stompClient.connect(
        {},
        frame => {
          // 소켓 연결 성공
          this.connected = true;
          console.log('소켓 연결 성공', frame);
          // 서버의 메시지 전송 endpoint를 구독합니다.
          // 이런형태를 pub sub 구조라고 합니다.
          this.stompClient.subscribe("/sendChat/", res => {
            console.log('구독으로 받은 메시지 입니다.', res.body);

            // 받은 데이터를 json으로 파싱하고 리스트에 넣어줍니다.
            this.recvList.push(JSON.parse(res.body))
          });
        },
        error => {
          // 소켓 연결 실패
          console.log('소켓 연결 실패', error);
          this.connected = false;
        }
      );        
    },
    send() {
      console.log("Send message:" + this.message);
      if (this.stompClient && this.stompClient.connected) {
        const msg = { 
          roomNo: this.roomNo,
          nickName: this.nickName,
          content: this.message 
        };
        this.stompClient.send(`/receiveChat/${this.roomNo}`, JSON.stringify(msg), {});
      }
    },
    sendMessage (e) {
      if(e.keyCode === 13 && this.nickName !== '' && this.message !== ''){
        this.send()
        this.message = ''
      }
    }
  },
  mounted() {
  }
}
</script>

<style>
.w3-bar.w3-black.w3-hide-small .w3-bar-item.w3-button {
  display: inline-block;
  height: 40px;
} 
.w3-bar.w3-black.w3-hide-small .w3-bar-item.w3-right {
  height: 40px;
  padding-top: 6px;
  padding-right: 5px;
  margin: 0 0;
}
.w3-bar.w3-black.w3-hide-small .w3-bar-item.w3-right > i {
  position: relative;
  top: -2px;
}
.w3-bar.w3-black.w3-hide-small .fa {
  /* height: 30px; */
  margin: 0;
  /* padding-top: 6px; 
  margin: 0 0; */
}
.wait-background {
  background-image: url(../../public/homedesign/images/wait_mafia.gif);
  background-repeat: no-repeat;
  background-size: cover;
}

.chatList {
  height: 300px;
  width: 80%;
  background-color: whitesmoke;
  margin: auto;
}
.chatList::-webkit-scrollbar {
  width: 2px;
}

.chatItem {
  width: 80%;
  display: flex;
  margin: auto;
}

.chatInput {
  width: 955px;
}

</style>