<template>
  <div>
    <h1>게임결과창임</h1>
    <h1>이긴직업: {{ this.gameResult.winJob }}</h1>
    <h1>이긴사람: {{ this.gameResult.nickname }}</h1>
    <button v-on:click="pushWaitRoom">나가실?</button>
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