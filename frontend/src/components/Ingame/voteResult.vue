<template>
  <div :class="{'ingameNight': progress.isNight, 'ingameDay': progress.isDay, 'ingameDayVote': progress.isVoteDay, 'ingameDayVoteResult': progress.isVoteDayResult, 'ingameNightResult': progress.isNightResult}">
    <div>투표결과 확인 페이지</div>
    <br>
    <br>
    <br>
    <button @click="transResultPage">바껴라!!</button>

    <div :class="{'voteResultDay': progress.isVoteDayResult, 'voteResultNight': progress.isNightResult}">
      <!-- <h1 v-if="!!deadColor && progress.isVoteDayResult" style="text-align: center; color: black;">투표 결과<br>{{ whoIsGone }}<br>퇴출되었습니다!</h1> -->
      <h1 v-if="!!deadColor && progress.isVoteDayResult" style="text-align: center; color: black;">{{ showingMsg }}</h1>
      <h1 v-if="!!deadColor && progress.isNightResult" style="text-align: center; color: white;">밤 사이에<br>{{ whoIsGone }}<br>사망하였습니다,,,</h1>
      <h1 v-if="!deadColor">아무 일 없이 지나감</h1>
      <div @click="getOut" :class="{
        'redChar': deadColor === 'red',
        'redCharGone': deadColor === 'red' && isGone,
        'blueChar': deadColor === 'blue',
        'blueCharGone': deadColor === 'blue' && isGone,
        'whiteChar': deadColor === 'white',
        'whiteCharGone': deadColor === 'white' && isGone,
        'blackChar': deadColor === 'black',
        'blackCharGone': deadColor === 'black' && isGone,
        'yellowChar': deadColor === 'yellow',
        'yellowCharGone': deadColor === 'yellow' && isGone
        }"></div>
        <div v-if="!!deadColor" :class="{
          'deadShadow': true,
          'deadShadowGone': isGone,
          }"></div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      progress: {
        isVoteDayResult: true,
        isNightResult: false,
      },
      deadColor: "", // 투표 결과로 죽은 사람이 있다면? 그 결과로 오는 id값을 gameInfos의 값들과 비교한 후 해당 유저의 색 정보를 이 값에 넣어두도록 하자.
      // 없으면? 얘 ""로 되어있을 것, ""일 경우 보여줄 부분도 고려하자. 장면 바뀔 때 초기화 하기
      isGone: false, // 투표 결과 화면에서 다음 날로 넘어갈 때 false로 맞춰주는거 잊지 말자
      whoIsGone: "임시닉네임", // 투표 결과 혹은 밤 중 죽은 사람의 닉네임을 여기 넣자. 장면 바뀔 때 초기화 하기
      msgCnt: 0, // 투표 결과 타이핑 효과에 필요
      showingMsg: "", // 투표 결과로 보여줄 메시지
      stopCnt: 0, // 낮 투표 결과 타이핑 효과의 setInterval을 멈추기 위한 값
      stopCnt2: 0, // 밤 투표 결과 타이핑 효과의 setInterval을 멈추기 위한 값
    }
  },
  created() {
    this.deadColor = 'red';
    this.stopCnt = setInterval(this.typeEffect, 150); // 인게임에 이식할 땐 낮 투표 상황에서 투표 결과 상황으로 넘어가지는 순간에 실행하면 됨
    // this.stopCnt2 = setInterval(this.typeEffect2, 150); // 인게임에 이식할 땐 밤 투표 상황에서 투표 결과 상황으로 넘어가지는 순간에 실행하면 됨
  },
  methods: {
    typeEffect2() {
      this.msgCnt += 1;
      this.showingMsg = `밤 사이에 ${ this.whoIsGone } 님이 사망하였습니다,,,`.slice(0, this.msgCnt);
      if (this.msgCnt === 50) {
        clearInterval(this.stopCnt2);
        this.stopCnt2 = 0;
      }
    },
    typeEffect() {
      this.msgCnt += 1;
      this.showingMsg = `투표 결과 ${ this.whoIsGone } 님이 퇴출되었습니다!`.slice(0, this.msgCnt);
      console.log("hi")
      if (this.msgCnt === 50) {
        clearInterval(this.stopCnt);
        this.stopCnt = 0;
      }
    },
    getOut() {
      if (this.deadColor === "red") {
        this.isGone = true;
      }
    },
    transResultPage() {
      this.progress.isVoteDayResult = !this.progress.isVoteDayResult;
      this.progress.isNightResult = !this.progress.isNightResult;
      this.isGone = false;
      this.msgCnt = 0;
    },
    redToBlue() {
      this.deadColor = 'blue';
      setTimeout(() => {
        this.blueToRed();
      }, 10000);
    },
    blueToRed() {
      this.deadColor = 'red';
      setTimeout(() => {
        this.redToBlue();
      }, 10000);
    },
  },
}
</script>

<style>
.voteResultDay {
  z-index: 1800;
  box-shadow : rgba(0,0,0,0.5) 0 0 0 9999px;
  margin: auto;
  position: absolute;
  top: 10%;
  left: 10%;
  right: 10%;
  width: 80vh;
  height: 80vh;
  border-radius: 30px;
  /* 낮 투표 결과기 때문에, 이 결과지의 배경은 낮을 보여주는게 좋을 듯. 낮에 이런 일이 있었다~ 이런 느낌 */
  background-image: url(../../../public/homedesign/images/day_mafia.gif);
  background-repeat: no-repeat;
  background-size: cover;
}
.voteResultNight {
  z-index: 1800;
  box-shadow : rgba(0,0,0,0.5) 0 0 0 9999px;
  margin: auto;
  position: absolute;
  top: 10%;
  left: 10%;
  right: 10%;
  width: 80vh;
  height: 80vh;
  border-radius: 30px;
  /* 밤 투표 결과기 때문에, 이 결과지의 배경은 밤을 보여주는게 좋을 듯. 밤에 이런 일이 있었다~ 이런 느낌 */
  background-image: url(../../../public/homedesign/images/wait_mafia.gif);
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
  background-image: url(../../../public/homedesign/images/mafia_red.png);
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
  background-image: url(../../../public/homedesign/images/mafia_blue.png);
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
  background-image: url(../../../public/homedesign/images/mafia_white.png);
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
  background-image: url(../../../public/homedesign/images/mafia_black.png);
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
  background-image: url(../../../public/homedesign/images/mafia_yellow.png);
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
  background-image: url(../../../public/homedesign/images/mafia_shadow.png);
  background-size: cover;
  background-repeat: no-repeat;
}
.deadShadowGone {
  top: 70%;
  left: 90%;
  width: 0px;
  height: 0px;
}
</style>