// 실험
import jwt_decode from "jwt-decode"
import {
  login,
  findById,
  modifyMemberInfo,
  signUpMember,
} from "@/api/member.js";

const memberStore = {
  namespaced: true,
  state: {
    isLogin: false,
    isLoginError: false,
    userInfo: null,
    isSignupError: false,
    record: null,
  },
  getters: {
    checkUserInfo: function(state) {
      return state.userInfo;
    },
  },
  mutations: {
    SET_IS_LOGIN: (state, isLogin) => {
      state.isLogin = isLogin;
    },
    SET_IS_LOGIN_ERROR: (state, isLoginError) => {
      state.isLoginError = isLoginError;
    },
    SET_USER_INFO: (state, userInfo) => {
      state.isLogin = true;
      state.userInfo = userInfo;
    },
    SET_IS_SIGNUP_ERROR: (state, isSignupError) => {
      state.isSignupError = isSignupError;
    },
    SET_RECORD: (state, record) => {
      state.record = record;
    },
  },
  actions: {
    async userConfirm({ commit }, user) {
      await login(
        user,
        (response) => {
          if (response.data.message === "success") {
            console.log("success 됐나?")
            let token = response.data["access-token"];
            let record = response.data["record"];
            commit("SET_RECORD", record);
            commit("SET_IS_LOGIN", true);
            commit("SET_IS_LOGIN_ERROR", false);
            sessionStorage.setItem("access-token", token);
          } else {
            commit("SET_IS_LOGIN", false);
            commit("SET_IS_LOGIN_ERROR", true);
          }
        },
        () => {},
      );
    },
    getUserInfo({ commit }, token) {
      let decode_token = jwt_decode(token);
      findById(
        decode_token.userid,
        (response) => {
          if (response.data.message === "success") {
            commit("SET_USER_INFO", response.data.userInfo);
          } else {
            console.log("유저 정보 없음")
          }
        },
        (error) => {
          console.log(error);
        },
      );
    },
    modifyMember({ commit }, user) {
      modifyMemberInfo(user);
      commit("SET_USER_INFO", user);
    },
    async registerMember({ commit }, user) {
      console.log(user);
      await signUpMember(
        user,
        (response) => {
          console.log(response);
          if (response.data === "success") {
            commit("SET_IS_LOGIN", false);
            commit("SET_IS_LOGIN_ERROR", false);
            commit("SET_IS_SIGNUP_ERROR", false);
            commit("SET_USER_INFO", null);
            console.log("성공");
          } else {
            commit("SET_IS_SIGNUP_ERROR", true);
          }
        },
        (error) => {
          console.log(error);
          commit("SET_IS_SIGNUP_ERROR", true);
        },
      );
    },
  },
};

export default memberStore;

// const state = {
//   isLogin: false,
//   userName: "",
//     // Login
//   id: "",
//   email: "",
//   nickname: "",
//   password: "",
//   intro: "",
//   imagePath: "",
//   myPoint: "",
// };

// const getters = { // return값이 있는 함수 형태로 작성
//   // isLogin: function (state) {
//   //   return state.isLogin;
//   // },
//   // userId: function (state) {
//   //   return state.id;
//   // },
//   isLogin: state => state.isLogin,
//   userId: state => state.id,
// };

// const actions = { // 비순차적, 비동기 로직 사용

// };

// const mutations = { // state의 값에 접근하여 그 값을 바꿔줌
//   SET_LOGIN(state, payload) {
//     state.isLogin = payload.isLogin;
//     state.userName = payload.userName;
//     state.id = payload.id;
//     state.email = payload.email;
//     state.nickname = payload.nickname;
//     state.password = payload.password;
//     state.intro = payload.intro;
//     state.imagePath = payload.imagePath;
//     state.myPoint = payload.myPoint;
//   },
// };

// export default {
//   namespaced: true,
//   state,
//   getters,
//   mutations,
//   actions,
// }