<template>
  <div class="w3-black mypage-background">
    <div class="container mypage-undernavbar">
        <my-profile></my-profile>
      <hr>
        <my-friend></my-friend>
      <hr>
    </div>
  </div>
</template>

<script>
  import MyFriend from '@/components/Mypage/MyFriend.vue'
  import MyProfile from '@/components/Mypage/MyProfile.vue'


  export default {
    name: 'MyPage',
    components: {
      MyFriend,
      MyProfile,
    },
    data() {
      return {
        num : 1,
      }
    },
    computed: {
    },
    methods: {
    }}

</script>

<style>
  .w3-bar.w3-black.w3-hide-small .w3-bar-item.w3-button {
    display: inline-block;
    height: 40px;
  } 
  .w3-bar.w3-black.w3-hide-small .w3-bar-item.w3-right {
    height: 40px;
    padding-top: 6px;
    padding-right: 5px;
    margin: 0 0;
  }
  .w3-bar.w3-black.w3-hide-small .w3-bar-item.w3-right > i {
    position: relative;
    top: -2px;
  }
  .w3-bar.w3-black.w3-hide-small .fa {
    /* height: 30px; */
    margin: 0;
    /* padding-top: 6px; 
    margin: 0 0; */
  }
  .auto-parent {
    position: absolute; 
    top: 45px; right:20px; 
    height: 50px; width: 50px;
  }
  .auto {
    position: absolute; 
    top: -16px; left: -201px;
  }
  .auto > .auto-child:nth-child(1) {
    padding-top: 3px;
  }
  .auto-child {
    padding-left: 4px;
    padding-top: 1px;
    width: 220px;
  }
  .mypage-background {
    background-image: url(../../public/homedesign/images/mypage_mafia.gif);
    background-repeat: no-repeat;
    background-size: cover;
  }
  .mypage-undernavbar {
  padding-top: 80px;
  }
</style>