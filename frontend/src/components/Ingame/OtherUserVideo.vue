<template>
  <div v-if="streamManager" class="brownColor">
    <div :class="{
      'userCharBlack': gameInfo.color === 'black',
      'userCharWhite': gameInfo.color === 'white',
      'userCharRed': gameInfo.color === 'red',
      'userCharBlue': gameInfo.color === 'blue',
      'userCharYellow': gameInfo.color === 'yellow',
      }">
    </div>
    <div v-if="gameInfo.isAlive === true" class="checkAlive"></div>
    <!-- <div class="checkAlive"></div> -->
    <div v-if="gameInfo.isAlive === false" class="checkDead"></div>
    <!-- <div class="checkDead"></div> -->
    <h3 style="padding-top: 18px; margin-top: 20px;"><b>{{ gameInfo.nickname }}</b></h3>
    <ov-video :stream-manager="streamManager" style="border-radius: 10px;" />
  </div>
</template>

<script>
import OvVideo from "./OvVideo";

export default {
  name: "UserVideo",
  data() {
    return {
      votedInfos: [],
    };
  },

  components: {
    OvVideo,
  },

  props: {
    streamManager: Object,
    gameInfo: Object,
  },

  watch: {
    gameInfo() {
      this.votedInfos = this.gameInfo.voted;
    },
  },

  computed: {
    clientData() {
      const { clientData } = this.getConnectionData();
      return clientData;
    },
  },

  methods: {
    getConnectionData() {
      const { connection } = this.streamManager.stream;
      return JSON.parse(connection.data);
    },
  },
};
</script>

<style>
#post {
  position: relative;
}
.checkDead {
  background-image: url(../../../public/homedesign/images/check_circle.png);
  background-repeat : no-repeat;
  background-size: 100% 100%;  
  position: absolute;
  left: 16%;
  top: 16%;
  width: 30%;
  height: 15%;  
}
.checkAlive {
  background-image: url(../../../public/homedesign/images/check_circle.png);
  background-repeat : no-repeat;
  background-size: 100% 100%;  
  position: absolute;
  left: 54%;
  top: 16%;
  width: 30%;
  height: 15%;
}

/* 다른유저 카메라 근처에 띄워질 캐릭터 그림 */
.userCharBlack {
  position: absolute;
  background-image: url(../../../public/homedesign/images/mafia_black.png);
  background-size: cover;
  background-repeat: no-repeat;
  height: 10vh;
  width: 10vh;
  top: 80%;
  left: 80%;
}
.userCharWhite {
  position: absolute;
  background-image: url(../../../public/homedesign/images/mafia_white.png);
  background-size: cover;
  background-repeat: no-repeat;
  height: 10vh;
  width: 10vh;
  top: 80%;
  left: 80%;
}
.userCharBlue {
  position: absolute;
  background-image: url(../../../public/homedesign/images/mafia_blue.png);
  background-size: cover;
  background-repeat: no-repeat;
  height: 10vh;
  width: 10vh;
  top: 80%;
  left: 80%;
}
.userCharRed {
  position: absolute;
  background-image: url(../../../public/homedesign/images/mafia_red.png);
  background-size: cover;
  background-repeat: no-repeat;
  height: 10vh;
  width: 10vh;
  top: 80%;
  left: 80%;
}
.userCharYellow {
  position: absolute;
  background-image: url(../../../public/homedesign/images/mafia_yellow.png);
  background-size: cover;
  background-repeat: no-repeat;
  height: 10vh;
  width: 10vh;
  top: 80%;
  left: 80%;
}
</style>