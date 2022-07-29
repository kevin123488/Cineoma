<template>

<!--  -->
<!--  -->
<!--  -->


    <section class="u-align-center u-clearfix u-image u-section-2" id="carousel_bb13" data-image-width="1920" data-image-height="1080">
      <div class="u-clearfix u-sheet u-valign-middle u-sheet-1">
        <div class="u-align-center u-container-style u-expanded-width-md u-expanded-width-sm u-expanded-width-xs u-group u-shape-rectangle u-group-1">
          <div class="u-container-layout u-valign-middle u-container-layout-1">
            <h6 class="u-custom-font u-text u-text-1">GAME CHANNEL</h6>
            <h2 class="">어제는 시민이였던 내가 오늘은 마피아?!</h2>
            <div>
            <h1 class="u-custom-font u-text u-text-1 my-2">Login</h1>
            <p style="font-family: 'NeoDunggeunmo Code';">test 테스트</p>
            <div class="loginForm">
                <input type="text" placeholder="아이디 입력" v-model="userId">
                <input type="text" placeholder="비밀번호 입력" v-model="userPw">
            </div>
            <div>
                <button class="loginSubmit" @click="login">login</button>
                <button class="goToSignup" @click="goToSignup">회원가입</button>
            </div>
            </div>

          </div>
        </div>
      </div>
    </section>






<!--  -->
<!--  -->
<!--  -->


<!-- 디자인 전 로그인 코드 -->
<!-- <div>
  <h1>Login</h1>
  <div class="loginForm">
    <input type="text" placeholder="아이디 입력" v-model="userId">
    <input type="text" placeholder="비밀번호 입력" v-model="userPw">
  </div>
  <div>
    <button class="loginSubmit" @click="login">login</button>
    <button class="goToSignup" @click="goToSignup">회원가입</button>
  </div>
</div> -->
</template>

<script>

import http from "../common/axios"

export default {
    data() {
        return {
            userId: null,
            userPw: null,
        }
    },

    methods: {
        async login() { // 프로미스 이용
            let loginObj = {
                id: this.userId,
                password: this.userPw,
            }
            console.log(loginObj)
            try {
                let { data } = await http.post("/login", loginObj);
                // 비동기적으로 처리하기 위해 async, await 사용
                console.log(data)
                // commit은 mutations에, dispatch는 actions에 요청을 보내는 거라고 생각하자
                this.$store.commit("accounts/SET_LOGIN", {
                    isLogin: true,
                    username: data.user.username,
                    id: data.user.id,
                    email: data.user.email,
                    nickname: data.user.nickname,
                    password: data.user.password,
                });

                this.$router.push("/lobby")
            } catch (error) {
                console.log(error)
                if (error.response.status == "404") {
                    alert("확인좀")
                } else {
                    alert("서버 문제입니다")
                }
            }
        },

        goToSignup() {
            this.$router.push("/signup")
        },
    }
}
</script>

<style>

</style>