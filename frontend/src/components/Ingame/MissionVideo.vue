<template>
	<!-- <video autoplay class="video-size" /> -->
	<div>당신은 미션을 수행해야됩니다 (class1 3초 이상 유지, 6초 텀)</div>
	<!-- <button type="button" @click="startMotion">Start</button> -->
	<div><canvas id="canvas"></canvas></div>
	<div id="label-container"></div>
	<div id="missionCnt">미션완료횟수</div>
</template>

<script type="text/javascript">

// import * as tf from '@tensorflow/tfjs';
import * as tmPose from '@teachablemachine/pose';

export default {
	name: 'OvVideo',
	data() {
		return{
			URL: "https://teachablemachine.withgoogle.com/models/NVvUJYLtt/",
			modelURL: "https://teachablemachine.withgoogle.com/models/NVvUJYLtt/model.json",
			metadataURL: "https://teachablemachine.withgoogle.com/models/NVvUJYLtt/metadata.json",
			model: '',
			maxPredictions: '',
			labelContainer: '',
			webcam: '',
			ctx: '',
			start: 0,
			tryingMission: false,
			end : '',
		};
	},
	props: {
		streamManager: Object,
	},
	methods: {
    async startMotion() {
        // load the model and metadata
        // Refer to tmImage.loadFromFiles() in the API to support files from a file picker
        // Note: the pose library adds a tmPose object to your window (window.tmPose)
        this.model = Object.freeze(await tmPose.load(this.modelURL, this.metadataURL));
        this.maxPredictions = this.model.getTotalClasses();
        // Convenience function to setup a webcam
        const size = 200;
        const flip = false; // whether to flip the webcam
        this.webcam = new tmPose.Webcam(size, size, flip); // width, height, flip
        await this.webcam.setup(); // request access to the webcam
        await this.webcam.play();
        window.requestAnimationFrame(this.loop);
        // append/get elements to the DOM
        const canvas = document.getElementById("canvas");
        canvas.width = size; canvas.height = size;
        this.ctx = canvas.getContext("2d");
        this.labelContainer = document.getElementById("label-container");
        for (let i = 0; i < this.maxPredictions; i++) { // and class labels
            this.labelContainer.appendChild(document.createElement("div"));
        }
    },

	async loop() {
		this.webcam.update(); // update the webcam frame
		await this.predict();
		window.requestAnimationFrame(this.loop);
    },

    async predict() {
        // Prediction #1: run input through posenet
        // estimatePose can take in an image, video or canvas html element
        // console.log(this.webcam)
        console.log(this.model)
        console.log(typeof(this.model))
        console.log(Object.keys(this.model))
        console.log('모델')
        
        const { pose, posenetOutput } = await this.model.estimatePose(this.webcam.canvas);
        // Prediction 2: run input through teachable machine classification model
        console.log('통과')
        console.log(pose)
        console.log(posenetOutput)

        const prediction = await this.model.predict(posenetOutput);
        
        // 예측퍼센트 판별

        // 현재시간
        var nowTime = new Date()
        // console.log(nowTime - end)
        // 1.00이 되고, 마지막 시간이랑 3초 텀이 있어야 판별
        if ((nowTime - this.end) >= 6000) {
            // 동작 인식되면
            if (prediction[0].probability.toFixed(2) === '1.00') {
                // 아직 미션시작 안했으면 시작시간 넣어주고 tryingMission true로
                if (this.start === 0) {
                    this.start = new Date()
                }
                // 시작했으면 종료판별, 미션 성공조건 확인되면 성공태그 더해줌 
                else {
                    if (nowTime - this.start >=3000) {
                        var missionTag = document.querySelector('#missionCnt')
                        var missionClear = document.createElement("div")
                        missionClear.innerText = '성공~'
                        missionTag.appendChild(missionClear);
                        this.start = 0
                        this.end = new Date()
                    }
                }
            }
            // 모션 일치안하면 초기화
            else {
                this.start = 0
            }
        }

        for (let i = 0; i < this.maxPredictions; i++) {
            const classPrediction =
                prediction[i].className + ": " + prediction[i].probability.toFixed(2);
            this.labelContainer.childNodes[i].innerHTML = classPrediction;
        }

        // finally draw the poses
        this.drawPose(pose);
    },

    drawPose(pose) {
        if (this.webcam.canvas) {
            this.ctx.drawImage(this.webcam.canvas, 0, 0);
            // draw the keypoints and skeleton
            if (pose) {
                const minPartConfidence = 0.5;
                tmPose.drawKeypoints(pose.keypoints, minPartConfidence, this.ctx);
                tmPose.drawSkeleton(pose.keypoints, minPartConfidence, this.ctx);
            }
        }
    },

	},
	created() {
        this.startMotion()
	},
	mounted () {
		// this.streamManager.addVideoElement(this.$el);
	},
};
</script>

<style>
.video-size {
    width: 100%;
    height: 200px;
}
</style>
