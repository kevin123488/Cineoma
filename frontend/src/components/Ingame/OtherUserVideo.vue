<template>
  <div v-if="streamManager" class="brownColor">
    <div v-if="gameInfo.isAlive === true" class="checkAlive"></div>
    <div v-if="gameInfo.isAlive === false" class="checkDead"></div>
    <h3 style="padding-top: 18px; margin-top: 20px;"><b>{{ gameInfo.nickname }}</b></h3>
    <ov-video :stream-manager="streamManager" style="border-radius: 10px;" />
    <div>
    </div>
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
.checkAlive {
  background-image: url(../../../public/homedesign/images/check_circle.png);
  background-repeat : no-repeat;
  background-size: 100% 100%;  
  position: fixed;
  left: 0;
  top: 0;
  width: 2%;
  height: 1%;  
}
.checkDead {
  background-image: url(../../../public/homedesign/images/check_circle.png);
  background-repeat : no-repeat;
  background-size: 100% 100%;  
  position: fixed;
  left: 0;
  top: 0;
  width: 2%;
  height: 1%;
}
</style>