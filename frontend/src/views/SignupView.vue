<template>
  <div class="signUpLayout">
    <div class="signUpChar"></div>
    <div class="sighUpPaper">
      <div class="signUpMsg">
        <h6 class="u-custom-font u-text u-text-1">GAME CHANNEL</h6>
        <h2 class="u-custom-font u-text u-text-2 my-2">MAFIA</h2>
        <h1 class="u-custom-font u-text u-text-1 my-2">SignUp</h1>
      </div>
      <div class="signupForm">
        <input
          class="signUpInput"
          type="text"
          placeholder="이름 입력"
          v-model="user.userName"
        />
        <input
          class="signUpInput"
          type="text"
          placeholder="아이디 입력"
          v-model="user.id"
        />
        <input
          class="signUpInput"
          type="password"
          placeholder="비밀번호 입력"
          v-model="user.password"
        />
        <input
          class="signUpInput"
          type="password"
          placeholder="비밀번호 확인"
          v-model="userpwdConfirm"
        />
        <input
          class="signUpInput"
          type="email"
          placeholder="이메일 입력"
          v-model="user.email"
        />
        <input
          class="signUpInput"
          type="text"
          placeholder="닉네임 입력"
          v-model="user.nickname"
        />
      </div>
      <div class="signUpBtn">
        <button class="learn-more" @click="register" style="height: 30px">
          가입
        </button>
        <button class="learn-more" @click="movePage" style="height: 30px">
          go to login
        </button>
      </div>
    </div>
  </div>
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
        nickname: null,
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
    validEmail: function (email) {
      var re =
        /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
      return re.test(email);
    },
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
      if (this.user.id.length < 6) {
        alert("ID 길이가 6자 이상이어야 합니다.");
        return;
      }
      if (this.user.userName.length < 2) {
        alert("유저 이름이 2자 이상이어야 합니다.");
        return;
      }
      if (this.user.nickname.length < 2) {
        alert("유저 닉네임이 2자 이상이어야 합니다.");
        return;
      }
      if (!this.pwMatches) {
        alert("비밀번호와 비밀번호 확인이 일치하지 않습니다.");
        return;
      }
      if (!this.validEmail(this.user.email)) {
        alert("이메일 양식이 올바르지 않습니다.");
        return;
      }

      await this.registerMember(this.user);
      if (this.isSignupError === false) {
        alert("회원가입 축하");
        this.movePage();
      } else {
        alert("이미 가입된 아이디 입니다");
      }
    },
    movePage() {
      this.$router.push({ name: "login" });
    },
  },
};
</script>

<style>
.signUpChar {
  z-index: 2;
  position: absolute;
  top: 70%;
  left: 25%;
  background-image: url(../../public/homedesign/images/mafia_red.png);
  width: 20vh;
  height: 20vh;
  background-size: cover;
  background-repeat: no-repeat;
  animation-duration: 1s;
  animation-iteration-count: infinite;
  animation-name: charUp;
}
@keyframes charUp {
  from {
    top: 57%;
    left: 27%;
  }
  to {
    top: 58.5%;
    left: 27%;
  }
}
.signUpLayout {
  background-image: url(../../public/homedesign/images/intro_movie.mp4);
  background-size: cover;
  background-repeat: no-repeat;
  height: 100vh;
}
.signUpMsg {
  position: absolute;
  height: 30vh;
  width: 50vw;
  top: 30%;
  left: 50%;
  transform: translate(-50%, -50%);
  text-align: center;
}
.signupForm {
  position: absolute;
  text-align: center;
  top: 60%;
  left: 50%;
  transform: translate(-50%, -50%);
  height: 30vh;
  width: 30vw;
}
.signUpInput {
  height: 4.5vh;
  width: 20vw;
  margin-top: 5px;
  border-radius: 20px;
  border: 2px solid white;
}
.signUpBtn {
  position: absolute;
  top: 90%;
  left: 50%;
  transform: translate(-50%, -50%);
}
.sighUpPaper {
  position: absolute;
  transform: translate(-50%, -50%);
  top: 50%;
  left: 50%;
  width: 50vw;
  height: 65vh;
  background-image: url(../../public/homedesign/images/mypage_background.png);
  background-size: cover;
  background-repeat: no-repeat;
}
</style>
