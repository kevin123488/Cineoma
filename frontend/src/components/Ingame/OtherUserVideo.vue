<template>
  <div v-if="streamManager" class="brownColor">
    <br>
    <h3><b>{{ gameInfo.nickname }}</b></h3>
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
