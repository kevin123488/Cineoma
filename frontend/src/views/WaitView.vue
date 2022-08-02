<template>

<div class="w3-main mx-5">

  <!-- Header -->
  <header id="portfolio">
    <div class="w3-container">
    <h1><b>{{ roomTitle }}</b></h1>
    <div class="w3-section w3-bottombar w3-padding-16">
      <a href="/lobby">
      <!-- <router-link :to="{ name: 'lobby' }" > -->
      <!-- 방나가기 /room/roomNum -->
      <!-- method: PUT -->
      <!-- 방 삭제 /room/roomNum -->
      <!-- method: DELETE -->
      
        <button v-if="isCaptain" v-on:click="leaveroom" class="w3-button w3-white w3-hide-small"><i class='fa fa-close'></i>방삭제</button>
        <button v-if="!isCaptain" v-on:click="leaveroom" class="w3-button w3-white w3-hide-small"><i class='fa fa-close'></i>방나가기</button>

      <!-- </router-link> -->
      </a>
    </div>
    </div>
  </header>
  
  <!-- First Photo Grid-->
  <div class="w3-row">

    <div class="w3-col m4">
      <div class="mx-5 my-5 w3-container w3-margin-bottom border border-secondary">
        <div class="w3-container w3-white">
          <p><b>유저닉네임</b></p>
          <p>간략한 유저 프로필 정보</p>
        </div>
      </div>
    </div>

    <div class="w3-col m4">
      <div class="mx-5 my-5 w3-container w3-margin-bottom border border-secondary">
        <div class="w3-container w3-white">
          <p><b>유저닉네임</b></p>
          <p>간략한 유저 프로필 정보</p>
        </div>
      </div>
    </div>

    <div class="w3-col m4">
      <div class="mx-5 my-5 w3-container w3-margin-bottom border border-secondary">
        <div class="w3-container w3-white">
          <p><b>유저닉네임</b></p>
          <p>간략한 유저 프로필 정보</p>
        </div>
      </div>
    </div>

  </div>

  
  <!-- Second Photo Grid-->
  <div class="w3-row">

    <div class="w3-col m4">
      <div class="mx-5 my-5 w3-container border border-secondary">
        <div class="w3-container w3-white">
          <p><b>유저닉네임</b></p>
          <p>간략한 유저 프로필 정보</p>
        </div>
      </div>
    </div>

    <div class="w3-col m4">
      <div class="mx-5 my-5 w3-container w3-margin-bottom border border-secondary">
        <div class="w3-container w3-white">
          <p><b>유저닉네임</b></p>
          <p>간략한 유저 프로필 정보</p>
        </div>
      </div>
    </div>

    <div class="w3-col m4">
      <div class="mx-5 my-5 w3-container w3-margin-bottom border border-secondary">
        <div class="w3-container w3-white">
          <p><b>유저닉네임</b></p>
          <p>간략한 유저 프로필 정보</p>
        </div>
      </div>
    </div>

  </div>
</div>



    <h1>대기방임</h1>
    <hr>
    <router-link :to="{ name: 'ingame', params: { id_pk: num } }" 
        class="w3-bar-item w3-button">게임시작</router-link>
</template>

<script>
  import { mapGetters, mapActions } from 'vuex'
  const ingameStore = "ingameStore"

  export default {

    components: {
    },
    data() {
      return {
        num : 1,
      }
    },
    
    computed: {
      ...mapGetters(ingameStore, [
        'roomTitle',
        'isCaptain'
      ]),
      // ...mapGetters(memberStore, [
      //   'isLogin',
      // ]),
      ...mapActions(ingameStore, [
        'saveRoomTitle',
        'saveIsCaptain',
      ])
    },

    methods: {
      leaveroom() {
        this.saveIsCaptain(false)
        this.saveRoomTitle("")
      }
    },
    created() {
      console.log(this.isCaptain)
    },
    mounted() {
    }
    }
</script>