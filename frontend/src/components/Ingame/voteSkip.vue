<template>
<!-- 낮 중에만 활성화 되도록, v-if progress === day -->
  <div v-if="showModal" class="skipModal">
    <!-- 클릭할때마다 요소가 생성되게 해야 함. 어떻게? 생성자 ㄱ? 일단 고민 -->
      <h1>유저 {{ voteUser }} 님께서 스킵에 투표하셨습니다!</h1>
  </div>
  <button v-if="progress.isDay" @click="sendSkip">
    낮 스킵 후 투표로 넘어가기
    지금 낮임
  </button>
  <div v-if="progress.isVoteDay">지금 낮 투표임</div>
  <div v-if="progress.isVoteDayResult">지금 낮 투표 결과임</div>
  <div v-if="progress.isNight">지금 밤임</div>
  <div v-if="progress.isNightResult">지금 밤 투표 결과임</div>
</template>

<script>
// 프론트에서 정보 받을 uri -> ("/sendMafia/{roomNo}")
// 프론트에서 정보 줄 때 사용할 uri -> ("/receiveMafia")
const ingameStore = "ingameStore";
import { mapState } from 'vuex';
import Stomp from 'webstomp-client'
import SockJS from 'sockjs-client'

// 문제점: 1. isSkiped 버튼이 뜨는 타이밍 조절 -> 낮 진입하자마자 isSkiped true 만들기 -> 낮 진입시 정보를 받아야 할듯

