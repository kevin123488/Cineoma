<template>
  <div class="charSetClass" v-if="streamManager && gameInfo.isAlive === true">
    <div :class="{
      'userCharBlack': gameInfo.color === 'black',
      'userCharWhite': gameInfo.color === 'white',
      'userCharRed': gameInfo.color === 'red',
      'userCharBlue': gameInfo.color === 'blue',
      'userCharYellow': gameInfo.color === 'yellow',
      }">
    </div>
    <mission-video :stream-manager="streamManager" style="border-radius: 10px;" />
  </div>
</template>

<script>
import MissionVideo from "./MissionVideo";

export default {
  name: "UserVideo",

  components: {
    MissionVideo,
  },

  props: {
    streamManager: Object,
    gameInfo: Object,
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
.myVideoBackground {
  background-image: url(../../../public/homedesign/images/lobby_friend.png);
  background-repeat : no-repeat;
  background-size: 100% 100%;
}

</style>