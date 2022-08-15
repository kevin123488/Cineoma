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
    <div v-if="showblackGround" :class="{ blackGround : isDark, blackGroundOut : !isDark}"></div>

    <div :class="{ curtainLeftPanel : !curtainOut , curtainLeftPanelOpen : curtainOut}"></div>
    <div :class="{ curtainRightPanel : !curtainOut , curtainRightPanelOpen : curtainOut}"></div>

    <div class="curtain__prize">
      <div class="gameEndBackground">
        <div class="mainCurtain">


          <h1 style="font-size: 70px; padding-top: 200px;, margin-top: 0px; color: white;">{{ job }} 승리!</h1>

          <div style="height: 250px;">
            <br>
            <div v-show="!showblackGround" @click="returnWaitRoom" class="w3-container my-3 w3-white" style="width:200px; margin: auto; border-radius: 10px; cursor: pointer;">
              <h3>대기방으로</h3>
            </div>
          </div>

          <div style="height: 250px;">
            <div class="d-flex justify-content-center" style="margin: auto;">
              <div v-for="(member, idx) in winMember" :key="idx">
                <p>{{ member.nickname }}</p>
                <img class="winCharacter" :src="'/../../homedesign/images/mafia_' + member.color + '.png'" alt="">
              </div>
            </div>
          </div>

        </div>
      </div>
    </div>

  </div>
</div>

</template>

<script>

import { mapActions, mapGetters, mapState } from 'vuex'
const ingameStore = "ingameStore"
const roomdataStore = "roomdataStore"
const memberStore = "memberStore"


  export default {
    name: 'GameEndView',
    components: {
    },
    data() {
      return {
        job: '시민',
        curtainOut: false,
        isDark: true,
        showblackGround: true,
        winMember: [{ color: 'red', nickname: '마피아고수' }, { color: 'blue', nickname: '마피아초고수' }, { color: 'black', nickname: '마피아개고수' }]
      }
    },
    computed: {
      ...mapState(memberStore, ["userInfo"]),      
      ...mapGetters(ingameStore, [
          'gameResult',
      ]),
      ...mapGetters(roomdataStore, [
          'roomNo',
          'password',
      ]),        
    },   
    methods: {
      ...mapActions(roomdataStore , ["enterRoom",]),
      
      returnWaitRoom() {
        const roomInfo =  { no: this.roomNo, info: {id: this.userInfo.id, no: this.roomNo, password: this.password, }}
        this.enterRoom(roomInfo)
      },
    },
    created() {
      if (this.gameResult[0].winJob === 'mafia') {
        this.job = '마피아'
      } else if (this.gameResult[0].winJob === 'police') {
        this.job = '교주'
      } else {
        this.job = '시민'
      }

      this.gameResult.forEach((winUser) => {
        this.winMember.push({ color: winUser.color, nickname: winUser.nickname })
      });
    },
    mounted() {
      setTimeout(() =>{ this.isDark = false }, 1000)
      setTimeout(() =>{ this.curtainOut = true }, 2000)
      setTimeout(() =>{ this.showblackGround = false }, 4500)
    }
  }

</script>

<style>

div {
  margin: 0;
}
.winCharacter {
  height: 200px;
  width: 200px;
  background-size: 100% 100%;
  animation: bounce 1s ease infinite;
}
.gameEndBackground {
  background-image: url(../../public/homedesign/images/game_end.jpg);
  height: 100vh;
  background-size: 100% 100%;
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