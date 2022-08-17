<template>
  <div v-if="streamManager">
    <mission-video :stream-manager="streamManager" style="border-radius: 10px;" />
    <div>
      <p>{{ gameInfo.nickname }}</p>
    </div>
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