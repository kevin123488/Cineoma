<template>

<!--  -->
<!--  -->
<!--  -->

    <section class="signupLayout u-align-center u-clearfix u-image u-section-2" id="carousel_bb13" data-image-width="1920" data-image-height="1080">
      <div class="u-clearfix u-sheet u-valign-middle u-sheet-1">
        <div class="u-align-center u-container-style u-expanded-width-md u-expanded-width-sm u-expanded-width-xs u-group u-shape-rectangle u-group-1">
          <div class="u-container-layout u-valign-middle u-container-layout-1">
            <h6 class="u-custom-font u-text u-text-1">GAME CHANNEL</h6>
            <h2 class="u-custom-font u-text u-text-2 my-2">MAFIA</h2>
            <h1 class="u-custom-font u-text u-text-1 my-2">SignUp</h1>
            <div class="signupForm">
                <input type="text" placeholder="이름 입력" v-model="user.userName">
                <input type="text" placeholder="아이디 입력" v-model="user.id">
                <input type="password" placeholder="비밀번호 입력" v-model="user.password">
                <input type="password" placeholder="비밀번호 확인" v-model="userpwdConfirm">
                <input type="text" placeholder="이메일 입력" v-model="user.email">
                <input type="text" placeholder="닉네임 입력" v-model="user.nickname">
            </div>
            <div>
                <button @click="register">가입</button>
                <button @click="movePage">go to login</button>
            </div></div>
        </div>
      </div>
    </section>

<!--  -->
<!--  -->
<!--  -->


  <!-- 디자인 전 사인업 코드 -->
  <!-- <h1>SignUp</h1>
  <div class="signupForm">
    <input type="text" placeholder="이름 입력" v-model="username">
    <input type="text" placeholder="아이디 입력" v-model="id">
    <input type="text" placeholder="비밀번호 입력" v-model="password">
    <input type="text" placeholder="비밀번호 확인" v-model="password2">
    <input type="text" placeholder="이메일 입력" v-model="email">
    <input type="text" placeholder="닉네임 입력" v-model="nickname">
  </div>
  <div>
    <button @click="signup">가입</button>
    <button @click="goToLogin">go to login</button>
  </div> -->
</template>

<script>
import { mapActions, mapMutations, mapState } from "vuex";
const memberStore = "memberStore";

export default {
    name: "signUp",
    data() {
        return {
            user: {
                id: null,
                password: null,
                email: null,
                userName: null,
                nuckname: null,
            },
            userpwdConfirm: null,
            pwMatches: true,
        };
    },
    computed: {
        ...mapState(memberStore, ["isLogin", "isLoginError", "isSignupError"]),
    },
    methods: {
        ...mapMutations(memberStore, ["SET_IS_SIGNUP_ERROR"]),
        ...mapActions(memberStore, ["registerMember"]),

        pwCheck() {
            if (this.user.password === this.userpwdConfirm) {
                this.pwMatches = true;
            } else {
                this.pwMatches = false;
            }
        },
        async register(event) {
            event.preventDefault();
            this.pwCheck();
            if (this.pwMatches) {
                await this.registerMember(this.user);
                if (this.isSignupError === false) {
                    alert("회원가입 축하")
                    this.movePage();
                } else {
                    alert("이미 가입된 아이디 입니다");
                }
            } else {
                alert("비밀번호가 일치하지 않습니다");
            }
        },
        movePage() {
            this.$router.push({ name: "login" })
        }
    },

}
</script>

<style>

</style>