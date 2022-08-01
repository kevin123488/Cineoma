<template>
  <div>
    <!-- Header -->
    <header class="w3-row">
      <div class="w3-col m2" id="info-time">
        <ingame-timer id="timer" :time="20" />
        <p id="day">day:</p>
      </div>
      <div class="w3-col m7 border border-secondary px-3" id="info">
        <p>누가 죽었슴, 마피아는 아니였슴</p>
      </div>
      <div class="w3-col m1">
        <button>vote</button>
      </div>
    </header>
  </div>
</template>

<script>
import IngameTimer from './IngameTimer.vue'

export default {
    components: {
        IngameTimer,
    }

}
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