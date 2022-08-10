<template>
  <div class="w3-main mx-5">
    <ingame-nav class="mb-3"></ingame-nav>
    <!-- 유저 화면 -->
    <div class="w3-row">
      <div class="w3-col m8">
        <div class="w3-row">
          <user-video
          v-for="sub in subscribers"
          :key="sub.stream.connection.connectionId"
          :stream-manager="sub">
          </user-video>
          <!-- <user-video
            :stream-manager="subscribers[0]"
            :gameInfo="gameInfo[0]"
            @click="doVote(gameInfo[0])"
          />
          <user-video
            :stream-manager="subscribers[1]"
            :gameInfo="gameInfo[1]"
            @click="doVote(gameInfo[1])"
          />
          <user-video
            :stream-manager="subscribers[2]"
            :gameInfo="gameInfo[2]"
            @click="doVote(gameInfo[2])"
          />
          <user-video
            :stream-manager="subscribers[3]"
            :gameInfo="gameInfo[3]"
            @click="doVote(gameInfo[3])"
          /> -->
        </div>
      </div>

      <div class="w3-col m4">
        <ul class="mx-2 my-2 w3-container border border-secondary">
          <li>직업</li>
          <li>미션</li>
        </ul>
        <user-video
          :stream-manager="publisher"
          :gameInfo="myInfo"
        />
      </div>
    </div>
  </div>
</template>
publisher
sub
<script>
const memberStore = "memberStore";
import { mapState } from "vuex";
import IngameNav from "@/components/Ingame/IngameNav.vue";

import Stomp from 'webstomp-client'
import SockJS from 'sockjs-client'
import axios from "axios";

import { OpenVidu } from "openvidu-browser";
import UserVideo from "@/components/Ingame/UserVideo.vue";

import { mapGetters, mapActions } from 'vuex'
const roomdataStore = "roomdataStore"

axios.defaults.headers.post["Content-Type"] = "application/json";

const OPENVIDU_SERVER_URL = "https://i7e107.p.ssafy.io:443";
const OPENVIDU_SERVER_SECRET = "MY_SECRET";

export default {
  name: "IngameView",
  components: {
    IngameNav,
    UserVideo,
  },
  data() {
    return {
      // OpenVidu objects
      OV: undefined,
      session: undefined,
      publisher: undefined,
      subscribers: [],

      // Join form
      mySessionId: this.$route.roomnumber,
      myUserName: "",

      // 게임정보
      gameInfos: [],
      myInfo: {},
      progress: {
        isDay: true,
        isVoteDay: false,
        isVoteDayResult: false,
        isNight: false,
        isNightResult: false,
      },
      missionWin: false,

      // 투표
      voteNo: 1, // 투표권
    };
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
    ...mapGetters(roomdataStore, [
      'roomNo',
      'roomTitle',
      'isCaptain',
      'isConnected',
    ]),
    ...mapGetters(memberStore, [
      'isLogin',
    ]),
    ...mapActions(roomdataStore, [
      'deleteRoom',
      'enterRoom',
      'saveRoomTitle',
      'saveIsCaptain',
      'saveIsConnected',
    ])           
  },
  created() {
    this.mySessionId = "a";
    this.myUserName = this.userInfo.nickname;
    this.myUserId = this.userInfo.id;
    this.joinSession();
    this.myInfo = {
      id: this.userInfo.id,
      nickname: this.userInfo.nickname,
      isAlive: true, // 살았나 죽었나
      color: '', //색깔
      job: '', // 직업
      voted: [],
    };
    // this.connect()
  },
  mounted() {
    console.log(this.isConnected)
  },
  methods: {
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
        this.gameInfos.push({
          id: userData[0],
          nickname: userData[1],
          isAlive: true, // 살았나 죽었나
          color: '', //색깔
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
              publishAudio: true, // Whether you want to start publishing with your audio unmuted or not
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

          // 직업, 색깔 설정
          this.stompClient.subscribe(`/sendMafia/${this.mySessionId}/${this.userInfo.id}`, res => {
            console.log('구독으로 받은 게임 정보입니다.', res.body);
            const data = JSON.parse(res.body);

            this.myInfo.job = data.job;
            data.joinUsers.forEach(joinUser => {
              this.gameInfos.forEach(gameInfo => {
                if (joinUser.id === gameInfo.id) {
                  gameInfo.color = joinUser.color;
                }
              })
            });
          });

          this.stompClient.subscribe(`/sendMafia/${this.mySessionId}`, res => {
            console.log('구독으로 받은 게임 정보입니다.', res.body);
            const data = JSON.parse(res.body);

            // 낮 투표
            if (data.progress === 'voteDay') {
              this.gameInfos.forEach(gameInfo => {
                // 내가 투표 받을 때
                if (this.myInfo.id === data.votedId) {
                  this.myInfo.push(data.id)
                }
                // 다른 사람이 투표 받을 때
                else if (gameInfo.id === data.votedId) {
                  gameInfo.voted.push(data.id)
                }
              })
            }

            // 낮 투표 결과
            if (data.progress === 'voteDayFinish') {
              // 승자 결정
              if (data.winJob !== '') {
                this.$router.push({ name: 'final', params: { roomnumber: this.mySessionId } });
              }
              // 죽은 사람이 없을 때
              else if (data.id === '') {
                this.progress.isVoteDay = false;
                this.progress.isVoteDayResult = true;
              }
              // 죽은 사람이 있을 때
              else {
                this.gameInfos.forEach(gameInfo => {
                  // 자신이 죽었을 때
                  if (this.myInfo.id === data.id) {
                    this.myInfo.isAlive = false
                  }
                  // 다른 사람이 죽었을 때
                  else if (gameInfo.id === data.id) {
                    gameInfo.isAlive = false
                  }
                })
              }
            }

            if (data.progress === 'voteNight') {
              console.log(data)
            }
          });
        },

        error => {
          // 소켓 연결 실패
          console.log('소켓 연결 실패', error);
          this.connected = false;
        }
      );        
    },

    sendVote(voteId) {
      if (this.stompClient && this.stompClient.connected) {
        const msg = { 
            progress: 'voteDay',
            roomNo: this.mySessionId,
            id: this.myUserId,
            nickname: this.userInfo.nickname,
            job: this.myInfo.job,
            vote: voteId,
            ifWin: this.missionWin
        }
        if (this.progress.isNight) {
          msg.progress = 'voteNight'
        }
        console.log(msg);
        this.stompClient.send('/receiveMafia', JSON.stringify(msg), {});
        }
      },

    // 투표
    doVote(info) {
      if (this.voteNo === 1 && (this.progress.isVoteDay || this.progress.isNight)) {
        this.sendVote(info.id);
        this.voteNo = 0;
        console.log(`투표한 id ${info.id}`);
      }
    },
  }
}
</script>

<style></style>
