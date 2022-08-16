<template>
   <video autoplay class="video-size" />
</template>

<script>
export default {
   name: 'OvVideo',

   props: {
      streamManager: Object,
   },

   mounted () {
      this.streamManager.addVideoElement(this.$el);
   },
};
</script>

<style>
.video-size {
   width: 200px;
   height: 100%;
   margin: 20px 0px 30px;
}
</style>