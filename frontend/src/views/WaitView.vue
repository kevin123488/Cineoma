<template class="">
  <div class="w3-black wait-background" style="height: 1000px">
    <div class="w3-main mx-5">
      <router-link :to="{ name: 'lobby' }">
        <div>소켓연결 테스트ㄱㄱ</div>
      </router-link>

      <!-- Header -->
      <header id="portfolio">
        <div class="w3-container">
          <h1>
            <b>{{ roomTitle }}</b>
          </h1>
          <div class="w3-section w3-bottombar w3-padding-16">
            <span v-if="isCaptain">
              <button
                v-if="ifStart"
                @click="startSignal"
                class="w3-button w3-white w3-hide-small"
              >
                게임시작
              </button>
              <button v-else disabled class="w3-button w3-white w3-hide-small">
                게임시작
              </button>
            </span>

            <span>
              <button
                v-if="!ifReady"
                @click="sendReady"
                class="w3-button w3-white w3-hide-small"
              >
                준비
              </button>
              <button
                v-if="ifReady"
                @click="sendReady"
                class="w3-button w3-white w3-hide-small"
              >
                준비취소
              </button>
            </span>
            <a href="/lobby">
              <!-- <router-link :to="{ name: 'lobby' }" > -->
              <!-- 방나가기 /room/roomNum -->
              <!-- method: PUT -->
              <!-- 방 삭제 /room/roomNum -->
              <!-- method: DELETE -->

              <button
                v-if="isCaptain"
                class="w3-button w3-white w3-hide-small"
                @click="sendBreak"
              >
                <i class="fa fa-close"></i>방삭제
              </button>
              <button
                v-if="!isCaptain"
                class="w3-button w3-white w3-hide-small"
                @click="sendOut"
              >
                <i class="fa fa-close"></i>방나가기
              </button>

              <!-- </router-link> -->
            </a>
          </div>
        </div>
      </header>

      <!-- First Photo Grid-->
      <div class="w3-row">
        <div
          class="w3-col m4 mx-5 my-5 w3-container w3-margin-bottom border border-secondary w3-white"
          v-for="(item, idx) in usersInfo"
          :key="idx"
        >
          <p>{{ item.nickName }}</p>
          <p>{{ item.winRate }}</p>
          <button id="readyButton" @click="isReady" v-if="!isCaptain">
            준비
          </button>
        </div>
      </div>

      <div class="w3-row">
        <div class="chatList w3-col m12">
          <div v-for="(item, idx) in recvList" :key="idx">
            <h3>{{ item.nickName }} : {{ item.content }}</h3>
          </div>
        </div>
        <div class="chatItem">
          <input
            v-model="message"
            type="text"
            @keyup.enter="sendChat"
            class="chatInput w3-col m10"
          />
          <button @click="sendChat" id="chatButton" class="w3-col m2">
            보내기
          </button>
        </div>
      </div>
    </div>
    <h1>대기방임</h1>
    <hr />
    <div v-if="isCaptain">
      <!-- <button v-if="ifStart" @click="startSignal">게임시작</button>
      <button v-else disabled>게임시작</button> -->
    </div>
    <button @click="startTest">게임시작-임시-</button>
  </div>
</template>

<script>
import { mapGetters, mapActions, mapState } from "vuex";
const roomdataStore = "roomdataStore";
const memberStore = "memberStore";

import Stomp from "webstomp-client";
import SockJS from "sockjs-client";
// import router from '@/router'

