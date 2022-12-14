// 참여중인 방 제목, 이 유저가 방장인지 체크할 예정
// import { createStore } from 'vuex'
// import { reactive } from "vue";
import { roomMake, roomDelete, roomEnter } from "@/api/room.js";
import router from "@/router";

const roomdataStore = {
  namespaced: true,
  state: {
    roomNo: -1,
    roomTitle: "뭐임?",
    isCaptain: false,
    password: "",
    isConnected: false,
  },
  getters: {
    password(state) {
      return state.password;
    },
    roomNo(state) {
      return state.roomNo;
    },
    roomTitle(state) {
      return state.roomTitle;
    },
    isCaptain(state) {
      return state.isCaptain;
    },
    isConnected(state) {
      return state.isConnected;
    },
  },
  mutations: {
    SET_PASSWORD: (state, password) => (state.password = password),
    SET_ROOMNO: (state, roomNo) => (state.roomNo = roomNo),
    SET_ROOMTITLE: (state, roomTitle) => (state.roomTitle = roomTitle),
    SET_ISCAPTAIN: (state, isCaptain) => (state.isCaptain = isCaptain),
    SET_ISCONNECTED: (state, isConnected) => (state.isConnected = isConnected),
  },
  actions: {
    savePassword({ commit }, password) {
      commit("SET_PASSWORD", password);
    },
    saveRoomNo({ commit }, roomNo) {
      commit("SET_ROOMNO", roomNo);
    },
    saveIsCaptain({ commit }, isCaptain) {
      commit("SET_ISCAPTAIN", isCaptain);
    },
    saveRoomTitle({ commit }, roomTitle) {
      commit("SET_ROOMTITLE", roomTitle);
    },
    saveIsConnected({ commit }, isConnected) {
      commit("SET_ISCONNECTED", isConnected);
    },
    // 외부요청
    async makeRoom({ commit }, roomInfo) {
      console.log(roomInfo);
      await roomMake(
        roomInfo,
        (response) => {
          console.log("============================");
          console.log("방만들기 성공");
          console.log(response.data);
          commit("SET_ROOMTITLE", response.data.roomTitle);
          commit("SET_ROOMNO", response.data.no);
          commit("SET_ISCAPTAIN", true);
          console.log(response.data.roomTitle);
          console.log(response.data.no);
          router.push({
            name: "wait",
            params: { roomnumber: response.data.no },
          });
        },
        () => {}
      );
    },

    async deleteRoom({ commit }, roomNo) {
      await roomDelete(
        roomNo,
        () => {
          commit("SET_ROOMTITLE", "");
          commit("SET_ISCAPTAIN", false);
        },
        () => {}
      );
    },

    // roomInfo = { roomNo: int, password: { password: int } }
    async enterRoom({ commit }, roomInfo) {
      await roomEnter(
        roomInfo,
        () => {
          console.log("========응답오나=======");
          commit("SET_ROOMNO", roomInfo.no);
          commit("SET_PASSWORD", roomInfo.password);
          router.push({ name: "wait", params: { roomnumber: roomInfo.no } });
        },
        () => {}
      );
    },
  },
};

export default roomdataStore;

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
