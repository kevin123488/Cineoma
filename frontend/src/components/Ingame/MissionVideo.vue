<template>
	<!-- <video autoplay class="video-size" /> -->
    <div v-if="ifWin" class="clearSign"></div>
	<div class="content brownColor" style="font-size: 20px; text-align: center; padding-top: 40px; padding-bottom: 20px; font-family: 'NeoDunggeunmo Code';"><b>{{ motionExplanation[missionClass] }}</b></div>
	<!-- <button type="button" @click="startMotion">Start</button> -->
	<div style="text-align: center; border-radius: 10px;"><canvas id="canvas"></canvas></div>
	<div id="label-container" class="brownColor"></div>
	<div id="missionCnt" class="d-flex missionInfo brownColor" style="margin-left: 50px;">신자카운트: </div>

	<div id="clearMessage" class="brownColor" style="font-size: 20px; font-family: 'NeoDunggeunmo Code';"></div>
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
			URL: "https://teachablemachine.withgoogle.com/models/yk2e7UetV/",
			modelURL: "https://teachablemachine.withgoogle.com/models/yk2e7UetV/model.json",
			metadataURL: "https://teachablemachine.withgoogle.com/models/yk2e7UetV/metadata.json",
			model: '',
			maxPredictions: '',
			labelContainer: '',
			webcam: '',
			ctx: '',
			start: 0,
			tryingMission: false,
			end: '',
            motionExplanation: ['고개를 오른쪽으로 기울이세요!', '고개를 왼쪽으로 기울이세요!', '왼손으로 머리를 긁으세요!']
		};
	},
    computed: {
        ...mapGetters(ingameStore, [
            "ifWin", 
            "isDay",
            "missionCnt",
            "missionClass",
            ]),
    },
	props: {
		streamManager: Object,
	},
	methods: {
    ...mapActions(ingameStore, ["setIfWin", "setMissionCnt"]),

    async startMotion() {
        // load the model and metadata
        // Refer to tmImage.loadFromFiles() in the API to support files from a file picker
        // Note: the pose library adds a tmPose object to your window (window.tmPose)
        this.model = Object.freeze(await tmPose.load(this.modelURL, this.metadataURL));
        this.maxPredictions = this.model.getTotalClasses();
        // Convenience function to setup a webcam
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
        this.labelContainer = document.getElementById("label-container");
        
        // 미션 랜덤배정 후
        // this.labelContainer.appendChild(document.createElement("div"));

        // 미션 랜덤배정 전
        for (let i = 0; i < 3; i++) { // and class labels
            const percent = document.createElement("div");
            percent.classList.add("missionInfo");
            this.labelContainer.appendChild(percent);
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
        
        const { pose, posenetOutput } = await this.model.estimatePose(this.webcam.canvas);
        // Prediction 2: run input through teachable machine classification model
        // console.log(pose)
        // console.log(posenetOutput)

        const prediction = await this.model.predict(posenetOutput);
        
        // 예측퍼센트 판별

        // 현재시간
        var nowTime = new Date()
        // console.log(nowTime - end)
        // 1.00이 되고, 마지막 시간이랑 3초 텀이 있어야 판별
        if (!this.ifWin && this.isDay) {
            // 쿨타임 끝났으면
            const coolTime = String(parseInt(prediction[this.missionClass].probability.toFixed(2)*100)) + ' % 일치';
            this.labelContainer.childNodes[0].innerText = coolTime;
            if ((nowTime - this.end) >= 6000) {
                // 동작 인식되면
                if (prediction[this.missionClass].probability.toFixed(2) === '1.00') {
                    // 아직 미션시작 안했으면 시작시간 넣어주고 tryingMission true로
                    if (this.start === 0) {
                        this.start = new Date()
                    }
                    // 시작했으면 종료판별, 미션 성공조건 확인되면 성공태그 더해줌 
                    else {
                        if (nowTime - this.start >= 3000 && this.missionCnt < 4) {
                            var missionTag = document.querySelector('#missionCnt')
                            var cntPlus = document.createElement("div")

                            cntPlus.classList.add("believer");
                            
                            missionTag.appendChild(cntPlus);
                            this.start = 0
                            this.end = new Date()
                            this.setMissionCnt(this.missionCnt + 1)
                            console.log(this.missionCnt)
                            if (this.missionCnt === 4) {
                                var clearMessage = document.querySelector('#clearMessage')
                                var missionClear = document.createElement("div")
                                missionClear.classList.add("missionClearInfo");
                                missionClear.innerText = '낮 투표에서 살아남으면 승리!'
                                clearMessage.appendChild(missionClear);
                                this.setIfWin(true)
                            }
                        }
                    }
                }
                // 모션 일치안하면 초기화
                else {
                    this.start = 0
                }

                // 미션유지중인 시간 정보
                if (this.start === 0) {
                    const duration = '3 초 유지시 클리어';
                    this.labelContainer.childNodes[1].innerText = duration;
                } else {
                    const duration = String(parseInt(4 - (nowTime - this.start)/1000)) + ' 초 유지시 클리어'
                    this.labelContainer.childNodes[1].innerText = duration;
                }
            } else {
                this.labelContainer.childNodes[0].innerText = '성공!';
                const duration = String(parseInt(7 - (nowTime - this.end)/1000)) + ' 초 뒤 도전가능'
                this.labelContainer.childNodes[1].innerText = duration;
            }

            // 미션 랜덤배정 후=
            // const coolTime = String(parseInt(prediction[this.missionClass].probability.toFixed(2)*100)) + ' % 일치';
            // this.labelContainer.childNodes[0].innerText = coolTime;

            // 미션 랜덤배정 전
            // for (let i = 0; i < 3; i++) {
            //     const classPrediction =
            //         prediction[i].className + ": " + prediction[i].probability.toFixed(2);
            //     this.labelContainer.childNodes[i].innerHTML = classPrediction;
            // }
        } else if (!this.ifWin && !this.isDay) {
            const duration = '낮에만 신도를 모을 수 있습니다'
            this.labelContainer.childNodes[1].innerText = duration;
        }


        // finally draw the poses
        this.drawPose(pose);
    },

    drawPose(pose) {
        if (this.webcam.canvas) {
            this.ctx.drawImage(this.webcam.canvas, 0, 0);
            // draw the keypoints and skeleton
            if (pose) {
                // const minPartConfidence = 0.5;
                // tmPose.drawKeypoints(pose.keypoints, minPartConfidence, this.ctx);
                // tmPose.drawSkeleton(pose.keypoints, minPartConfidence, this.ctx);
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
#canvas {
    margin: auto;
}
.missionInfo {
  font-size: 24px;
  font-family: 'NeoDunggeunmo Code';
  text-align: center;
  /* margin: auto; */
}
.missionClearInfo {
  font-size: 20px;
  font-family: 'NeoDunggeunmo Code';
  text-align: center;
  color:rgb(160, 26, 26);
  font-weight: bold;  
}
.believer {
  background-image: url(../../../public/homedesign/images/believer.png);
  background-repeat : no-repeat;
  background-size: 100% 100%;
  width: 30px;
  height: 30px;
  margin-top:7px;
}
.clearSign {
  position: absolute;
  top: 56%;
  left: 68%;
  right: 30%;
  opacity: 0.9;
  width: 40vh;
  height: 40vh;
  margin: auto;
  background-image: url(../../../public/homedesign/images/missionClear.png);
  background-size: 40vh 40vh;
  background-repeat: no-repeat;
  background-color: rgba(255, 255, 255, 0);
  transition-duration: 0.5s;
  transition-timing-function: ease;
}
.brownColor {
  color:rgb(106, 66, 14)
}
</style>
