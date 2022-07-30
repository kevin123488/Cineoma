// 실험

const state = {
  isLogin: false,
  userName: "",
    // Login
  id: "",
  email: "",
  nickname: "",
  password: "",
  intro: "",
  imagePath: "",
  myPoint: "",
};

const getters = { // return값이 있는 함수 형태로 작성
  // isLogin: function (state) {
  //   return state.isLogin;
  // },
  // userId: function (state) {
  //   return state.id;
  // },
  isLogin: state => state.isLogin,
  userId: state => state.id,
};

const actions = { // 비순차적, 비동기 로직 사용

};

const mutations = { // state의 값에 접근하여 그 값을 바꿔줌
  SET_LOGIN(state, payload) {
    state.isLogin = payload.isLogin;
    state.userName = payload.userName;
    state.id = payload.id;
    state.email = payload.email;
    state.nickname = payload.nickname;
    state.password = payload.password;
    state.intro = payload.intro;
    state.imagePath = payload.imagePath;
    state.myPoint = payload.myPoint;
  },
};

export default {
  namespaced: true,
  state,
  getters,
  mutations,
  actions,
}