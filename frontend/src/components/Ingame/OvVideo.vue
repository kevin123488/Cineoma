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
    width: 100%;
    height: 200px;
}
</style>