export default {
  components: {},
  data() {
    return {
      num: 1,
      user: {},
      usersInfo: [],
      nickName: "",
      message: "",
      recvList: [],
      startGame: false,
      ifStart: false,
      ifReady: false,
      sessionId: "",
    };
  },

  computed: {
    ...mapState(memberStore, ["userInfo"]),
    ...mapGetters(roomdataStore, [
      "roomNo",
      "roomTitle",
      "isCaptain",
      "isConnected",
    ]),
    ...mapGetters(memberStore, ["isLogin"]),
  },

  created() {
    this.user = this.userInfo;
    this.nickName = this.user.nickname;
    // App.vue가 생성되면 소켓 연결을 시도합니다.
    this.connect();
    console.log(this.isCaptain);
    console.log(this.roomNo);
    console.log(this.roomTitle);
  },

  methods: {
    ...mapActions(roomdataStore, [
      "deleteRoom",
      "enterRoom",
      "saveRoomTitle",
      "saveIsCaptain",
      "saveIsConnected",
    ]),
    // startVote() {
    //   this.$router.push("/vote");
    // },
    // 소켓 연결
    connect() {
      const serverURL = "http://localhost:8080/roomSocket";
      let socket = new SockJS(serverURL);
      this.stompClient = Stomp.over(socket);
      console.log(`소켓 연결을 시도합니다. 서버 주소: ${serverURL}`);

      // const oldCloseCB = this.stompClient.ws.onclose
      // this.stompClient.ws.onclose = e => {
      //  console.log('소켓끊김')
      //  console.log(e)
      // }

      socket.onclose = function () {
        this.saveIsConnected(false);
        console.log("===================끊겼나?=====================");
      };

      this.stompClient.connect(
        {},
        (frame) => {
          // 소켓 연결 성공
          this.connected = true;

          // this.saveIsConnected(true)
          console.log(this.isConnected);

          console.log("소켓 연결 성공", frame);
          console.log("소켓id출력");
          console.log(socket._transport.url);
          // const sessionIdLength = socket._transport.url.length
          this.sessionId = socket._transport.url.slice(-18, -10);
          this.sendProfile();

          // 채팅
          this.stompClient.subscribe(
            `/topic/sendChat/${this.roomNo}`,
            (res) => {
              console.log("구독으로 받은 채팅입니다.", res.body);
              this.recvList.push(JSON.parse(res.body));
            }
          );

          // 프로필
          this.stompClient.subscribe(
            `/topic/sendProfile/${this.roomNo}`,
            (res) => {
              console.log("구독으로 받은 프로필입니다.", res.body);
              const data = JSON.parse(res.body);
              this.usersInfo = data.userList;

              // 방 나가기
              if (res.body.progress === "out") {
                this.stompClient.disconnect();
                this.$route.push("lobby");
              }
            }
          );

          // 레디
          this.stompClient.subscribe(
            `/topic/sendReady/${this.roomNo}`,
            (res) => {
              console.log("구독으로 받은 레디입니다.", res.body);
              const readyData = JSON.parse(res.body);
              this.ifStart = readyData.ifStart;

              if (readyData.startGame === true) {
                this.$router.push({
                  name: "ingame",
                  params: { id_pk: this.roomNo },
                });
              }
            }
          );

          // 방 폭파
          this.stompClient.subscribe(
            `/topic/sendBreak/${this.roomNo}`,
            (res) => {
              console.log("방 폭파.", res.body);
              this.stompClient.disconnect();
              this.$router.push("lobby");
            }
          );
        },

        (error) => {
          // 소켓 연결 실패
          console.log("소켓 연결 실패", error);
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
          content: this.message,
        };
        console.log(msg);
        this.stompClient.send("/receiveChat", JSON.stringify(msg), {});
        this.message = "";
      }
    },

    // 프로필
    sendProfile() {
      if (this.stompClient && this.stompClient.connected) {
        const msg = {
          sessionId: this.sessionId,
          roomNo: this.roomNo,
          id: this.user.id,
        };
        console.log(msg);
        this.stompClient.send("/receiveProfile", JSON.stringify(msg), {});
      }
    },

    // 레디
    sendReady() {
      if (this.stompClient && this.stompClient.connected) {
        this.ifReady = !this.ifReady;
        const msg = {
          roomNo: this.roomNo,
          startGame: this.startGame,
          ifReady: this.ifReady,
          id: this.user.id,
        };
        console.log(msg);
        this.stompClient.send("/receiveReady", JSON.stringify(msg), {});
      }
    },

    // 방 나가기
    sendOut() {
      if (this.stompClient && this.stompClient.connected) {
        const msg = {
          roomNo: this.roomNo,
          id: this.user.id,
        };
        console.log(msg);
        this.stompClient.send("/receiveProfile", JSON.stringify(msg), {});
      }
    },

    // 방 폭파
    sendBreak() {
      if (this.stompClient && this.stompClient.connected) {
        const msg = {
          roomNo: this.roomNo,
        };
        console.log(msg);
        this.stompClient.send("/receiveBreak", JSON.stringify(msg), {});
      }
    },

    sendMessage(e) {
      if (e.keyCode === 13 && this.nickName !== "" && this.message !== "") {
        this.sendChat();
        this.message = "";
      }
    },

    // 레디
    isReady() {
      const readyButton = document.getElementById("readyButton");
      if (this.ifReady) {
        readyButton.innerText = "준비완료";
      } else {
        readyButton.innerText = "준비";
      }
      this.ifReady = !this.ifReady;
      this.sendReady();
    },

    startTest() {
      this.$router.push({ name: "ingame", params: { id_pk: this.roomNo } });
    },

    // 게임시작
    startSignal() {
      this.startGame = true;
      this.sendReady();
    },
  },
  mounted() {},
};
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
  border-radius: 20px;
  padding: 10px 10px 10px 10px;
  background-color: whitesmoke;
  margin: auto;
  color: #000;
}
.chatList::-webkit-scrollbar {
  width: 2px;
}

.chatItem {
  border-radius: 20px;
  padding: 10px 10px 10px 10px;
  display: flex;
  margin: auto;
  color: #000;
}

.chatInput {
  border-radius: 20px;
  padding: 10px 10px 10px 10px;
  font-family: "NeoDunggeunmo Code";
}

#chatButton {
  border-radius: 20px;
  padding: 10px 10px 10px 10px;
  font-family: "NeoDunggeunmo Code";
}
</style>
