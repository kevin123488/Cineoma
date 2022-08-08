<template>
<!-- <div id="target"></div> -->
<!--  -->
<!--  -->
<!--  -->

<!-- 여기 div 추가해서 일단 맛만 보겠음 -->
<!-- <div class="intro-background"> -->

<!-- </div> -->






<!--  -->
<!--  -->
<!--  -->


<div class="intro-background">
      <div class="u-clearfix u-sheet u-valign-middle u-sheet-1">
        <div class="u-align-center u-container-style u-expanded-width-md u-expanded-width-sm u-expanded-width-xs u-group u-shape-rectangle u-group-1"  style="margin-top: 150px">
          
      <transition name="loginFade">
        <div v-if="showLogin" class="login-layout u-container-layout u-valign-middle u-container-layout-1">
          <h2 class="">어제는 시민이였던 내가 오늘은 마피아?!</h2>
          <div class="fadeLogin">
            <h1 class="u-custom-font u-text u-text-1 my-2">Login</h1>
            <p style="font-family: 'NeoDunggeunmo Code';">갓 파 더</p>
            <div class="loginForm">
                <input class="inputText" type="text" placeholder="아이디 입력" v-model="user.id">
                <input type="password" @keyup.enter="confirm" placeholder="비밀번호 입력" v-model="user.password">
            </div>
            <div>
                <div class="router-nav-in"><div class="btn-size u-button-style u-nav-link u-text-active-palette-1-base u-text-hover-palette-2-base" style="font-family: 'NeoDunggeunmo Code';"><button class="learn-more" style="height: 30px;" @click="confirm">login</button></div></div>
                <div class="router-nav-in"><div class="btn-size u-button-style u-nav-link u-text-active-palette-1-base u-text-hover-palette-2-base" style="font-family: 'NeoDunggeunmo Code';"><button class="learn-more" style="height: 30px;" @click="goToSignup">signup</button></div></div>
                <!-- <button class="loginSubmit" @click="login">login</button>
                <button class="goToSignup" @click="goToSignup">회원가입</button> -->
            </div>
          </div>
        </div>
      </transition>
        </div>

      </div>

  <!-- <div class="login-background">
    <h1 class="mt-0">Login</h1>
    <div class="loginForm">
      <input type="text" placeholder="아이디 입력" v-model="userId">
      <input type="text" placeholder="비밀번호 입력" v-model="userPw">
    </div>
    <div class="loginBtn">
      <button class="loginSubmit" @click="login">login</button>
      <button class="goToSignup" @click="goToSignup">회원가입</button>
    </div>
  </div> -->
</div>
</template>

<script>
import { mapState, mapActions } from 'vuex';
const memberStore = "memberStore";

export default {
  name: "LoginView",
    data() {
        return {
            user: {
              id: null,
              password: null,
            },
            showLogin: false,
        };
    },
    computed: {
      ...mapState(memberStore, ["isLogin", "isLoginError"]),
    },
    methods: {
      ...mapActions(memberStore, ["userConfirm", "getUserInfo"]),
      async confirm() {
        await this.userConfirm(this.user);
        let token = sessionStorage.getItem("access-token");
        if (this.isLogin) {
          await this.getUserInfo(token);
          console.log('토큰확인')
          console.log(token)
          this.$router.push({ name: "lobby"});
        }
      },
      goToSignup() {
          this.$router.push("/signup")
      },
    },
    mounted () {
        window.scrollTo({top:2000, behavior:"instant"})
        // let step;
        // for (step = 0; step < 1000; step++) {
        //     setTimeout(() => {
        //         console.log("쉬어")
        //     }, 10)
        //     console.log("되나?")
        window.scrollTo({top:0, behavior:"smooth"})
        // }
        // $(document).ready(function(){
        //     const offset = $("#target").offset();
        //     $('html, body').animate({scrollTop: offset.top}, 500);
        // });
        // $(document).ready(function(){
        //     const offset = $("#target").offset();
        //     $('html, body').animate({scrollTop: offset.top}, 50000);
        // });
        setTimeout(() => {
            this.showLogin = true
        }, 1000)
    },
    created() {
      console.log('created')
    }
}
</script>

<style>
.intro-background {
    z-index: -1;
    background-image: url(../../public/homedesign/images/intro_movie.mp4);
    background-repeat: no-repeat;
    background-size: cover;
    height: 2000px;
    margin-top: 0px;
}
.login-background {
    position: absolute;
    top: 20%;
    left: 40%;
}
.login-layout {
    z-index: 1;
    margin-top: 80px;
}
/* 로그인 천천히 띄우기 */
.loginFade-enter-active {
  transition: opacity 2s;
}
.loginFade-enter-from {
  opacity: 0;
}
.inputText {
}
</style>