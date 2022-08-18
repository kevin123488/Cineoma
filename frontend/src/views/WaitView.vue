<template class="">
  <div class="w3-black wait-background">
    <div class="waitViewChar"></div>
    <h1 class="waitRoomName" style="padding: 15px">
      <b>{{ roomTitle }}</b>
    </h1>
    <div class="waitMainSource">
      <!-- <router-link :to="{ name: 'lobby' }">
        <div>소켓연결 테스트ㄱㄱ</div>
      </router-link> -->

      <!-- Header -->
      <div class="waitRoomBtns">
        <div class="d-flex mt-3">
          <span v-if="isCaptain">
            <button
              v-if="ifStart"
              class="w3-button w3-white w3-hide-small mx-2"
              style="height: 50px; font-size: 20px"
              @click="startSignal"
            >
              게임시작
            </button>
            <button
              v-else
              class="w3-button w3-white w3-hide-small mx-2"
              style="height: 50px; font-size: 20px"
              disabled
            >
              게임시작
            </button>
          </span>

          <span v-if="!isCaptain">
            <button
              v-if="!ifReady"
              class="w3-button w3-white w3-hide-small mx-2"
              style="height: 50px; font-size: 20px"
              @click="sendReady"
            >
              준비
            </button>
            <button
              v-if="ifReady"
              class="w3-button w3-white w3-hide-small mx-2"
              style="height: 50px; font-size: 20px"
              @click="sendReady"
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
              class="w3-button w3-white w3-hide-small mx-2"
              style="height: 50px; font-size: 20px"
              @click="sendBreak"
            >
              <i class="fa fa-close"></i>방삭제
            </button>
            <button
              v-if="!isCaptain"
              class="w3-button w3-white w3-hide-small mx-2"
              style="height: 50px; font-size: 20px"
              @click="sendOut"
            >
              <i class="fa fa-close"></i>방나가기
            </button>

            <!-- </router-link> -->
          </a>
        </div>
      </div>

      <!-- First Photo Grid-->
      <!-- class="userListInfo w3-col m3 mx-3 my-3 w3-container w3-margin-bottom border border-secondary w3-white" -->
      <div class="userListInfo">
        <div
          class="userItemInfo d-flex"
          v-for="(item, idx) in waitUsers"
          :key="idx"
        >
          <div id="userDetail">
            <div v-show="item.ifReady" class="readySetStamp"></div>
            <p>닉네임: {{ item.nickName }}</p>
            <p>승률: {{ item.winRate }}%</p>
          </div>
          <div class="imgWrap">
            <img
              class="userItemInfoImg"
              :src="'/../../homedesign/images/' + item.imagePath"
              alt=""
            />
          </div>
          <!-- <div class="readySetChar" v-show="item.ifReady"></div> -->
        </div>
      </div>

      <div class="w3-row">
        <!-- <div class="chatList w3-col m12"> -->
        <div class="chatList" id="chatList">
          <div v-for="(item, idx) in recvList" :key="idx">
            <h3>{{ item.nickName }} : {{ item.content }}</h3>
          </div>
        </div>
        <div class="chatItem">
          <input
            v-model="message"
            type="text"
            @keyup.enter="sendChat"
            class="chatInput"
          />
          <button @click="sendChat" class="sendChatBtn learn-more">
            보내기
          </button>
        </div>
      </div>
    </div>
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
      waitUsers: [],
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
  },

  watch: {
    usersInfo() {
      this.waitUsers = this.usersInfo;
    },
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
      // const serverURL = "https://i7e107.p.ssafy.io/roomSocket";
      let socket = new SockJS(serverURL);
      this.stompClient = Stomp.over(socket);

      // const oldCloseCB = this.stompClient.ws.onclose
      // this.stompClient.ws.onclose = e => {
      //  console.log('소켓끊김')
      //  console.log(e)
      // }

      socket.onclose = function () {
        this.saveIsConnected(false);
      };

      this.stompClient.connect(
        {},
        (frame) => {
          // 소켓 연결 성공
          this.connected = true;

          // this.saveIsConnected(true)
          console.log(this.isConnected);

          console.log("소켓 연결 성공", frame);
          // const sessionIdLength = socket._transport.url.length
          this.sessionId = socket._transport.url.slice(-18, -10);
          this.sendProfile();

          // 채팅
          this.stompClient.subscribe(
            `/topic/sendChat/${this.roomNo}`,
            (res) => {
              this.recvList.push(JSON.parse(res.body));

              this.$nextTick(() => {
                let messages = document.querySelector("#chatList");

                messages.scrollTo({
                  top: messages.scrollHeight,
                  behavior: "smooth",
                });
              });
            }
          );

          // 프로필
          this.stompClient.subscribe(
            `/topic/sendProfile/${this.roomNo}`,
            (res) => {
              const data = JSON.parse(res.body);
              this.usersInfo = data.userList;

              // 방 나가기
              if (res.body.progress === "out") {
                this.stompClient.disconnect();
                this.$router.push({ name: "lobby " });
              }
            }
          );

          // 레디
          this.stompClient.subscribe(
            `/topic/sendReady/${this.roomNo}`,
            (res) => {
              const readyData = JSON.parse(res.body);
              this.ifStart = readyData.ifStart;
              this.usersInfo.forEach((user) => {
                if (user.id === readyData.id) {
                  user.ifReady = readyData.ifReady;
                }
              });

              if (readyData.startGame === true) {
                this.$router.push({
                  name: "ingame",
                  params: { id_pk: this.roomNo },
                });
              }
            }
          );

          // 방 폭파
          this.stompClient.subscribe(`/topic/sendBreak/${this.roomNo}`, () => {
            this.stompClient.disconnect();
            this.$router.push({ name: "lobby" });
          });
        },

        () => {
          // 소켓 연결 실패
          this.connected = false;
        }
      );
    },

    sendChat() {
      if (this.stompClient && this.stompClient.connected) {
        const msg = {
          roomNo: this.roomNo,
          nickName: this.nickName,
          content: this.message,
        };
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
        this.stompClient.send("/receiveReady", JSON.stringify(msg), {});
      }
    },

    // 방 나가기
    sendOut() {
      if (this.stompClient && this.stompClient.connected) {
        this.stompClient.disconnect();
        // const msg = {
        //   roomNo: this.roomNo,
        //   id: this.user.id,
        // };
        // console.log(msg);
        // this.stompClient.send("/receiveProfile", JSON.stringify(msg), {});
      }
    },

    // 방 폭파
    sendBreak() {
      if (this.stompClient && this.stompClient.connected) {
        const msg = {
          roomNo: this.roomNo,
        };
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

      const voteMembers = document.querySelectorAll(".votePaperMembers");
      voteMembers.forEach((voteMember) => {
        while (voteMember.hasChildNodes()) {
          voteMember.removeChild(voteMember.firstChild);
        }
      });
    },

    // 게임시작
    startSignal() {
      this.startGame = true;
      this.sendReady();
    },
  },
  mounted() {},
  unmounted() {
    this.stompClient.disconnect();
  },
};
</script>

