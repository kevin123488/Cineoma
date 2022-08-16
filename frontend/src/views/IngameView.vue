<template>
  <div
    :class="{
      ingameNight: progress.isNight,
      ingameDay: progress.isDay,
      ingameDayVote: progress.isVoteDay,
      ingameDayVoteResult: progress.isVoteDayResult,
      ingameNightResult: progress.isNightResult,
    }"
  >
    <!-- 직업확인 두루마리 -->
    <button
      @click="openJobRoll"
      :class="{
        jobRollFormSide: !isjobRollCenter,
        jobRollFormCenter: isjobRollCenter,
      }"
    ></button>

    <!-- 직업확인창 -->
    <button v-if="isJobRollOpen" @click="switchJobRoll" class="openedJobRoll">
      <!-- 마피아 -->
      <div v-if="myInfo.job === 'mafia'">
        <div class="mafiaImage"></div>
        <div style="width: 65%; margin-left: 120px; margin-top: 270px">
          <h3 class="">당신의 직업: 마피아</h3>
          <h3 class="">밤마다 한명의 플레이어를 죽일 수 있습니다.</h3>
          <h3 class="">모든 플레이어를 죽이면 승리!</h3>
        </div>
      </div>

      <!-- 의사 -->
      <div v-if="myInfo.job === 'doctor'">
        <div class="doctorImage"></div>
        <div style="width: 65%; margin-left: 120px; margin-top: 250px">
          <h3 class="">당신의 직업: 의사</h3>
          <h3 class="">
            밤마다 한명의 플레이어를 마피아로 부터 보호할 수 있습니다.
          </h3>
          <h3 class="">마피아를 잡아내면 승리!</h3>
        </div>
      </div>

      <!-- 교주 -->
      <div v-if="myInfo.job === 'police'">
        <div class="headmasterImage"></div>
        <div style="width: 65%; margin-left: 120px; margin-top: 270px">
          <h3 class="">당신의 직업: 교주</h3>
          <h3 class="">특정 동작을 수행할 때 마다 신자 카운트가 증가합니다.</h3>
          <h3 class="">다른 플레이어 수 만큼 신자 카운트를 쌓으면 승리!</h3>
        </div>
      </div>

      <!-- 시민 -->
      <div v-if="myInfo.job === 'citizen'">
        <div class="citizenImage"></div>
        <div style="width: 65%; margin-left: 120px; margin-top: 250px">
          <h3 class="">당신의 직업: 시민</h3>
          <h3 class="">
            당신은 선량한 시민입니다. 다른 사람들과 협력해 마피아를 잡아내세요.
          </h3>
          <h3 class="">마피아를 잡아내면 승리!</h3>
        </div>
      </div>
    </button>

    <!-- 낮 투표용지 -->
    <div class="GovoteForm" v-if="progress.isVoteDay">
      <h3 class="dayVoteTitle">지금 낮 투표임</h3>
      <div class="voteItem">
        <div class="voteUserList" v-for="info in gameInfos" :key="info.id">
          <div
            :id="info.id"
            v-if="info.isAlive"
            class="btn-size u-button-style u-nav-link u-text-active-palette-1-base u-text-hover-palette-2-base"
            @click="chooseVote(info)"
          >
            <button class="learn-more">{{ info.nickname }}</button>
          </div>
        </div>
      </div>
      <div>
        <h5 style="text-align: center" class="mt-5">
          선택한 유저:
          <span style="font-weight: bold">{{ selected.nickname }}</span>
        </h5>
        <button
          class="sendVoteBtn"
          @click="sendVote(selected.id)"
          v-if="!!selected && !isVoted"
        >
          투표 확정 ㄱㄱ
        </button>
      </div>
    </div>

    <!-- 낮 투표 스킵 -->
    <button
      class="ingameDaySkipBtn"
      v-if="!isSkiped && progress.isDay"
      @click="sendSkip"
    >
      낮 스킵 후 투표로 넘어가기 지금 낮임
    </button>

    <!-- 낮 투표 결과 -->
    <div
      :class="{
        voteResultDay: progress.isVoteDayResult,
        voteResultNight: progress.isNightResult,
      }"
    >
      <!-- <h1 v-if="!!deadColor && progress.isVoteDayResult" style="text-align: center; color: black;">투표 결과<br>{{ whoIsGone }}<br>퇴출되었습니다!</h1> -->
      <h1
        v-if="progress.isVoteDayResult"
        style="text-align: center; color: black"
      >
        {{ showingMsg }}
      </h1>
      <h1
        v-if="progress.isNightResult"
        style="text-align: center; color: white"
      >
        {{ showingMsg }}
      </h1>
      <!-- <h1 v-if="!deadColor">아무 일 없이 지나감</h1> -->
      <div
        @click="getOut"
        :class="{
          redChar: deadColor === 'red',
          redCharGone: deadColor === 'red' && isGone,
          blueChar: deadColor === 'blue',
          blueCharGone: deadColor === 'blue' && isGone,
          whiteChar: deadColor === 'white',
          whiteCharGone: deadColor === 'white' && isGone,
          blackChar: deadColor === 'black',
          blackCharGone: deadColor === 'black' && isGone,
          yellowChar: deadColor === 'yellow',
          yellowCharGone: deadColor === 'yellow' && isGone,
        }"
      ></div>
      <div
        v-if="!!deadColor"
        :class="{
          deadShadow: true,
          deadShadowGone: isGone,
        }"
      ></div>
    </div>

    <div class="w3-main mx-5">
      <ingame-nav class="mb-3" :count="count" :progress="progress"></ingame-nav>
      <!-- 유저 화면 -->
      <div class="w3-row">
        <div class="w3-col m8">
          <div class="w3-row">
            <div class="mx-2 my-2 w3-container w3-col m5" id="video-container">
              <other-user-video
                class="OtherVideoBackground"
                :stream-manager="subscribers[0]"
                :gameInfo="gameInfos[0]"
              >
              </other-user-video>
            </div>

            <div class="mx-2 my-2 w3-container w3-col m5" id="video-container">
              <other-user-video
                class="OtherVideoBackground"
                :stream-manager="subscribers[1]"
                :gameInfo="gameInfos[1]"
              >
              </other-user-video>
            </div>

            <div class="mx-2 my-2 w3-container w3-col m5" id="video-container">
              <other-user-video
                class="OtherVideoBackground"
                :stream-manager="subscribers[2]"
                :gameInfo="gameInfos[2]"
              >
              </other-user-video>
            </div>

            <div class="mx-2 my-2 w3-container w3-col m5" id="video-container">
              <other-user-video
                class="OtherVideoBackground"
                :stream-manager="subscribers[3]"
                :gameInfo="gameInfos[3]"
              >
              </other-user-video>
            </div>
          </div>
        </div>

        <div class="w3-col m4">
          <div class="mx-2 py-2 w3-container w3-col m9 just myVideoBackground">
            <h3 class="brownColor" style="text-align: center">
              <b
                ><I>직업: {{ myInfo.job }}</I></b
              >
            </h3>
          </div>
          <div
            class="mx-2 my-2 w3-container w3-col m9 just"
            style="padding: 0%; justify-content: center"
            id="video-container"
          >
            <user-video
              v-if="myInfo.job !== 'police'"
              :stream-manager="publisher"
              :gameInfo="myInfo"
              class="myVideoBackground"
              style="text-align: center"
            />
            <mission-user-video
              v-if="myInfo.job === 'police'"
              :stream-manager="publisher"
              :gameInfo="myInfo"
              class="myVideoBackground"
            />
          </div>
        </div>
      </div>
    </div>
    <a href="/game/end">
      <button class="w3-button w3-white w3-hide-small" @click="gameEnd">
        <i class="fa fa-close"></i>게임끝내기
      </button>
    </a>
  </div>
