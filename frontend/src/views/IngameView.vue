<template>
  <div class="w3-main mx-5">
    <ingame-nav class="mb-3"></ingame-nav>
    <!-- 유저 화면 -->
    <div class="w3-row">
      <div class="w3-col m8">
          <div class="w3-row">
            <user-video v-for="sub in subscribers"
            :key="sub.stream.connection.connectionId"
            :stream-manager="sub"
            class="mx-2 my-2 w3-container border border-secondary w3-col m6"
            id="video-container"
            />
          </div>
      </div>

      <div class="w3-col m4">
        <ul class="mx-2 my-2 w3-container border border-secondary">
          <li>직업</li>
          <li>미션</li>
        </ul>
        <user-video :stream-manager="publisher"
        class="mx-2 my-2 w3-container border border-secondary"
        />
      </div>
    </div>
  </div>
</template>

<script>
import IngameNav from '@/components/Ingame/IngameNav.vue'
import axios from 'axios'
import { OpenVidu } from 'openvidu-browser'
import UserVideo from '@/components/Ingame/UserVideo.vue'
import { mapGetters, mapActions } from 'vuex'
const roomdataStore = "roomdataStore"
const memberStore = "memberStore"

axios.defaults.headers.post['Content-Type'] = 'application/json'

const OPENVIDU_SERVER_URL = "https://i7e107.p.ssafy.io";
const OPENVIDU_SERVER_SECRET = "E107";

  export default {
    name: 'IngameView',
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
        mySessionId: '',
        myUserName: '',

        // 진행상황
        time: 'day'
      }
    },
  computed: {
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
      this.mySessionId = this.$route.params.roomnumber;
      this.myUserName = 'Participant' + Math.floor(Math.random() * 100);
      this.joinSession();
    },
    mounted() {
      console.log(this.isConnected)
    },
    methods: {
      joinSession () {
        // --- Get an OpenVidu object ---
        this.OV = new OpenVidu();

        // --- Init a session ---
        this.session = this.OV.initSession();

        // --- Specify the actions when events take place in the session ---

        // On every new Stream received...
        this.session.on('streamCreated', ({ stream }) => {
          const subscriber = this.session.subscribe(stream);
          this.subscribers.push(subscriber);
        });

        // On every Stream destroyed...
        this.session.on('streamDestroyed', ({ stream }) => {
          const index = this.subscribers.indexOf(stream.streamManager, 0);
          if (index >= 0) {
            this.subscribers.splice(index, 1);
          }
        });

        // On every asynchronous exception...
        this.session.on('exception', ({ exception }) => {
          console.warn(exception);
        });

        // --- Connect to the session with a valid user token ---

        // 'getToken' method is simulating what your server-side should do.
        // 'token' parameter should be retrieved and returned by your own backend
        this.getToken(this.mySessionId).then(token => {
          this.session.connect(token, { clientData: this.myUserName })
            .then(() => {

              // --- Get your own camera stream with the desired properties ---

              let publisher = this.OV.initPublisher(undefined, {
                audioSource: undefined, // The source of audio. If undefined default microphone
                videoSource: undefined, // The source of video. If undefined default webcam
                publishAudio: true,  	// Whether you want to start publishing with your audio unmuted or not
                publishVideo: true,  	// Whether you want to start publishing with your video enabled or not
                resolution: '640x480',  // The resolution of your video
                frameRate: 30,			// The frame rate of your video
                insertMode: 'APPEND',	// How the video is inserted in the target element 'video-container'
                mirror: false       	// Whether to mirror your local video or not
              });

              this.mainStreamManager = publisher;
              this.publisher = publisher;

              // --- Publish your stream ---

              this.session.publish(this.publisher);
            })
            .catch(error => {
              console.log('There was an error connecting to the session:', error.code, error.message);
            });
        });

        window.addEventListener('beforeunload', this.leaveSession)
      },

      leaveSession () {
        // --- Leave the session by calling 'disconnect' method over the Session object ---
        if (this.session) this.session.disconnect();

        this.session = undefined;
        this.mainStreamManager = undefined;
        this.publisher = undefined;
        this.subscribers = [];
        this.OV = undefined;

        window.removeEventListener('beforeunload', this.leaveSession);
      },

      updateMainVideoStreamManager (stream) {
        if (this.mainStreamManager === stream) return;
        this.mainStreamManager = stream;
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

      getToken (mySessionId) {
        return this.createSession(mySessionId).then(sessionId => this.createToken(sessionId));
      },

      // See https://docs.openvidu.io/en/stable/reference-docs/REST-API/#post-session
      createSession (sessionId) {
        return new Promise((resolve, reject) => {
          axios
            .post(`${OPENVIDU_SERVER_URL}/openvidu/api/sessions`, JSON.stringify({
              customSessionId: sessionId,
            }), {
              auth: {
                username: 'OPENVIDUAPP',
                password: OPENVIDU_SERVER_SECRET,
              },
            })
            .then(response => response.data)
            .then(data => resolve(data.id))
            .catch(error => {
              if (error.response.status === 409) {
                resolve(sessionId);
              } else {
                console.warn(`No connection to OpenVidu Server. This may be a certificate error at ${OPENVIDU_SERVER_URL}`);
                if (window.confirm(`No connection to OpenVidu Server. This may be a certificate error at ${OPENVIDU_SERVER_URL}\n\nClick OK to navigate and accept it. If no certificate warning is shown, then check that your OpenVidu Server is up and running at "${OPENVIDU_SERVER_URL}"`)) {
                  location.assign(`${OPENVIDU_SERVER_URL}/accept-certificate`);
                }
                reject(error.response);
              }
            });
        });
      },

      // See https://docs.openvidu.io/en/stable/reference-docs/REST-API/#post-connection
      createToken (sessionId) {
        return new Promise((resolve, reject) => {
          axios
            .post(`${OPENVIDU_SERVER_URL}/openvidu/api/sessions/${sessionId}/connection`, {}, {
              auth: {
                username: 'OPENVIDUAPP',
                password: OPENVIDU_SERVER_SECRET,
              },
            })
            .then(response => response.data)
            .then(data => resolve(data.token))
            .catch(error => reject(error.response));
        });
      },

      // 
      // 
      // 인게임 보내는 
      sendVote() {
        console.log("Send message:" + this.message);
        if (this.stompClient && this.stompClient.connected) {
          const msg = { 
            roomNo: this.roomNo,
            nickName: this.nickName,
            content: this.message 
          };
          console.log(msg);
          this.stompClient.send('/receiveChat', JSON.stringify(msg), {});
        }
      },
    },
  }

</script>

<style>
#video-container {
  width: 45%;
}
</style>