<template>
  <div>
    <!-- Header -->
    <header class="w3-row">
      <div class="w3-col m2" id="info-time">
        <ingame-timer id="timer" :time="count" />
        <p id="day">day: {{ progress.nowDay }}</p>
      </div>
      <div class="w3-col m7 border border-secondary px-3" id="info">
        <p v-show="progress.nowDay === 0 && myInfo.job === ''">
          곧 게임이 시작됩니다.
        </p>
        <p v-show="progress.nowDay === 0 && myInfo.job !== ''">
          두루마리를 열어 직업을 확인하세요.
        </p>
        <p v-show="progress.nowDay !== 0 && progress.isDay">
          아침이 밝았습니다.
        </p>
        <p v-show="progress.isVoteDay">낮이 되었습니다. 마피아를 찾으세요.</p>
        <p v-show="progress.isVoteDayResult">
          {{ progress.nowDay }}번째 날 낮의 투표 결과입니다.
        </p>
        <p v-show="progress.isNight">밤이 되었습니다.</p>
        <p v-show="progress.isNightResult">
          {{ progress.nowDay }}번째 날 새벽입니다.
        </p>
      </div>
      <div class="w3-col m1">
        <button>vote</button>
      </div>
    </header>
  </div>
</template>

<script>
import IngameTimer from "./IngameTimer.vue";

export default {
  data() {
    return {
      ingameNow: "아침",
    };
  },
  props: {
    count: Object,
    progress: Object,
    myInfo: Object,
  },
  components: {
    IngameTimer,
  },
};
</script>

<style>
#info-time {
  text-align: center;
  height: 115px;
}

#timer {
  font-size: 30px;
}

#day {
  font-size: 20px;
}

#info {
  height: 115px;
}
</style>
