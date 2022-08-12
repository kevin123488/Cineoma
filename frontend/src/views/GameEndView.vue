<template>

<!-- <div class="curtain">
  <div class="curtain__wrapper">
    <input type="checkbox">
    <div class="curtain__panel curtain__panel--left"></div>
    <div class="curtain__prize">커튼~</div>
    <div class="curtain__panel curtain__panel--right">
    </div>
  </div>
</div> -->

<div class="curtain">
  <div class="curtain__wrapper d-flex">
    <input type="checkbox">
      <div class="curtain__prize">
        <div class="curtain__leftPanel, curtain__panel--left"></div>
        <div class="curtain__rightPanel, curtain__panel--right"></div>

        <div class="gameEndBackground">
          <div class="mainCurtain">
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
        }
    },
    created() {
      // 이긴 직업에 따라 배경바뀔거 표시
    },
    mounted() {
      // this.curtainOut = true
    }
  }

</script>

<style>

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
.curtain__leftPanel {
  background-image: url(../../public/homedesign/images/leftOpenningCurtain.png);
  background-repeat : no-repeat;
  background-size: 100% 100%;
  width:50%;
  height:100vh;
  float:left;
  position:relative;
  z-index:2;
  transition:all .5s ease-out;
}
.curtain__leftPanel_close {
  background-image: url(../../public/homedesign/images/leftOpenningCurtain.png);
  background-repeat : no-repeat;
  background-size: 100% 100%;
  left:-50%;
  width:50%;
  height:100vh;
  float:left;
  position:absolute;
  z-index:2;
  transition:all .5s ease-out;
}
.curtain__rightPanel {
  background-image: url(../../public/homedesign/images/rightOpenningCurtain.png);
  background-repeat : no-repeat;
  background-size: 100% 100%;
  width:50%;
  height:100vh;
  float:left;
  position:absolute;
  z-index:2;
  transition:all 5s ease-out;
}
.curtain__rightPanel_close {
  background-image: url(../../public/homedesign/images/rightOpenningCurtain.png);
  background-repeat : no-repeat;
  background-size: 100% 100%;
  right:-50%;
  width:50%;
  height:100vh;
  float:left;
  position:relative;
  z-index:2;
  transition:all 5s ease-out;
}
.curtain__prize {
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
   display:inline-block;
   width:0;
   height:100%;
   vertical-align:middle;
}
[type="checkbox"] {
   position:absolute;
   cursor:pointer;
   width:100%;
   height:100%;
   z-index:100;
   opacity:0;
}
[type="checkbox"]:checked ~ .curtain__panel--left {
   transform:translateX(-100%)
}
[type="checkbox"]:checked ~ .curtain__panel--right {
   transform:translateX(100%)
}
</style>