</template>

<script>
const memberStore = "memberStore";
const ingameStore = "ingameStore";
const roomdataStore = "roomdataStore";
import { mapState, mapGetters, mapActions } from "vuex";
import IngameNav from "@/components/Ingame/IngameNav.vue";

import Stomp from "webstomp-client";
import SockJS from "sockjs-client";
import axios from "axios";

import { OpenVidu } from "openvidu-browser";
import UserVideo from "@/components/Ingame/UserVideo.vue";
import OtherUserVideo from "@/components/Ingame/OtherUserVideo.vue";
import MissionUserVideo from "@/components/Ingame/MissionUserVideo.vue";

axios.defaults.headers.post["Content-Type"] = "application/json";

const OPENVIDU_SERVER_URL = "https://i7e107.p.ssafy.io:6443";
const OPENVIDU_SERVER_SECRET = "MY_SECRET";

export default {
  name: "IngameView",
  components: {
    IngameNav,
    UserVideo,
    OtherUserVideo,
    MissionUserVideo,
  },
  data() {
    return {
      // OpenVidu objects
      OV: undefined,
      session: undefined,
      publisher: undefined,
      subscribers: [],

      // Join form
      mySessionId: this.$route.params.roomnumber,
      myUserName: "",

      // 게임정보
      startGame: false,
      startGameSignal: false,
      gameInfos: [],
      myInfo: {},
      progress: {
        nowDay: 0,
        isDay: true,
        isVoteDay: false,
        isVoteDayResult: false,
        isNight: false,
        isNightResult: false,
      },
      missionWin: false,

      // 투표
      voteNo: 1, // 투표권
      selected: "",
      isVoted: false,

      // 시간관련
      dayStartTime: null,
      gameDayTime: null,
      gameDayVoteTime: null,
      gameVoteResultTime: null,
      gameNightTime: null,
      gameNightResultTime: null,
      dayTimeCount: 0,
      dayVoteTimeCount: 0,
      dayVoteResultCount: 0,
      dayNightTimeCount: 0,
      dayNightResultCount: 0,
      count: 0,

      // 직업정보열람용
      isjobRollCenter: true,
      isJobRollOpen: false,
      job: "citizen",
      setUserColor: [],

      // 낮 스킵 관련
      isSkiped: false, // 투표 상황으로 넘어가면 반드시 false로 재 설정

      // 낮 투표 관련
      voteClearNum: 0, // 낮 투표상황에서 투표했을 때 sendVote 함수를 실행 안시키기 위한 썸띵
      dayVoteUser: [], // 투표상황에서 벗어나면 (낮 투표에서 낮 투표 결과로 가면) 빈 리스트로 초기화 해줘야 함
      dayVotedUser: [], // 애도 마찬가지임
      clearId: 0,

      // 투표 결과
      deadColor: "", // 투표 결과로 죽은 사람이 있다면 걔 색을 얘한테 ㄱ
      isGone: false, //
      whoIsGone: "", // 투표 결과로 죽은 사람이 있다면 걔 닉네임을 얘한테 ㄱ
      msgCnt: 0, // 투표결과 타이핑 효과에 필요
      showingMsg: "", // 투표 결과로 보여줄 메시지
      stopCnt: 0, // 투표결과 타이핑 효과 멈추는 용도
      stopCnt2: 0, // 투표결과 타이핑 효과 멈추는 용도

      // 밤 투표 결과
      voteClearNum2: 0,
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
    ...mapGetters(ingameStore, ["job"]),
  },
  beforeCreate() {},

  async created() {
    this.mySessionId = `${this.roomNo}`;
    // 미션정보 세팅
    this.setIfWin(false);
    this.setMissionCnt(0);
    this.setMissionClass(this.randomNum(0, 2));

    // this.mySessionId = "a";
    this.myUserName = this.userInfo.nickname;
    this.myUserId = this.userInfo.id;
    // console.log("=====================참가자=================")
    // console.log("=====================참가자=================")
    // console.log("=====================참가자=================")
    // console.log(this.subscribers)
    this.OV = await new OpenVidu();

    // --- Init a session ---
    this.session = await this.OV.initSession();

    // --- Specify the actions when events take place in the session ---

    // On every new Stream received...
    await this.session.on("streamCreated", ({ stream }) => {
      const subscriber = this.session.subscribe(stream);
      this.subscribers.push(subscriber);
      const tmp = subscriber.stream.connection.data.split('"');
      console.log(`tmp : ${tmp}`);
      const userData = tmp[3].split(",");

      console.log(`userData : ${userData}`);
      console.log(`id : ${userData[0]}`); // 아이디
      console.log(`nick : ${userData[1]}`); // 닉네임
      console.log("=============얘는 session on 부분=================");
      this.gameInfos.push({
        id: userData[0],
        nickname: userData[1],
        isAlive: true, // 살았나 죽었나
        color: "", //색깔
        voted: [],
      });
    });

    // On every Stream destroyed...
    await this.session.on("streamDestroyed", ({ stream }) => {
      const index = this.subscribers.indexOf(stream.streamManager, 0);
      if (index >= 0) {
        this.subscribers.splice(index, 1);
      }
    });

    // On every asynchronous exception...
    await this.session.on("exception", ({ exception }) => {
      console.warn(exception);
    });

    // --- Connect to the session with a valid user token ---

    // 'getToken' method is simulating what your server-side should do.
    // 'token' parameter should be retrieved and returned by your own backend
    await this.getToken(this.mySessionId).then((token) => {
      this.session
        .connect(token, {
          clientData: `${this.myUserId}, ${this.myUserName}`,
        })
        .then(() => {
          // --- Get your own camera stream with the desired properties ---

          let publisher = this.OV.initPublisher(undefined, {
            audioSource: undefined, // The source of audio. If undefined default microphone
            videoSource: undefined, // The source of video. If undefined default webcam
            publishAudio: false, // Whether you want to start publishing with your audio unmuted or not
            publishVideo: true, // Whether you want to start publishing with your video enabled or not
            resolution: "640x480", // The resolution of your video
            frameRate: 30, // The frame rate of your video
            insertMode: "APPEND", // How the video is inserted in the target element 'video-container'
            mirror: false, // Whether to mirror your local video or not
          });

          this.publisher = publisher;

          // --- Publish your stream ---

          this.session.publish(this.publisher);
        })
        .catch((error) => {
          console.log(
            "There was an error connecting to the session:",
            error.code,
            error.message
          );
        });
    });

    window.addEventListener("beforeunload", this.leaveSession);

    this.myInfo = {
      id: this.userInfo.id,
      nickname: this.userInfo.nickname,
      isAlive: true, // 살았나 죽었나
      color: "", //색깔
      job: "", // 직업
      voted: [],
    };
    const serverURL = "http://localhost:8080/roomSocket";
    // const serverURL = "https://i7e107.p.ssafy.io/roomSocket";
    let socket = await new SockJS(serverURL);
    this.stompClient = await Stomp.over(socket);
    console.log(`소켓 연결을 시도합니다. 서버 주소: ${serverURL}`);
    await this.stompClient.connect(
      {},
      async (frame) => {
        // 소켓 연결 성공
        this.connected = true;
        console.log("소켓 연결 성공", frame);
        console.log("======================됐나?=======================");
        // 직업, 색깔 설정
        // 소켓Id 다시줌
        this.sessionId = socket._transport.url.slice(-18, -10);
        this.sendStart();

        await this.stompClient.subscribe(
          `/topic/sendMafia/${this.mySessionId}/${this.userInfo.id}`,
          (res) => {
            console.log(
              "=========================개인별로 받는 정보============================="
            );
            console.log(res);
            console.log("구독으로 받은 게임 정보입니다.", res.body);
            console.log(`==== gameInfos ${this.gameInfos} ====`);
            const data = JSON.parse(res.body);
            console.log(
              "=======================직업 뭐받는지 확인================"
            );
            this.myInfo.job = data.job;
            this.setUserColor = data;

            // data.joinUsers.forEach((joinUser) => {
            //   this.gameInfos.forEach((gameInfo) => {
            //     console.log("===지금지금지금===");
            //     if (joinUser.id === gameInfo.id) {
            //       gameInfo.color = joinUser.color;
            //     }
            //     if (joinUser.id === this.myInfo.id) {
            //       this.myInfo.color = joinUser.color;
            //     }
            //   });
            // }
            // );

            // for문 실험
            // for (let joinUser of data.joinUsers) {
            //   for (let gameInfo of this.gameInfos) {
            //     console.log("지금지금지금지금지금");
            //     if (joinUser.id === gameInfo.id) {
            //       gameInfo.color = joinUser.color;
            //     }
            //     if (joinUser.id === this.myInfo.id) {
            //       console.log("해치웠나?");
            //       this.myInfo.color = joinUser.color;
            //     }
            //   }
            // }

            // this.setCount(data.absoluteTime);
            console.log(
              "=============얘는 gameinfos에 색 세팅되는 부분================="
            );
            console.log(
              "====================gameinfos 확인===================="
            );
            data.joinUsers.forEach((joinUser) => {
              this.gameInfos.forEach((gameInfo) => {
                console.log(joinUser.id);
                if (joinUser.id === gameInfo.id) {
                  gameInfo.color = joinUser.color;
                }
                if (joinUser.id === this.myInfo.id) {
                  this.myInfo.color = joinUser.color;
                }
              });
            });
            this.setStartTime(data.absoluteTime);
            console.log(
              "=============얘는 gameinfos에 색 세팅되는 부분================="
            );
            console.log(
              "====================gameinfos 확인===================="
            );
            console.log(this.gameInfos);
            this.startDay();
          }
        );

        await this.stompClient.subscribe(
          `/topic/sendMafia/${this.mySessionId}`,
          (res) => {
            console.log("구독으로 받은 게임 정보입니다.", res.body);
            const data = JSON.parse(res.body);

            // 낮 진행
            if (data.progress === "day") {
              this.color = data.color; // 긴급버튼 누른 사람의 색
              this.voteUser = data.nickname; // 긴급버튼 누른 사람의 닉네임
              this.showModal = true;

              setTimeout(() => {
                this.showModal = false;
              }, 1000);

              if (data.ifSkip === true) {
                this.progress.isDay = false;
                this.setIsDay(false);
                this.progress.isVoteDay = true;
                clearTimeout(this.clearId);
                this.setSkipTime();
              }
            }

            // 낮 투표
            // 투표할때마다 상호작용
            if (data.progress === "voteDay") {
              // 낮 투표 클릭할때마다 누가 누굴 선택했는지 모두에게 정보가 올 것
              // data에 dayVoteUser와 dayVotedUser를 리스트의 형태로 만들어 두는거임
              // 그 dayVoteUser에는 id값을, dayVotedUser에는 votedId를 넣어둘 거임
              // dayVoteUser의 id값을 이용, id에 해당하는 색 정보를 받고
              // 색 정보에 맞는 표식(체크든 동그라미든)을 votedId에 해당하는 유저에게 띄워줌
              // voteDay에서 voteResult로 넘어갈 때 dayVoteUser와 dayVotedUser 초기화 해줌
              this.dayVoteUser.push(data.id);
              this.dayVotedUser.push(data.votedId);
              console.log("=========votedId========");
              console.log(data.votedId);
              if (data.votedId !== "") {
                const clicked = document.getElementById(`${data.votedId}`);
                console.log(clicked);
                const element = document.createElement("div");
                this.gameInfos.forEach((user) => {
                  if (user.id === data.id) {
                    element.classList.add(`${user.color}Voted`); // black부터 white까지 클래스 만들어두기
                    clicked.appendChild(element);
                  }
                });
              }
              // const clicked = document.getElementById(`${data.votedId}`);
              // console.log(clicked)
              // const element = document.createElement("div");
              // this.gameInfos.forEach((user) => {
              //   if (user.id === data.id) {
              //     element.classList.add(`${user.color}`); // black부터 white까지 클래스 만들어두기
              //     clicked.appendChild(element);
              //   }
              // });
            }

            // 낮 투표 결과 받기
            console.log("res body progress 오냐?");
            console.log(data.progress);
            if (data.progress === "voteDayFinish") {
              // 이 시점에 낮 투표시 넣어줬던 누가 누구 뽑았는지에 대한 정보를 지워줄 필요가 있음
              // 누가 하겠지
              // 지금 나는 모르겠음
              console.log(
                "================투표 응답 확인하자================="
              );
              console.log(res);
              this.gameInfos.forEach((user) => {
                if (user.id === data.id) {
                  this.deadColor = user.color;
                  this.whoIsGone = user.nickname;
                }
              });
              if (data.winJob !== "") {
                this.gameEnd(data.winJob);
              } else {
                this.dayVoteResult();
                console.log(
                  "================================================="
                );
                console.log(
                  "투표 결과 들어와서 투표 결과 보여주는 부분 진행되냐?"
                );
              }
            }

            // 밤 투표
            if (data.progress === "voteNight") {
              this.gameInfos.forEach((user) => {
                if (user.id === data.votedId) {
                  this.deadColor = user.color;
                  this.whoIsGone = user.nickname;
                }
              });
              if (data.winJob) {
                this.gameEnd(data.winJob);
              } else {
                this.dayNightResult();
              }
            }
          }
        );
      },

      (error) => {
        // 소켓 연결 실패
        console.log("소켓 연결 실패", error);
        this.connected = false;
      }
    );
    // this.startDay();
  },
  mounted() {
    console.log(this.isConnected);
  },
  watch: {
    startGame() {
      this.startGameSignal = true;
    },
  },
  methods: {
    ...mapActions(ingameStore, [
      "setGameResult",
      "setIsDay",
      "setIfWin",
      "setMissionCnt",
      "setMissionClass",
    ]),
    ...mapActions(roomdataStore, [
      "deleteRoom",
      "enterRoom",
      "saveRoomTitle",
      "saveIsCaptain",
      "saveIsConnected",
    ]),

    // 투표
    chooseVote(user) {
      this.selected = user;
    },

    // 랜덤미션번호
    randomNum(min, max) {
      var randNum = Math.floor(Math.random() * (max - min + 1)) + min;
      return randNum;
    },

    // 직업정보열람용
    switchJobRoll() {
      this.isjobRollCenter = false;
      this.isJobRollOpen = false;
      this.myInfo.job = this.setUserColor.job;
      this.gameInfos.forEach((gameInfo) => {
        this.setUserColor.joinUsers.forEach((joinUser) => {
          if (gameInfo.id === joinUser.id) {
            gameInfo.color = joinUser.color;
          }
        });
      });
    },
    openJobRoll() {
      if (this.isjobRollCenter) {
        this.isJobRollOpen = true;
      } else {
        setTimeout(() => {
          this.isJobRollOpen = true;
        }, 700);
        if (!this.isjobRollCenter) {
          this.isjobRollCenter = true;
        }
      }
    },

    joinSession() {
      // --- Get an OpenVidu object ---
      this.OV = new OpenVidu();

      // --- Init a session ---
      this.session = this.OV.initSession();

      // --- Specify the actions when events take place in the session ---

      // On every new Stream received...
      this.session.on("streamCreated", ({ stream }) => {
        const subscriber = this.session.subscribe(stream);
        this.subscribers.push(subscriber);
        const tmp = subscriber.stream.connection.data.split('"');
        console.log(`tmp : ${tmp}`);
        const userData = tmp[3].split(",");

        console.log(`userData : ${userData}`);
        console.log(`id : ${userData[0]}`); // 아이디
        console.log(`nick : ${userData[1]}`); // 닉네임
        console.log("=============얘는 session on 부분=================");
        this.gameInfos.push({
          id: userData[0],
          nickname: userData[1],
          isAlive: true, // 살았나 죽었나
          color: "", //색깔
          voted: [],
        });
      });

      // On every Stream destroyed...
      this.session.on("streamDestroyed", ({ stream }) => {
        const index = this.subscribers.indexOf(stream.streamManager, 0);
        if (index >= 0) {
          this.subscribers.splice(index, 1);
        }
      });

      // On every asynchronous exception...
      this.session.on("exception", ({ exception }) => {
        console.warn(exception);
      });

      // --- Connect to the session with a valid user token ---

      // 'getToken' method is simulating what your server-side should do.
      // 'token' parameter should be retrieved and returned by your own backend
      this.getToken(this.mySessionId).then((token) => {
        this.session
          .connect(token, {
            clientData: `${this.myUserId}, ${this.myUserName}`,
          })
          .then(() => {
            // --- Get your own camera stream with the desired properties ---

            let publisher = this.OV.initPublisher(undefined, {
              audioSource: undefined, // The source of audio. If undefined default microphone
              videoSource: undefined, // The source of video. If undefined default webcam
              publishAudio: false, // Whether you want to start publishing with your audio unmuted or not
              publishVideo: true, // Whether you want to start publishing with your video enabled or not
              resolution: "640x480", // The resolution of your video
              frameRate: 30, // The frame rate of your video
              insertMode: "APPEND", // How the video is inserted in the target element 'video-container'
              mirror: false, // Whether to mirror your local video or not
            });

            this.publisher = publisher;

            // --- Publish your stream ---

            this.session.publish(this.publisher);
          })
          .catch((error) => {
            console.log(
              "There was an error connecting to the session:",
              error.code,
              error.message
            );
          });
      });

      window.addEventListener("beforeunload", this.leaveSession);
    },

    leaveSession() {
      // --- Leave the session by calling 'disconnect' method over the Session object ---
      if (this.session) this.session.disconnect();

      this.session = undefined;
      this.mainStreamManager = undefined;
      this.publisher = undefined;
      this.subscribers = [];
      this.OV = undefined;

      window.removeEventListener("beforeunload", this.leaveSession);
    },

    /**
     * --------------------------
     * SERVER-SIDE RESPONSIBILITY
     * --------------------------
     * These methods retrieve the mandatory user token from OpenVidu Server.
     * This behavior MUST BE IN YOUR SERVER-SIDE IN PRODUCTION (by using
     * the API REST, openvidu-java-client or openvidu-node-client):
     *   1) Initialize a Session in OpenVidu Server	(POST /openvidu/api/sessions)
     *   2) Create a Connection in OpenVidu Server (POST /openvidu/api/sessions/<SESSION_ID>/connection)
     *   3) The Connection.token must be consumed in Session.connect() method
     */

    getToken(mySessionId) {
      return this.createSession(mySessionId).then((sessionId) =>
        this.createToken(sessionId)
      );
    },

    // See https://docs.openvidu.io/en/stable/reference-docs/REST-API/#post-session
    createSession(sessionId) {
      return new Promise((resolve, reject) => {
        axios
          .post(
            `${OPENVIDU_SERVER_URL}/openvidu/api/sessions`,
            JSON.stringify({
              customSessionId: sessionId,
            }),
            {
              auth: {
                username: "OPENVIDUAPP",
                password: OPENVIDU_SERVER_SECRET,
              },
            }
          )
          .then((response) => response.data)
          .then((data) => resolve(data.id))
          .catch((error) => {
            if (error.response.status === 409) {
              resolve(sessionId);
            } else {
              console.warn(
                `No connection to OpenVidu Server. This may be a certificate error at ${OPENVIDU_SERVER_URL}`
              );
              if (
                window.confirm(
                  `No connection to OpenVidu Server. This may be a certificate error at ${OPENVIDU_SERVER_URL}\n\nClick OK to navigate and accept it. If no certificate warning is shown, then check that your OpenVidu Server is up and running at "${OPENVIDU_SERVER_URL}"`
                )
              ) {
                location.assign(`${OPENVIDU_SERVER_URL}/accept-certificate`);
              }
              reject(error.response);
            }
          });
      });
    },

    // See https://docs.openvidu.io/en/stable/reference-docs/REST-API/#post-connection
    createToken(sessionId) {
      return new Promise((resolve, reject) => {
        axios
          .post(
            `${OPENVIDU_SERVER_URL}/openvidu/api/sessions/${sessionId}/connection`,
            {},
            {
              auth: {
                username: "OPENVIDUAPP",
                password: OPENVIDU_SERVER_SECRET,
              },
            }
          )
          .then((response) => response.data)
          .then((data) => resolve(data.token))
          .catch((error) => reject(error.response));
      });
    },

    // 투표 결과창 관련
    typeEffect() {
      this.msgCnt += 1;
      if (this.whoIsGone) {
        this.showingMsg =
          `투표 결과 ${this.whoIsGone} 님이 퇴출되었습니다!`.slice(
            0,
            this.msgCnt
          );
      } else {
        this.showingMsg = `아무도 퇴출되지 않았습니다`.slice(0, this.msgCnt);
      }
      console.log("hi");
      if (this.msgCnt === 50) {
        clearInterval(this.stopCnt);
        this.stopCnt = 0;
      }
    },
    typeEffect2() {
      this.msgCnt += 1;
      if (this.whoisGone) {
        this.showingMsg =
          `밤 사이에 ${this.whoIsGone} 님이 사망하였습니다,,,`.slice(
            0,
            this.msgCnt
          );
      } else {
        this.showingMsg = `조용한 밤을 보냈습니다`.slice(0, this.msgCnt);
      }
      if (this.msgCnt === 50) {
        clearInterval(this.stopCnt2);
        this.stopCnt2 = 0;
      }
    },
    getOut() {
      this.isGone = true;
    },

    // 시간 관련
    // 게임시작
    startDay() {
      console.log("게임 시작함");
      setTimeout(() => {
        this.dayTime();
        this.startGame = true;
      }, 1000);
    },

    dayTime() {
      console.log("=================유저 정보 세팅 확인====================");
      console.log(this.gameInfos);
      console.log(this.myInfo);
      this.progress.nowDay = this.progress.nowDay + 1;
      this.progress.isNightResult = false;
      this.progress.isDay = true;
      this.isVoted = false;
      this.setIsDay(true);

      if (this.myInfo.isAlive) {
        this.avOn();
      }

      this.getDayTimeCount();
      this.count = this.dayTimeCount;
      setTimeout(() => {
        this.dayVoteTime();
      }, this.count * 1000 + 200);
    },

    dayVoteTime() {
      this.isSkiped = false;
      this.progress.isDay = false;
      this.setIsDay(false);
      this.progress.isVoteDay = true;
      this.getDayVoteTimeCount();
      this.count = this.dayVoteTimeCount;
      this.voteClearNum = setTimeout(() => {
        this.sendVote("");
        console.log(
          "=======================지금 들어가나?============================="
        );
      }, this.count * 1000 + 200);
    },

    dayVoteResult() {
      this.stopCnt = setInterval(this.typeEffect, 150); // 투표결과 타이핑 효과
      this.progress.isVoteDay = false;
      this.progress.isVoteDayResult = true;
      this.getDayVoteResultCount();
      this.count = this.dayVoteResultCount;
      this.isVoted = false;
      setTimeout(() => {
        this.isGone = false;
        this.deadColor = "";
        this.msgCnt = 0;
        this.stopCnt = 0;
        this.showingMsg = "";
        this.whoIsGone = "";
        this.dayNightTime();
      }, this.count * 1000 + 200);
    },

    dayNightTime() {
      this.progress.isVoteDayResult = false;
      this.progress.isNight = true;

      this.avOff();
      this.getDayNightTimeCount();
      this.count = this.dayNightTimeCount;
      this.voteClearNum2 = setTimeout(() => {
        this.sendVote("");
      }, this.count * 1000 + 200);
      // 여기 강제투표
    },

    dayNightResult() {
      this.stopCnt2 = setInterval(this.typeEffect2, 150);
      this.progress.isNight = false;
      this.progress.isNightResult = true;
      this.getDayNightResultCount();
      this.count = this.dayNightResultCount;
      setTimeout(() => {
        this.isGone = false;
        this.deadColor = "";
        this.msgCnt = 0;
        this.stopCnt2 = 0;
        this.showingMsg = "";
        this.whoIsGone = "";
        this.setDayEndTime();
        this.dayTime();
      }, this.count * 1000 + 200);
    },

    // 클릭 상호작용
    sendSkip() {
      if (this.stompClient && this.stompClient.connected) {
        const msg = {
          progress: "day",
          roomNo: this.roomNo,
          id: this.userInfo.id,
        };
        this.stompClient.send("/receiveMafia", JSON.stringify(msg), {});
        // this.progress.isDay = false; // 버튼 누르면 정보 담아서 보냄 -> 버튼 숨김
        this.isSkiped = true;
      } // 나중에 주석 풀기
      // this.dayToDayVote(); // 얘 나중에 주석처리
    },

    // 프로필
    sendStart() {
      if (this.stompClient && this.stompClient.connected) {
        const msg = {
          progress: "start",
          sessionId: this.sessionId,
          roomNo: this.roomNo,
          id: this.userInfo.id,
        };
        console.log(msg);
        this.stompClient.send("/receiveMafia", JSON.stringify(msg), {});
      }
    },

    sendVote(voteId) {
      clearTimeout(this.voteClearNum);
      clearTimeout(this.voteClearNum2);
      if (this.stompClient && this.stompClient.connected) {
        const msg = {
          progress: "voteDay",
          roomNo: this.mySessionId,
          id: this.myUserId,
          nickname: this.userInfo.nickname,
          job: this.myInfo.job,
          vote: voteId,
          ifWin: this.missionWin,
        };
        if (this.progress.isNight) {
          msg.progress = "voteNight";
        }
        console.log(msg);
        console.log("==================================");
        this.stompClient.send("/receiveMafia", JSON.stringify(msg), {});
      }
      this.selected = ""; // 투표 누르면 투표확정 버튼 사라지게 하자
      this.isVoted = true;
    },

    // 투표
    doVote(info) {
      if (
        this.voteNo === 1 &&
        (this.progress.isVoteDay || this.progress.isNight)
      ) {
        this.sendVote(info.id);
        this.voteNo = 0;
        console.log(`투표한 id ${info.id}`);
      }
    },

    avOn() {
      this.publisher.publishAudio(true);
      this.publisher.publishVideo(true);
    },

    avOff() {
      this.publisher.publishAudio(false);
      this.publisher.publishVideo(false);
    },

    gameEnd(winJob) {
      // const gameResult = {
      //   winJob: "mafia",
      //   nickname: "마피아고수",
      //   color: "red",
      // };
      const winJobList = [];
      this.gameInfos.forEach((user) => {
        //  if (user.job === winJob) {
        //   winJobList.push({
        //     winJob: winJob,
        //     nickname: user.nickname,
        //     color: user.color,
        //   });
        //  }
        if (winJob === "citizen") {
          if (user.job === "citizen" || user.job === "doctor") {
            winJobList.push({
              winJob: winJob,
              nickname: user.nickname,
              color: user.color,
            });
          }
        } else {
          if (user.job === winJob) {
            winJobList.push({
              winJob: winJob,
              nickname: user.nickname,
              color: user.color,
            });
          }
        }
      });

      console.log(typeof this.setGameResult);
      this.setGameResult(winJobList);
      this.leaveSession();
      this.stompClient.disconnect();
      this.$router.push({
        name: "gameend",
      });
    },

    setStartTime(time) {
      const convTime = time.split(":");

      const startHours = parseInt(convTime[0]); //시
      const startMinutes = parseInt(convTime[1]); //분
      const startSeconds = parseInt(convTime[2]); //초

      const startToday = new Date();
      const startYear = startToday.getFullYear(); //년
      const startMonth = startToday.getMonth(); //월
      const startDate = startToday.getDate(); //일

      const gameStartTime = new Date(
        startYear,
        startMonth,
        startDate,
        startHours,
        startMinutes,
        startSeconds
      );

      this.dayStartTime = gameStartTime;
      console.log("=============================");
      console.log(`시간입니다. ${this.dayStartTime}`);

      this.dayStartTime.setSeconds(this.dayStartTime.getSeconds() + 15);
      this.gameDayTime = new Date(this.dayStartTime);
      console.log(`==== gameDayTime ${this.gameDayTime} ====`);

      this.dayStartTime.setSeconds(this.dayStartTime.getSeconds() + 25);
      this.gameDayVoteTime = new Date(this.dayStartTime);
      console.log(`==== gameDayVoteTime ${this.gameDayVoteTime} ====`);

      this.dayStartTime.setSeconds(this.dayStartTime.getSeconds() + 5);
      this.gameVoteResultTime = new Date(this.dayStartTime);
      console.log(`==== gameVoteResultTime ${this.gameVoteResultTime} ====`);

      this.dayStartTime.setSeconds(this.dayStartTime.getSeconds() + 10);
      this.gameNightTime = new Date(this.dayStartTime);
      console.log(`==== gameNightTime ${this.gameNightTime} ====`);

      this.dayStartTime.setSeconds(this.dayStartTime.getSeconds() + 5);
      this.gameNightResultTime = new Date(this.dayStartTime);
      console.log(`==== gameNightResultTime ${this.gameNightResultTime} ====`);
    },

    getDayTimeCount() {
      let nowTime = new Date();
      const elapsedMSec = this.gameDayTime.getTime() - nowTime.getTime();
      console.log(`${this.gameDayTime} - ${nowTime}`);
      console.log(
        `${this.gameDayTime.getTime()} - ${nowTime.getTime()} = ${elapsedMSec}`
      );
      this.dayTimeCount = Math.floor(elapsedMSec / 1000);
      console.log(`==== dayTimeCount ${this.dayTimeCount} ====`);
    },

    getDayVoteTimeCount() {
      let nowTime = new Date();
      const elapsedMSec = this.gameDayVoteTime.getTime() - nowTime.getTime();
      console.log(`${this.gameDayVoteTime} - ${nowTime}`);
      console.log(
        `${this.gameDayVoteTime.getTime()} - ${nowTime.getTime()} = ${elapsedMSec}`
      );
      this.dayVoteTimeCount = Math.floor(elapsedMSec / 1000);
      console.log(`==== dayVoteTimeCount ${this.dayVoteTimeCount} ====`);
    },

    getDayVoteResultCount() {
      let nowTime = new Date();
      const elapsedMSec = this.gameVoteResultTime.getTime() - nowTime.getTime();
      this.dayVoteResultCount = Math.floor(elapsedMSec / 1000);
      console.log(`==== dayVoteResultCount ${this.dayVoteResultCount} ====`);
    },

    getDayNightTimeCount() {
      let nowTime = new Date();
      const elapsedMSec = this.gameNightTime.getTime() - nowTime.getTime();
      this.dayNightTimeCount = Math.floor(elapsedMSec / 1000);
      console.log(`==== dayNightTimeCount ${this.dayNightTimeCount} ====`);
    },

    getDayNightResultCount() {
      let nowTime = new Date();
      const elapsedMSec =
        this.gameNightResultTime.getTime() - nowTime.getTime();
      this.dayNightResultCount = Math.floor(elapsedMSec / 1000);
      console.log(`==== dayNightResultCount ${this.dayNightResultCount} ====`);
    },

    setDayEndTime() {
      let nowTime = new Date();
      this.dayStartTime = nowTime;

      this.dayStartTime.setSeconds(this.dayStartTime.getSeconds() + 15);
      this.gameDayTime = new Date(this.dayStartTime);
      console.log(`==== gameDayTime ${this.gameDayTime} ====`);

      this.dayStartTime.setSeconds(this.dayStartTime.getSeconds() + 25);
      this.gameDayVoteTime = new Date(this.dayStartTime);
      console.log(`==== gameDayVoteTime ${this.gameDayVoteTime} ====`);

      this.dayStartTime.setSeconds(this.dayStartTime.getSeconds() + 5);
      this.gameVoteResultTime = new Date(this.dayStartTime);
      console.log(`==== gameVoteResultTime ${this.gameVoteResultTime} ====`);

      this.dayStartTime.setSeconds(this.dayStartTime.getSeconds() + 10);
      this.gameNightTime = new Date(this.dayStartTime);
      console.log(`==== gameNightTime ${this.gameNightTime} ====`);

      this.dayStartTime.setSeconds(this.dayStartTime.getSeconds() + 5);
      this.gameNightResultTime = new Date(this.dayStartTime);
      console.log(`==== gameNightResultTime ${this.gameNightResultTime} ====`);
    },

    setSkipTime() {
      this.dayStartTime = new Date();

      this.dayStartTime.setSeconds(this.dayStartTime.getSeconds() + 25);
      this.gameDayVoteTime = new Date(this.dayStartTime);
      console.log(`==== gameDayVoteTime ${this.gameDayVoteTime} ====`);

      this.dayStartTime.setSeconds(this.dayStartTime.getSeconds() + 5);
      this.gameVoteResultTime = new Date(this.dayStartTime);
      console.log(`==== gameVoteResultTime ${this.gameVoteResultTime} ====`);

      this.dayStartTime.setSeconds(this.dayStartTime.getSeconds() + 10);
      this.gameNightTime = new Date(this.dayStartTime);
      console.log(`==== gameNightTime ${this.gameNightTime} ====`);

      this.dayStartTime.setSeconds(this.dayStartTime.getSeconds() + 5);
      this.gameNightResultTime = new Date(this.dayStartTime);
      console.log(`==== gameNightResultTime ${this.gameNightResultTime} ====`);
    },
  },
};
</script>

<style>
.voteUserList {
  display: flex;
  margin-top: 10px;
  cursor: pointer;
}
.userVideoLayout {
  height: 30vh;
  width: 30vh;
}
.ingameNight {
  background-image: url(../../public/homedesign/images/wait_mafia.gif);
  background-repeat: no-repeat;
  background-size: cover;
  height: 100vh;
}
.ingameDay {
  background-image: url(../../public/homedesign/images/day_mafia.gif);
  background-repeat: no-repeat;
  background-size: cover;
  height: 100vh;
}
.ingameDayVote {
  background-image: url(../../public/homedesign/images/intro_movie.mp4);
  background-repeat: no-repeat;
  background-size: cover;
  height: 100vh;
}
.ingameDayVoteResult {
  background-image: url(../../public/homedesign/images/intro_movie.mp4);
  background-repeat: no-repeat;
  background-size: cover;
  height: 100vh;
}
.ingameNightResult {
  background-image: url(../../public/homedesign/images/mypage_mafia.gif);
  background-repeat: no-repeat;
  background-size: cover;
  height: 100vh;
}
.dayVoteTitle {
  text-align: center;
  margin-top: 50px;
}
.voteItem {
  margin: auto;
  width: 50vh;
  left: 10%;
  right: 10%;
  margin-top: 60px;
  background-color: fff;
  /* opacity: 0.5; */
  height: 45vh;
  overflow: auto;
}
.voteItem::-webkit-scrollbar {
  display: none;
}
/* 직업열람용 */
.skipModal {
  margin: auto;
  position: absolute;
  width: 70%;
  height: 70%;
}
/* 결과창 Fade*/
.voteResultFade-enter-active,
.voteResultFade-leave-active {
  transition: opacity 1s;
}
.voteResultFade-enter-from,
.voteResultFade-leave-to {
  opacity: 0;
}
.ingameDaySkipBtn {
  position: absolute;
  top: 60%;
  left: 80%;
  height: 30px;
  border: 2px solid black;
  border-radius: 20px;
}
.jobRollFormCenter {
  position: absolute;
  top: 30%;
  left: 30%;
  right: 30%;
  /* background-color: white; */
  opacity: 0.85;
  width: 30vh;
  height: 30vh;
  margin: auto;
  border-radius: 30px;
  /* box-shadow: 5px 5px 5px 5px gray; */
  background-image: url(../../public/homedesign/images/jobRoll.png);
  background-size: 30vh 30vh;
  background-repeat: no-repeat;
  background-color: rgba(255, 255, 255, 0);
  transition-duration: 0.5s;
  transition-timing-function: ease;
  z-index: 1199;
}
.jobRollFormSide {
  position: absolute;
  top: 10%;
  left: 90%;
  right: 30%;
  /* background-color: white; */
  opacity: 0.85;
  width: 10vh;
  height: 10vh;
  margin: auto;
  border-radius: 30px;
  /* box-shadow: 5px 5px 5px 5px gray; */
  background-image: url(../../public/homedesign/images/jobRoll.png);
  background-size: 10vh 10vh;
  background-repeat: no-repeat;
  background-color: rgba(255, 255, 255, 0);
  transition-duration: 0.5s;
  transition-timing-function: ease;
  z-index: 2000;
}
.openedJobRoll {
  position: absolute;
  top: 5%;
  left: 10%;
  right: 10%;
  /* background-color: white; */
  opacity: 1;
  width: 80vh;
  height: 90vh;
  margin: auto;
  border-radius: 30px;
  /* box-shadow: 5px 5px 5px 5px gray; */
  background-image: url(../../public/homedesign/images/jobinfo.png);
  background-size: 80vh 90vh;
  background-repeat: no-repeat;
  background-color: rgba(255, 255, 255, 0);
  z-index: 1200;
}
.GovoteForm {
  z-index: 2;
  position: absolute;
  top: 10%;
  left: 10%;
  right: 10%;
  /* background-color: white; */
  /* opacity: 0.7; */
  width: 80vh;
  height: 80vh;
  margin: auto;
  border-radius: 30px;
  /* box-shadow: 5px 5px 5px 5px gray; */
  background-image: url(../../public/homedesign/images/jobinfo.png);
  background-size: 80vh 80vh;
  background-repeat: no-repeat;
}
.ingameBackground {
  background-image: url(../../public/homedesign/images/wait_mafia.gif);
  height: 100vh;
  background-size: cover;
  background-repeat: no-repeat;
  /* height: 773.3px; */
}
.dayVoteTitle {
  text-align: center;
  margin-top: 50px;
}
.mafiaImage {
  position: absolute;
  top: 10%;
  left: 20%;
  right: 30%;
  opacity: 1;
  width: 50vh;
  height: 50vh;
  margin: auto;
  border-radius: 30px;
  background-size: 50vh 50vh;
  background-image: url(../../public/homedesign/images/mafia_whiteicon.png);
  background-repeat: no-repeat;
  background-size: 100% 100%;
  margin-bottom: px;
}
.citizenImage {
  position: absolute;
  top: 8%;
  left: 20%;
  right: 30%;
  opacity: 1;
  width: 50vh;
  height: 50vh;
  /* margin: auto; */
  border-radius: 30px;
  background-size: 50vh 50vh;
  background-image: url(../../public/homedesign/images/citizen_whiteicon.png);
  background-repeat: no-repeat;
  background-size: 100% 100%;
  margin-bottom: px;
}
.doctorImage {
  position: absolute;
  top: 8%;
  left: 20%;
  right: 30%;
  opacity: 1;
  width: 50vh;
  height: 50vh;
  margin: auto;
  border-radius: 30px;
  background-size: 50vh 50vh;
  background-image: url(../../public/homedesign/images/doctor_whiteicon.png);
  background-repeat: no-repeat;
  background-size: 100% 100%;
  margin-bottom: px;
}
.headmasterImage {
  position: absolute;
  top: 8%;
  left: 20%;
  right: 30%;
  opacity: 1;
  width: 50vh;
  height: 50vh;
  margin: auto;
  border-radius: 30px;
  background-size: 50vh 50vh;
  background-image: url(../../public/homedesign/images/headmaster_whiteicon.png);
  background-repeat: no-repeat;
  background-size: 100% 100%;
  margin-bottom: px;
}

/* 투표결과 관련 style */
.voteResultDay {
  z-index: 1800;
  box-shadow: rgba(0, 0, 0, 0.5) 0 0 0 9999px;
  margin: auto;
  position: absolute;
  top: 10%;
  left: 10%;
  right: 10%;
  width: 80vh;
  height: 80vh;
  border-radius: 30px;
  /* 낮 투표 결과기 때문에, 이 결과지의 배경은 낮을 보여주는게 좋을 듯. 낮에 이런 일이 있었다~ 이런 느낌 */
  background-image: url(../../public/homedesign/images/day_mafia.gif);
  background-repeat: no-repeat;
  background-size: cover;
}
.voteResultNight {
  z-index: 1800;
  box-shadow: rgba(0, 0, 0, 0.5) 0 0 0 9999px;
  margin: auto;
  position: absolute;
  top: 10%;
  left: 10%;
  right: 10%;
  width: 80vh;
  height: 80vh;
  border-radius: 30px;
  /* 밤 투표 결과기 때문에, 이 결과지의 배경은 밤을 보여주는게 좋을 듯. 밤에 이런 일이 있었다~ 이런 느낌 */
  background-image: url(../../public/homedesign/images/wait_mafia.gif);
  background-repeat: no-repeat;
  background-size: cover;
}
.redChar {
  cursor: pointer;
  position: absolute;
  left: 32%;
  top: 52%;
  height: 35vh;
  width: 35vh;
  background-size: cover;
  background-image: url(../../public/homedesign/images/mafia_red.png);
  transition: all ease 3s;
}
.blueChar {
  cursor: pointer;
  position: absolute;
  left: 32%;
  top: 52%;
  height: 35vh;
  width: 35vh;
  background-size: cover;
  background-image: url(../../public/homedesign/images/mafia_blue.png);
  transition: all ease 3s;
}
.whiteChar {
  cursor: pointer;
  position: absolute;
  left: 32%;
  top: 52%;
  height: 35vh;
  width: 35vh;
  background-size: cover;
  background-image: url(../../public/homedesign/images/mafia_white.png);
  transition: all ease 3s;
}
.blackChar {
  cursor: pointer;
  position: absolute;
  left: 32%;
  top: 52%;
  height: 35vh;
  width: 35vh;
  background-size: cover;
  background-image: url(../../public/homedesign/images/mafia_black.png);
  transition: all ease 3s;
}
.yellowChar {
  cursor: pointer;
  position: absolute;
  left: 32%;
  top: 52%;
  height: 35vh;
  width: 35vh;
  background-size: cover;
  background-image: url(../../public/homedesign/images/mafia_yellow.png);
  transition: all ease 3s;
}
.redCharGone {
  transform: rotate(540deg);
  left: 90%;
  top: 10%;
  height: 0px;
  width: 0px;
}
.blueCharGone {
  transform: rotate(540deg);
  left: 90%;
  top: 10%;
  height: 0px;
  width: 0px;
}
.whiteCharGone {
  transform: rotate(540deg);
  left: 90%;
  top: 10%;
  height: 0px;
  width: 0px;
}
.yellowCharGone {
  transform: rotate(540deg);
  left: 90%;
  top: 10%;
  height: 0px;
  width: 0px;
}
.redCharGone {
  transform: rotate(540deg);
  left: 90%;
  top: 10%;
  height: 0px;
  width: 0px;
}
.deadShadow {
  position: absolute;
  top: 85%;
  left: 40%;
  width: 20vh;
  height: 20vh;
  transition: all ease 3s;
  background-image: url(../../public/homedesign/images/mafia_shadow.png);
  background-size: cover;
  background-repeat: no-repeat;
}
.deadShadowGone {
  top: 70%;
  left: 90%;
  width: 0px;
  height: 0px;
}
.myVideoBackground {
  background-image: url(../../public/homedesign/images/lobby_friend.png);
  background-repeat: no-repeat;
  background-size: 100% 100%;
  text-align: center;
}
.OtherVideoBackground {
  background-image: url(../../public/homedesign/images/ingame_others.jpg);
  background-repeat: no-repeat;
  background-size: 100% 100%;
  text-align: center;
}
.brownColor {
  color: rgb(106, 66, 14);
}
.sendVoteBtn {
  position: absolute;
  background-image: url(../../public/homedesign/images/vote_wood_dot.png);
  background-size: cover;
  background-repeat: no-repeat;
  left: 50%;
}
.blackVoted {
  background-image: url(../../public/homedesign/images/mafia_black.png);
  background-repeat: no-repeat;
  background-size: 100% 100%;
  width: 20px;
  height: 20px;
  text-align: center;
  background-color: black;
}
.whiteVoted {
  background-image: url(../../public/homedesign/images/mafia_white.png);
  background-repeat: no-repeat;
  background-size: 100% 100%;
  width: 20px;
  height: 20px;
  text-align: center;
  background-color: rgb(255, 255, 255);
}
.blueVoted {
  background-image: url(../../public/homedesign/images/mafia_blue.png);
  background-repeat: no-repeat;
  background-size: 100% 100%;
  width: 20px;
  height: 20px;
  text-align: center;
  background-color: rgb(0, 4, 255);
}
.redVoted {
  background-image: url(../../public/homedesign/images/mafia_red.png);
  background-repeat: no-repeat;
  background-size: 100% 100%;
  text-align: center;
  width: 20px;
  height: 20px;
  background-color: rgb(255, 0, 0);
}
.yellowVoted {
  background-image: url(../../public/homedesign/images/mafia_yellow.png);
  background-repeat: no-repeat;
  background-size: 100% 100%;
  text-align: center;
  width: 20px;
  height: 20px;
  background-color: rgb(255, 234, 0);
}
</style>
