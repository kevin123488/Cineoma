<template>
  <div v-if="streamManager">
    <mission-video :stream-manager="streamManager" />
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
