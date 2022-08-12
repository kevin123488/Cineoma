<template>
      <!-- 마피아 -->
      <div v-if="job === 'mafia'">
        <div class="mafiaImage"></div>        
        <div style="width: 65%; margin-left: 120px; margin-top: 390px;">
          <h3 class="">당신의 직업: 마피아</h3>
          <h3 class="">밤마다 한명의 플레이어를 죽일 수 있습니다.</h3>
          <h3 class="">모든 플레이어를 죽이면 승리!</h3>
        </div>  
      </div>  

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
    }}

</script>

<style>
</style>