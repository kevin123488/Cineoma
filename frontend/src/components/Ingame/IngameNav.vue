<template>
  <div>
    <!-- Header -->
    <header class="w3-row">
      <div class="w3-col m2" id="info-time">
        <ingame-timer id="timer" :time="count" />
        <p id="day">day: {{ progress.nowDay }}</p>
      </div>
      <div class="w3-col m7 border border-secondary px-3" id="info">
        <p v-show="progress.isDay">아침이 밝았습니다.</p>
        <p v-show="progress.isVoteDay">투표하세요.</p>
        <p v-show="progress.isVoteDayResult">투표 결과.</p>
        <p v-show="progress.isNight">밤.</p>
        <p v-show="progress.isNightResult">밤 결과.</p>
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
  },
  components: {
    IngameTimer,
  },
};
</script>

<style>
#info-time {
  text-align: center;
  height: 130px;
}

#timer {
  font-size: 35px;
}

#day {
  font-size: 20px;
}

#info {
  height: 125px;
}
</style>