<style>
.w3-bar.w3-black.w3-hide-small .w3-bar-item.w3-button {
  display: inline-block;
  height: 50px;
  font-size: 20px;
}
.w3-bar.w3-black.w3-hide-small .w3-bar-item.w3-right {
  height: 50px;
  font-size: 20px;
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
  height: 100vh;
}

.chatList {
  position: absolute;
  border: 2px solid black;
  border-radius: 20px;
  top: 15%;
  left: 50%;
  color: black;
  height: 50vh;
  width: 30vw;
  overflow: auto;
  padding-top: 2px;
  padding-left: 10px;
}
.chatList::-webkit-scrollbar {
  width: 2px;
}

.chatItem {
  position: absolute;
  width: 65vw;
  top: 90%;
  left: 50%;
  transform: translate(-50%, -50%);
}

.chatInput {
  border-radius: 20px;
  width: 55vw;
  padding: 10px 10px 10px 10px;
  font-family: "NeoDunggeunmo Code";
  background-color: transparent;
  color: black;
}

#chatButton {
  border-radius: 20px;
  padding: 10px 10px 10px 10px;
  font-family: "NeoDunggeunmo Code";
}

/* 대기방 디자인 */

.waitMainSource {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  background-image: url(../../public/homedesign/images/mypage_background.png);
  background-size: cover;
  background-repeat: no-repeat;
  height: 80vh;
  width: 80vw;
}

.waitRoomName {
  color: #000;
  z-index: 1000;
  position: absolute;
  top: 10%;
  left: 50%;
  transform: translate(-50%, -50%);
  background-image: url(../../public/homedesign/images/mypage_background.png);
  background-size: cover;
  background-repeat: no-repeat;
}

.waitRoomBtns {
  position: absolute;
  width: 10vw;
  height: 5vh;
  top: 5%;
  left: 10%;
}

.sendChatBtn {
  background-color: transparent;
  color: black;
  border-radius: 20px;
  border: 2px solid black;
}

.userListInfo {
  position: relative;
  overflow: auto;
  top: 47%;
  left: 30%;
  width: 25vw;
  height: 50vh;
  /* border: 2px solid black; */
  transform: translate(-50%, -50%);
  -ms-overflow-style: none;
  /* text-shadow: -1px 0 #000, 0 1px #000, 1px 0 #000, 0 -1px #000; */
  color: #000;
}

.userListInfo::-webkit-scrollbar {
  display: none;
}

.userItemInfo {
  position: relative;
  display: flex;
  justify-content: space-between;
  border-radius: 30px;
}

.userItemInfoImg {
  width: 8vw;
  border-radius: 30px;
}

.waitViewChar {
  z-index: 2;
  position: absolute;
  top: 70%;
  left: 25%;
  background-image: url(../../public/homedesign/images/mafia_red.png);
  width: 20vh;
  height: 20vh;
  background-size: cover;
  background-repeat: no-repeat;
  animation-duration: 1s;
  animation-iteration-count: infinite;
  animation-name: charUpWait;
}

@keyframes charUpWait {
  from {
    top: 65%;
    left: 8%;
  }
  to {
    top: 67%;
    left: 8%;
  }
}

.readySetChar {
  z-index: 1000;
  position: absolute;
  top: 60%;
  left: 60%;
  height: 10vh;
  width: 10vh;
  background-image: url(../../public/homedesign/images/mafia_black.png);
  background-size: cover;
  background-repeat: no-repeat;
}

#userDetail {
  font-size: 30px;
}

.imgWrap {
  display: flex;
  align-items: center;
}

.readySetStamp {
  z-index: 1000;
  position: absolute;
  left: -5%;
  top: -40%;
  height: 22vh;
  width: 42vh;
  background-image: url(../../public/homedesign/images/ready-stamp.png);
  background-size: cover;
  background-repeat: no-repeat;
}
</style>
