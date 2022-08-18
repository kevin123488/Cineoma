<template>
    <!-- <div> -->
      <!-- <h1 style="font-family: 'NeoDunggeunmo Code';">승률 등등</h1> -->
      <!-- <div class="goRound" style="width: 100%;">
        <ul class="w3-ul w3-white w3-center w3-hover-opacity-off"> -->
        <!-- <ul class="w3-ul w3-white w3-center w3-opacity w3-hover-opacity-off"> hover -->
          <!-- <li class="w3-dark-grey w3-xlarge w3-padding-32"><h3>전적</h3></li>
          <li class="w3-padding-16"><span>시민 승률: {{ citizenWinRate }}%</span></li>
          <li class="w3-padding-16"><span>마피아 승률: {{ mafiaWinRate }}%</span></li>
          <li class="w3-padding-16"><span>의사 승률: {{ doctorWinRate }}%</span></li>
          <li class="w3-padding-16"><span>경찰 승률: {{ policeWinRate }}%</span></li>
          <li class="w3-padding-16">
            <h3>총 승률: {{ winRate }}%</h3>
            <span class="w3-opacity">per month</span>
          </li>
          <li class="w3-light-grey w3-padding-24">
            <button class="w3-button w3-white w3-padding-large w3-hover-black"><span>Sign Up</span></button>
          </li>
        </ul>
      </div>
    </div> -->
    <!-- <div class="recordData">
      <div>
        <hr style="width: 400px; margin-left: auto; margin-right: auto; opacity: 0;">
        <div class="recordCitizen">
          <span style="color: black;">시민 승률: {{ citizenWinRate }}%</span>
          <hr style="width: 400px; margin-left: auto; margin-right: auto; opacity: 0.1;">
        </div>
        <div>
          <span style="color: black;">마피아 승률: {{ mafiaWinRate }}%</span>
          <hr style="width: 400px; margin-left: auto; margin-right: auto; opacity: 0.1;">
        </div>
        <div>
          <span>의사 승률: {{ doctorWinRate }}%</span>
          <hr style="width: 400px; margin-left: auto; margin-right: auto; opacity: 0.1;">
        </div>
        <div>
          <span>경찰 승률: {{ policeWinRate }}%</span>
          <hr style="width: 400px; margin-left: auto; margin-right: auto; opacity: 0.1;">
        </div>
        <div>
          <h3>총 승률: {{ winRate }}%</h3>
          <hr style="width: 400px; margin-left: auto; margin-right: auto; opacity: 0.1;">
        </div>
        <div>
          <span>시민이었던 내가 오늘은 마피아?</span>
        </div>
        <hr style="width: 400px; margin-left: auto; margin-right: auto; opacity: 0;">
      </div>
    </div> -->
    <ul class="winRateTest">
      <li><h2>총 승률: {{ winRate.toFixed(0) }}%</h2></li>
      <li><h3>마피아 승률: {{ mafiaWinRate.toFixed(0) }}%</h3></li>
      <li><h3>시민 승률: {{ citizenWinRate.toFixed(0) }}%</h3></li>
      <li><h3>의사 승률: {{ doctorWinRate.toFixed(0) }}%</h3></li>
      <li><h3>교주 승률: {{ policeWinRate.toFixed(0) }}%</h3></li>
    </ul>
</template>

<script>
import { mapState } from 'vuex';
const memberStore = "memberStore";

  export default {
    name: 'RecordDetail',
    components: {
    },
    data() {
      return {
        mafiaWinRate: "",
        policeWinRate: "",
        doctorWinRate: "",
        citizenWinRate: "",
        winRate: "",
      }
    },
    computed: {
      ...mapState(memberStore, ["record"]),
    },
    methods: {
    },
    created() {
      console.log(this.record)
      let winnum = (this.record[0].winCount + this.record[1].winCount + this.record[2].winCount + this.record[3].winCount) / (this.record[0].winCount + this.record[1].winCount + this.record[2].winCount + this.record[3].winCount + this.record[0].loseCount + this.record[1].loseCount + this.record[2].loseCount + this.record[3].loseCount + this.record[0].drawCount + this.record[1].drawCount + this.record[2].drawCount + this.record[3].drawCount)
      if (this.record[0].winCount + this.record[1].winCount + this.record[2].winCount + this.record[3].winCount + this.record[0].loseCount + this.record[1].loseCount + this.record[2].loseCount + this.record[3].loseCount + this.record[0].drawCount + this.record[1].drawCount + this.record[2].drawCount + this.record[3].drawCount === 0) {
        this.winRate = 0;
      } else {
        this.winRate = winnum.toFixed(2) * 100;
      }
      console.log(this.record[0])
      console.log("해치웠나?")
      this.record.forEach((rec) => {
        console.log("여기 확인")
        console.log(rec);
        // console.log(rec.type);
        if (rec.type === "mafia") {
          console.log("이부분은 마피아의 기록")
          if (rec.winCount === 0) {
            this.mafiaWinRate = 0
          } else {
            let num = rec.winCount / (rec.winCount + rec.drawCount + rec.loseCount);
            this.mafiaWinRate = num.toFixed(2) * 100;
            console.log("d")
          }
        } else if (rec.type === "police") {
          console.log("이부분은 경찰의 기록")
          if (rec.winCount === 0) {
            this.policeWinRate = 0
          } else {
            let num = rec.winCount / (rec.winCount + rec.drawCount + rec.loseCount);
            this.policeWinRate = num.toFixed(2) * 100;
            console.log("d")
          }
        } else if (rec.type === "doctor") {
          console.log("이부분은 의사의 기록")
          if (rec.winCount === 0) {
            this.doctorWinRate = 0
          } else {
            let num = rec.winCount / (rec.winCount + rec.drawCount + rec.loseCount);
            this.doctorWinRate = num.toFixed(2) * 100;
            console.log("d")
          }
        } else if (rec.type === "citizen") {
          console.log("이부분은 시민의 기록")
          if (rec.winCount === 0) {
            this.citizenWinRate = 0
          } else {
            let num = rec.winCount / (rec.winCount + rec.drawCount + rec.loseCount);
            this.citizenWinRate = num.toFixed(2) * 100;
            console.log("d")
          }
        }
      });
    },
    }

</script>
<style>
.recordData {
  margin: auto;
  margin-top: 50px;
  margin-bottom: 50px;
  background-color: white;
  color: white;
  opacity: 0.9;
  text-align: center;
  border-radius: 30px;
  border: 1px solid white;
  background-image: url(../../../public/homedesign/images/mypage_mafia_record.gif);
  box-shadow: 3px 3px 3px 3px rgb(92, 108, 168);
}
</style>