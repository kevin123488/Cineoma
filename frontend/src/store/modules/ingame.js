// 참여중인 방 제목, 이 유저가 방장인지 체크할 예정
// import { createStore } from 'vuex'
// import { reactive } from "vue";


const ingameStore = {
  namespaced: true,
  state: {
    roomTitle: "",
    isCaptain: false,
  },
  getters: {
    roomTitle(state) {
      return state.roomTitle;
    },
    isCaptain(state) {
      return state.isCaptain;
    },
  },
  mutations: {
    SET_ROOMTITLE: (state, roomTitle) => state.roomTitle = roomTitle,
    SET_ISCAPTAIN: (state, isCaptain) => state.isCaptain = isCaptain,
  },
  actions: {
    saveRoomTitle({ commit }, roomTitle) {
      commit('SET_ROOMTITLE', roomTitle)
    },
  
    saveIsCaptain({ commit }, isCaptain) {
      commit('SET_ISCAPTAIN', isCaptain)
    },
  },
};

export default ingameStore;


// export default createStore({
//   state: {
//     roomTitle: "asdf",
//     isCaptain: "",
//   },
  
//   getters: { // return값이 있는 함수 형태로 작성
//     roomTitle(state) {
//       return state.roomTitle + '';
//     },
//     isCaptain(state) {
//       return state.isCaptain + '';
//     },
//   },
  
//   actions: { // 비순차적, 비동기 로직 사용
//     saveRoomTitle({ commit }, roomTitle) {
//       commit('SET_ROOMTITLE', roomTitle)
//     },
  
//     saveIsCaptain({ commit }, isCaptain) {
//       commit('SET_ISCAPTAIN', isCaptain)
//     },
//   },
  
//   mutations: { // state의 값에 접근하여 그 값을 바꿔줌
//     SET_ROOMTITLE: (state, roomTitle) => state.roomTitle = roomTitle,
//     SET_ISCAPTAIN: (state, isCaptain) => state.isCaptain = isCaptain,
//   },
// })


// const state = {
//   roomTitle: "",
//   isCaptain: "",
// };

// const getters = reactive({ // return값이 있는 함수 형태로 작성
//   roomTitle: state => state.roomTitle,
//   isCaptain: state => state.isCaptain,
// });

// const actions = { // 비순차적, 비동기 로직 사용
//   saveRoomTitle({ commit }, roomTitle) {
//     commit('SET_ROOMTITLE', roomTitle)
//   },

//   saveIsCaptain({ commit }, isCaptain) {
//     commit('SET_ISCAPTAIN', isCaptain)
//   },
// };

// const mutations = { // state의 값에 접근하여 그 값을 바꿔줌
//   SET_ROOMTITLE: (state, roomTitle) => state.roomTitle = roomTitle,
//   SET_ISCAPTAIN: (state, isCaptain) => state.isCaptain = isCaptain,
// };

// export default {
//   namespaced: true,
//   state,
//   getters,
//   mutations,
//   actions,
// }
