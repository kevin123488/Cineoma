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
    
    <div class="curtain__prize">
      <div :class="{curtain__leftPanel: !curtainOut, curtain__leftPanel_close: curtainOut}"></div>
      <div :class="{curtain__rightPanel: !curtainOut, curtain__rightPanel_close: curtainOut}"></div>

      <div class="gameEndBackground">
        <div class="mainCurtain">

          <!-- 마피아 -->
          <!-- <div v-if="job === 'mafia'">
            <div class="mafiaImage"></div>        
            <div style="width: 65%; margin-left: 120px; margin-top: 390px;">
              <h3 class="">당신의 직업: 마피아</h3>
              <h3 class="">밤마다 한명의 플레이어를 죽일 수 있습니다.</h3>
              <h3 class="">모든 플레이어를 죽이면 승리!</h3>
            </div>  
          </div>   -->

          <!-- 의사 -->
          <div v-if="job === 'doctor'">
            <div class="doctorImage"></div>  
            <div style="width: 65%; margin-left: 120px; margin-top: 390px;">
              <h3 class="">당신의 직업: 의사</h3>
              <h3 class="">밤마다 한명의 플레이어를 마피아로 부터 보호할 수 있습니다.</h3>
              <h3 class="">마피아를 잡아내면 승리!</h3>
            </div>  
          </div>  

          <!-- 교주 -->
          <div v-if="job === 'headmaster'">
            <div class="headmasterImage"></div>      
            <div style="width: 65%; margin-left: 120px; margin-top: 390px;">
              <h3 class="">당신의 직업: 교주</h3>
              <h3 class="">특정 동작을 수행할 때 마다 신자 카운트가 증가합니다.</h3>
              <h3 class="">다른 플레이어 수 만큼 신자 카운트를 쌓으면 승리!</h3>
            </div>  
          </div>

          <!-- 시민 -->
          <div v-if="job === 'citizen'">
              <div class="citizenImage"></div>
              <div style="width: 65%; margin-left: 120px; margin-top: 390px;">
                <h3 class="">당신의 직업: 시민</h3>
                <h3 class="">당신은 선량한 시민입니다. 다른 사람들과 협력해 마피아를 잡아내세요.</h3>
                <h3 class="">마피아를 잡아내면 승리!</h3>
            </div>

          </div>
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
  position:relative;
  z-index:2;
  transition:all .5s ease-out;
}
.curtain__rightPanel {
  background-image: url(../../public/homedesign/images/rightOpenningCurtain.png);
  background-repeat : no-repeat;
  background-size: 100% 100%;
  width:50%;
  height:100vh;
  float:right;
  position:relative;
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
  float:right;
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
/* .curtain__panel__left:hover ~ .curtain__panel__left{
  transform:translateX(-100%)
}
.curtain__panel__right:hover ~ .curtain__panel__right{
  transform:translateX(100%)
} */
/*  */


</style>