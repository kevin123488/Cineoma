<template>
  <div v-if="streamManager" class="brownColor charSetClass">
    <div :class="{
      'userCharBlack': gameInfo.color === 'black',
      'userCharWhite': gameInfo.color === 'white',
      'userCharRed': gameInfo.color === 'red',
      'userCharBlue': gameInfo.color === 'blue',
      'userCharYellow': gameInfo.color === 'yellow',
      }">
    </div>
    <br>
    <h3><b>{{ gameInfo.nickname }}</b></h3>
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

.charSetClass {
  position: relative;
}

</style>