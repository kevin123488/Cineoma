<template>
	<!-- <video autoplay class="video-size" /> -->

	<!-- <button type="button" @click="startMotion">Start</button> -->
	<div style="text-align: center; border-radius: 10px;"><canvas id="canvas" style="border-radius: 10px;"></canvas></div>
</template>

<script type="text/javascript">

// import * as tf from '@tensorflow/tfjs';
import * as tmPose from '@teachablemachine/pose';
import { mapGetters, mapActions } from "vuex";
const ingameStore = "ingameStore";

export default {
	name: 'OvVideo',
	data() {
		return{
			webcam: '',
			start: 0,
			end: '',
		};
	},
    computed: {
        ...mapGetters(ingameStore, [
            "ifWin", 
            "isDay",
            ]),
    },
	props: {
		streamManager: Object,
	},
	methods: {
    ...mapActions(ingameStore, ["setIfWin", "setMissionCnt"]),

    async startMotion() {
        const size = 200;
        const flip = true; // whether to flip the webcam
        this.webcam = new tmPose.Webcam(size, size, flip); // width, height, flip
        await this.webcam.setup(); // request access to the webcam
        await this.webcam.play();
        window.requestAnimationFrame(this.loop);
        // append/get elements to the DOM
        const canvas = document.getElementById("canvas");
        canvas.width = size; canvas.height = size;
        this.ctx = canvas.getContext("2d");
        if (this.webcam.canvas) {
            this.ctx.drawImage(this.webcam.canvas, 0, 0);
        }        
    },

	async loop() {
		this.webcam.update(); // update the webcam frame
		window.requestAnimationFrame(this.loop);
    },

	},
	created() {
        this.startMotion()
	},
	mounted () {
	},
};
</script>

<style>
#canvas {
    margin: auto;
}
.brownColor {
  color:rgb(106, 66, 14)
}
</style>
