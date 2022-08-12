<template>
  <div v-if="streamManager">
    <ov-video :stream-manager="streamManager" />
    <div>
      <p>{{ gameInfo.nickname }}</p>
      <p>{{ this.votedInfos }}</p>
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
