<template>

<!--  -->
<!--  -->
<!--  -->

    <section class="u-align-center u-clearfix u-image u-section-2" id="carousel_bb13" data-image-width="1920" data-image-height="1080">
      <div class="u-clearfix u-sheet u-valign-middle u-sheet-1">
        <div class="u-align-center u-container-style u-expanded-width-md u-expanded-width-sm u-expanded-width-xs u-group u-shape-rectangle u-group-1">
          <div class="u-container-layout u-valign-middle u-container-layout-1">
            <h6 class="u-custom-font u-text u-text-1">GAME CHANNEL</h6>
            <h2 class="u-custom-font u-text u-text-2 my-2">MAFIA</h2>
            <h1 class="u-custom-font u-text u-text-1 my-2">SignUp</h1>
            <div class="signupForm">
                <input type="text" placeholder="이름 입력" v-model="userName">
                <input type="text" placeholder="아이디 입력" v-model="id">
                <input type="text" placeholder="비밀번호 입력" v-model="password">
                <input type="text" placeholder="비밀번호 확인" v-model="password2">
                <input type="text" placeholder="이메일 입력" v-model="email">
                <input type="text" placeholder="닉네임 입력" v-model="nickname">
            </div>
            <div>
                <button @click="signup">가입</button>
                <button @click="goToLogin">go to login</button>
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
import http from "../common/axios"

export default {
    data() {
        return {
            userName: "",
            id: "",
            password: "",
            password2: "",
            email: "",
            nickname: "",
        }
    },
    methods: {
        goToLogin() {
            this.$router.push("/")
        },
        async signup() {
            if (this.password === this.password2) {
                console.log("good")
            } else {
                alert("비밀번호 제대로 입력해라")
            } // alert 실행되고 난 뒤에 밑에 로직 그대로 수행되면
              // 비밀번호 확인할 이유가 없음
            let registerObj = {
                email: this.email,
                id: this.id,
                imagePath: null,
                intro: null,
                myPoint: null,
                nickname: this.nickname,
                // no: null, // no는 보내줄 필요 없음
                password: this.password,
                userName: this.userName,
            }
            console.log(registerObj)
            try {
                let { data } = await http.post("/user", registerObj);
                console.log(data);
                if (data.result == "success") { // 회원가입 성공하면
                    this.$router.push("/") // login 화면으로 보내고
                    alert("회원가입 완료! 로그인 하세요")
                } else {
                    alert("오류 발생")
                }
            } catch(error) {
                console.log(error)
                alert("오류 발생")
            }
        },
    },

}
</script>

<style>

</style>