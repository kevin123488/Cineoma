<template>
<!-- <div class="curtain">
  <div class="curtain__wrapper">
    <input type="checkbox">
    <div class="curtain__leftPanel curtain__panel--left"></div>
    <div class="curtain__rightPanel curtain__panel--right"></div>
    <div class="curtain__prize">안에내용</div>
  </div>
</div> -->

<div class="curtain">
  <div class="curtain__wrapper">

<!-- <input type="checkbox">
  <div class="curtain__leftPanel curtain__panel--left"></div>
  <div class="curtain__rightPanel curtain__panel--right"></div> -->
    <div :class="{ blackGround : isDark, blackGroundOut : !isDark}"></div>

    <div :class="{ curtainLeftPanel : !curtainOut , curtainLeftPanelOpen : curtainOut}"></div>
    <div :class="{ curtainRightPanel : !curtainOut , curtainRightPanelOpen : curtainOut}"></div>

    <div class="curtain__prize">
      <div class="gameEndBackground">
        <div class="mainCurtain">
          <h1 style="padding-top: 200px;, margin-top: 0px;">{{ gameResult.winJob }} 승리!</h1>
        </div>  
      </div>
    </div>

  </div>
</div>

</template>

<script>

import router from '@/router'
import { mapGetters } from 'vuex'
const ingameStore = "ingameStore"
const roomdataStore = "roomdataStore"


  export default {
    name: 'GameEndView',
    components: {
    },
    data() {
      return {
        job: 'mafia',
        curtainOut: false,
        isDark: true,
      }
    },
    computed: {
        ...mapGetters(ingameStore, [
            'gameResult',
        ]),
        ...mapGetters(roomdataStore, [
            'roomNo',
        ]),        
    },   
    methods: {
        pushWaitRoom() {
          router.push({ name: 'wait', params: { roomnumber: this.roomNo } })
        },
    },
    created() {
      // 이긴 직업에 따라 배경바뀔거 표시
      console.log(this.gameResult)
    },
    mounted() {
      setTimeout(() =>{ this.isDark = false }, 1000)
      setTimeout(() =>{ this.curtainOut = true }, 2000)
    }
  }

</script>

<style>

div {
  margin: 0;
}
.gameEndBackground {
  background-image: url(../../public/homedesign/images/wait_mafia.gif);
  height: 100vh;
  background-size: cover;
  background-repeat: no-repeat;
  /* height: 773.3px; */
}
.mainCurtain {
  background-image: url(../../public/homedesign/images/ending_curtain.png);
  height: 100vh;
  background-size: cover;
  background-repeat: no-repeat;
  /* height: 773.3px; */
}
.curtain {
  box-sizing:border-box;
  overflow:hidden;
  width:100%;
  height:100vh;
}
.curtainLeftPanel {
  background-image: url(../../public/homedesign/images/leftOpenningCurtain.png);
  background-repeat : no-repeat;
  background-size: 100% 100%;
  left:0%;
  width:50%;
  height:100vh;
  margin:0;
  padding:0;
  float:left;
  position:absolute;
  z-index:2;
  transition:all 2s ease;
}
.curtainLeftPanelOpen {
  background-image: url(../../public/homedesign/images/leftOpenningCurtain.png);
  background-repeat : no-repeat;
  background-size: 100% 100%;
  left:-50%;
  width:50%;
  height:100vh;
  margin:0;
  padding:0;
  float:left;
  position:absolute;
  z-index:2;
  transition:all 2s ease;
}
.curtainRightPanel {
  background-image: url(../../public/homedesign/images/rightOpenningCurtain.png);
  background-repeat : no-repeat;
  background-size: 100% 100%;
  right:0%;
  width:50%;
  height:100vh;
  margin:0;
  padding:0;
  float:left;
  position:absolute;
  z-index:2;
  transition:all 2s ease;
}
.curtainRightPanelOpen {
  background-image: url(../../public/homedesign/images/rightOpenningCurtain.png);
  background-repeat : no-repeat;
  background-size: 100% 100%;
  right:-50%;
  width:50%;
  height:100vh;
  margin:0;
  padding:0;
  float:left;
  position:absolute;
  z-index:2;
  transition:all 2s ease;
}
.curtain__prize {
  position:absolute;
  z-index:1;
  left:0;
  top:0;
  width:100%;
  height:100%;
  vertical-align:middle;
  text-align:center;
}
.curtain__prize:before {
  content:'';
  display:absolute;
  width:0;
  height:100%;
  vertical-align:middle;
}
.blackGround {
  background-color: black;
  position:absolute;
  z-index:3;
  left:0;
  top:0;
  opacity: 0.9;
  width:100%;
  height:100%;
  vertical-align:middle;
  text-align:center;
  transition:all 2s ease;
}
.blackGroundOut {
  background-color: black;
  position:absolute;
  z-index:3;
  left:0;
  top:0;
  opacity: 0;
  width:100%;
  height:100%;
  vertical-align:middle;
  text-align:center;
  transition:all 2s ease;
}
</style>