export default {
  data() {
    return {
      id: "", // id정보를 data에서 갖고있을 수 있음
      color: "", // color 정보를 data에서 갖고있을 수 있음
      // 낮 진입하자마자 정보를 받나? 일단 낮에 받는 정보는 투표 스킵시 오가는 정보밖에 없음. 즉 낮임을 알게 할 수 있는 요소가 없음
      userInfo: {
        id: "", // 임시 양식
      },
      isSkiped: false, // 낮 시간이 100초 지난 시점에 이 값이 계속 false라면? sendSkip 실행, 밤 투표 결과에서 낮으로 넘어올 때 이 값을 false로 맞춰줘야 함
      voteUser: "",
      showModal: false, // 얘 바꾸는 타이밍 나중에 확인 -> 얘는 괜찮은듯
      progress: {
        isDay: true, // 시작화면이 얘니까 일단 얘를 true로
        isVoteDay: false,
        isVoteDayResult: false,
        isNight: false,
        isNightResult: false,
      },
      clearId: "",
    }
  },
  computed: {
    ...mapState(ingameStore, ["user"]), // ingameStore에 저장되어 있는, 현재 게임에 접속중인 유저. 이름 바뀔 수 있음
  },
  methods: {
    dayToDayVote() { // 낮에서 낮 투표로
      if (this.isSkiped === false) {
        this.sendSkip();
      }
      this.progress.isDay = false;
      this.progress.isVoteDay = true;
      this.clearId = setTimeout(() => {
        this.voteToDayResult();
      }, 15000); // 투표시간 타이머
    },
    voteToDayResult() { // 낮 투표에서 낮 투표 결과로
      this.progress.isVoteDay = false;
      this.progress.isVoteDayResult = true;
      setTimeout(() => {
        this.dayResultToNightVote();
      }, 5000); // 낮 투표결과 보여줄 타이머
    },
    dayResultToNightVote() { // 낮 투표 결과에서 밤 투표로
      this.progress.isVoteDayResult = false;
      this.progress.isNight = true;
      setTimeout(() => {
        this.nightVoteToNightResult();
      }, 20000); // 밤 투표 진행할 타이머
    },
    nightVoteToNightResult() { // 밤 투표에서 밤 투표 결과로
      this.progress.isNight = false;
      this.progress.isNightResult = true;
      setTimeout(() => {
        this.nightResultToDay();
      }, 5000); // 밤 결과 보여줄 타이머
    },
    nightResultToDay() { // 밤 투표 결과에서 낮으로
      this.progress.isNightResult = false;
      this.progress.isDay = true;
      this.isSkiped = false;
      setTimeout(() => {
        this.dayToDayVote();
      }, 100000) // 낮 진행시킬 타이머
    },
    connect() {
      const serverURL = "http://localhost:8080/roomSocket"
      let socket = new SockJS(serverURL);
      this.stompClient = Stomp.over(socket);
      this.stompClient.connect(
        {},
        frame => {
          this.connected = true;
          console.log("소켓 연결 성공", frame);

          this.stompClient.subscribe(`/sendMafia/${this.roomNo}/${this.userInfo.id}`, res => {
            if (res.body.progress === day) {
              this.color = res.body.color;
              this.voteUser = res.body.nickname;
              this.showModal = true;

              setTimeout(() => {
                this.showModal = false;
              }, 1000);

              // setTimeout(() => {
              //   if (this.isSkiped === false) {
              //     this.dayToVote();
              //   }
              // }, 100000)

              if (res.body.ifSkip === true) {
                this.progress.isDay = false;
                this.progress.isVoteDay = true;
                clearTimeout(this.clearId);
              }
            } else if (res.body.progress === voteDay) {

            } else if (res.body.progress === voteDayFinish) {

            } else if (res.body.progress === voteNight) {

            }
          })
        }
      )
    },
    sendSkip() {
      if (this.stompClient && this.stompClient.connected) {
          const msg = {
              progress: day,
              roomNo: this.roomNo,
              id: this.userInfo.id,
          }
          this.stompClient.send('/receiveChat', JSON.stringify(msg), {});
          this.progress.isDay = false; // 버튼 누르면 정보 담아서 보냄 -> 버튼 숨김
          this.isSkiped = true;
      }
    },
    // 아래의 getVoteSkip 함수는 goDay 함수 안에서 실행될 것
    // getVoteSkip() {
    //   this.stompClient.subscribe(`/sendMafia/${this.roomNo}/${this.userInfo.id}`, res => { // id와 roomNo를 받아오는 부분이 어떻게 될지는 추후 확인해봐야 함
    //     if (res.body.progress === day) { // res 형식 확인 후 조율 필요
    //       this.color = res.body.color;
    //       this.voteUser = res.body.nickname;
    //       this.showModal = true;
    //       // 클릭한 사람의 정보를 모달로 1초간 띄워줄 예정
    //       setTimeout(() => {
    //         this.showModal = false;
    //       }, 1000);
    //       // 스킵 가능한 상황일 경우 스킵하는 로직 ㄱ
    //       if (res.body.ifSkip === true) {
    //         this.progress.isDay = false;
    //         this.progress.isVoteDay = true;
    //         // 여기에서 함수 호출하면 될 것 같음(낮 투표 로직 포함된 함수)
    //       } else {
    //         console.log("뭔가 바뀌진 않을 것 같다")
    //       }
    //     }
    //   })
    // },
    
    // 아래의 goDay 함수는 낮을 진행시킬 함수
    // connect 함수의 어떤 함수 안에서 호출될 함수 (아마 밤 투표 함수 안에서 호출될 듯)
    // goDay가 호출되면 우선 getVoteSkip 함수가 실행됨
    // 만약 getVoteSkip 함수에서 ifSkip === true를 만족시키지 못했다면? -> else 부분이 실행되고 아무것도 바뀌지 않겠지
    // 어쨌든 goDay() 함수의 타이머는 돌아가고, 그 타이머가 다 돌면 sendSkip 함수가 실행됨 -> skip 조건 완료되고 if문 안의 무언가(낮 투표로 넘어가는 함수)가 실행됨
    // 문제점: 이 방식대로 할 경우, 처음 goDay가 실행될 때 1번만 getVoteSkip 함수가 실행됨
    // 이 소켓 구독이라는게 한번 실행되면 계속 받아보는건지, 아님 연락이 올때마다 확인할 수 있게 connect 함수 안에 넣어둬야 하는건지를 잘 모르겠음
    // 이거 안되면 다른 방법 생각해 봐야할듯
    // goDay() {
    //   // this.getVoteSkip(); // 얘를 계속해서 실행시켜야 할 것 같기도 함 -> setInterval 사용
    //   // 혹은 위의 함수를 풀어서 이 안에 적어두기
    //   this.stompClient.subscribe(`/sendMafia/${this.roomNo}/${this.userInfo.id}`, res => {
    //     if (res.body.progress === day) {
    //       this.color = res.body.color;
    //       this.voteUser = res.body.nickname;
    //       this.showModal = true;
    //       // 스킵 신청한 사람의 닉네임을 1초간 띄워 줄 예정
    //       setTimeout(() => {
    //         this.showModal = false;
    //       }, 1000);
    //       // 스킵 가능한 경우 스킵 ㄱ
    //       if (res.body.ifSkip === true) {
    //         this.progress.isDay = false;
    //         this.progress.isVoteDay = true;
    //         // 그리고 여기서 함수 실행(낮 투표 진행하는 함수)
    //       } else {
    //         console.log("변화가 일어날만한 요소가 없음");
    //       }
    //     }
    //   })

    //   setTimeout(() => {
    //     if (this.isSkiped === false) {
    //       // 낮 시간 100초 기다렸다 아직 isskiped 안눌린 상태면 무효표(스킵표로 작동) 던지게 하기
    //       this.sendSkip();
    //     }
    //   }, 100000)
    // },
  },
}
</script>

<style>
.skipModal {
  margin: auto;
  position: absolute;
  width: 70%;
  height: 70%;
}

</style>