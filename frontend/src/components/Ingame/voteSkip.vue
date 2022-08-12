<template>
<!-- 낮 중에만 활성화 되도록, v-if progress === day -->
  <div class="ingameBackground">

    <!-- 직업확인 두루마리 -->
    <button @click="openJobRoll" :class="{ jobRollFormSide: !isjobRollCenter, jobRollFormCenter: isjobRollCenter }">
      <div class="voteItem">
      </div>
    </button>

    <!-- 직업확인창 -->
    <button v-if="isJobRollOpen" @click="switchJobRoll" class="openedJobRoll">

      <!-- 마피아 -->
      <div v-if="job === 'mafia'">
        <div class="mafiaImage"></div>        
        <div style="width: 65%; margin-left: 120px; margin-top: 390px;">
          <h3 class="">당신의 직업: 마피아</h3>
          <h3 class="">밤마다 한명의 플레이어를 죽일 수 있습니다.</h3>
          <h3 class="">모든 플레이어를 죽이면 승리!</h3>
        </div>  
      </div>  

      <!-- 의사 -->
      <div v-if="job === 'doctor'">
        <div class="doctorImage"></div>  
        <div style="width: 65%; margin-left: 120px; margin-top: 390px;">
          <h3 class="">당신의 직업: 의사</h3>
          <h3 class="">밤마다 한명의 플레이어를 마피아로 부터 보호할 수 있습니다.</h3>
          <h3 class="">마피아를 잡아내면 승리!</h3>
        </div>  
      </div>  

      <!-- 교주 -->
      <div v-if="job === 'headmaster'">
        <div class="headmasterImage"></div>      
        <div style="width: 65%; margin-left: 120px; margin-top: 390px;">
          <h3 class="">당신의 직업: 교주</h3>
          <h3 class="">특정 동작을 수행할 때 마다 신자 카운트가 증가합니다.</h3>
          <h3 class="">다른 플레이어 수 만큼 신자 카운트를 쌓으면 승리!</h3>
        </div>  
      </div>

      <!-- 시민 -->
      <div v-if="job === 'citizen'">
        <div class="citizenImage"></div>
        <div style="width: 65%; margin-left: 120px; margin-top: 120px;">
          <h3 class="">당신의 직업: 시민</h3>
          <h3 class="">당신은 선량한 시민입니다. 다른 사람들과 협력해 마피아를 잡아내세요.</h3>
          <h3 class="">마피아를 잡아내면 승리!</h3>
        </div>  
      </div>

    </button>

  </div>
</template>

<script>
// 프론트에서 정보 받을 uri -> ("/sendMafia/{roomNo}")
// 프론트에서 정보 줄 때 사용할 uri -> ("/receiveMafia")

// 문제점: 1. isSkiped 버튼이 뜨는 타이밍 조절 -> 낮 진입하자마자 isSkiped true 만들기 -> 낮 진입시 정보를 받아야 할듯

export default {
  data() {
    return {
      isjobRollCenter: true,
      isJobRollOpen: false,
      job: 'citizen',
    }
  },
  methods: {
    switchJobRoll() {
      this.isjobRollCenter = false
      this.isJobRollOpen = false
    },
    openJobRoll () {
      if (this.isjobRollCenter) {
        this.isJobRollOpen = true
      } else {
      setTimeout(() => {
        this.isJobRollOpen = true
      }, 700)
      if (!this.isjobRollCenter) {
        this.isjobRollCenter = true
      }      
      }
    }
  },
  computed: {
  },
  watch() { // 인게임 유저의 정보가 바뀔 때마다 실행?
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
/* 결과창 Fade*/
.voteResultFade-enter-active, .voteResultFade-leave-active {
  transition: opacity 1s;
}
.voteResultFade-enter-from, .voteResultFade-leave-to {
  opacity: 0;
}
.ingameDaySkipBtn {
  position: absolute;
  top: 70%;
  left: 70%;
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
  opacity: 0.7;
  width: 30vh;
  height: 30vh;
  margin: auto;
  border-radius: 30px;
  /* box-shadow: 5px 5px 5px 5px gray; */
  background-image: url(../../../public/homedesign/images/jobRoll.png);
  background-size: 30vh 30vh;
  background-repeat: no-repeat;
  background-color: rgba( 255, 255, 255, 0 );
  transition-duration: 0.5s;
  transition-timing-function: ease
}
.jobRollFormSide {
  position: absolute;
  top: 10%;
  left: 90%;
  right: 30%;
  /* background-color: white; */
  opacity: 0.7;
  width: 10vh;
  height: 10vh;
  margin: auto;
  border-radius: 30px;
  /* box-shadow: 5px 5px 5px 5px gray; */
  background-image: url(../../../public/homedesign/images/jobRoll.png);
  background-size: 10vh 10vh;
  background-repeat: no-repeat;
  background-color: rgba( 255, 255, 255, 0 );
  transition-duration: 0.5s;
  transition-timing-function: ease
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
  background-image: url(../../../public/homedesign/images/jobinfo.png);
  background-size: 80vh 90vh;
  background-repeat: no-repeat;
  background-color: rgba( 255, 255, 255, 0 );
}
.voteForm {
  position: absolute;
  top: 10%;
  left: 10%;
  right: 10%;
  /* background-color: white; */
  opacity: 0.7;
  width: 80vh;
  height: 80vh;
  margin: auto;
  border-radius: 30px;
  /* box-shadow: 5px 5px 5px 5px gray; */
  background-image: url(../../../public/homedesign/images/vote_paper.png);
  background-size: 80vh 80vh;
  background-repeat: no-repeat;
}
.ingameBackground {
  background-image: url(../../../public/homedesign/images/wait_mafia.gif);
  height: 100vh;
  background-size: cover;
  background-repeat: no-repeat;
  /* height: 773.3px; */
}
/* .mafiaImage {
  position: absolute;
  top: 20%;
  left: 20%;
  right: 30%;
  opacity: 1;
  width: 50vh;
  height: 50vh;
  margin: auto;
  border-radius: 30px;
  background-size: 50vh 50vh;
  background-image: url(../../../public/homedesign/images/mafia_whiteicon.png);
  background-repeat : no-repeat;
  background-size: 100% 100%;
  margin-bottom:px;  
}
.citizenImage {
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
  background-image: url(../../../public/homedesign/images/citizen_whiteicon.png);
  background-repeat : no-repeat;
  background-size: 100% 100%;
  margin-bottom:px;
}
.doctorImage {
  position: absolute;
  top: 0%;
  left: 20%;
  right: 30%;
  opacity: 1;
  width: 50vh;
  height: 50vh;
  margin: auto;
  border-radius: 30px;
  background-size: 50vh 50vh;  
  background-image: url(../../../public/homedesign/images/doctor_whiteicon.png);
  background-repeat : no-repeat;
  background-size: 100% 100%;
  margin-bottom:px;
}
.headmasterImage {
  position: absolute;
  top: 0%;
  left: 20%;
  right: 30%;
  opacity: 1;
  width: 50vh;
  height: 50vh;
  margin: auto;
  border-radius: 30px;
  background-size: 50vh 50vh;
  background-image: url(../../../public/homedesign/images/headmaster_whiteicon.png);
  background-repeat : no-repeat;
  background-size: 100% 100%;
  margin-bottom:px;  
} */
</style> 