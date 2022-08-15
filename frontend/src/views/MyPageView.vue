<template>
  <nav-header v-if="showNav" class='fixed-top' style="z-index: 1000;">
  </nav-header>
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
  import NavHeader from '@/components/Nav/NavHeader.vue'
  const mypageStore = "mypageStore";
  import { mapActions } from 'vuex';
  import { mapState } from 'vuex';
  const memberStore = "memberStore";

  export default {
    name: 'MyPage',
    components: {
      MyFriend,
      MyProfile,
      NavHeader,
    },
    data() {
      return {
        num : 1,
        showNav: true,
      }
    },
    computed: {
      ...mapState(memberStore, ["userInfo"]),
    },
    methods: {
      ...mapActions(mypageStore, ["getFriendsStore"]),
      scrollEvent() {
      if (scrollY <= 10) {
        this.showNav = true;
        console.log("지금 보여야됨")
        } else {
          this.showNav = false;
        }
      },
    },
    mounted() {
      document.addEventListener("scroll", this.scrollEvent);
    },
    unmounted() {
      document.removeEventListener("scroll", this.scrollEvent);
    },    
    created() {
      console.log(this.userInfo);
      this.getFriendsStore(this.userInfo.id); // mypageStore의 friendList 부분에 정보 넣기
    },
    }

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