// 들어올 데이터:
// 활성화 된 방 정보(정렬기능 넣을거라서 정렬기준 저장 필요)
// 친구목록 데이터
// 

const state = {
  friendList: {},
  roomList: {},
};

const getters = { // return값이 있는 함수 형태로 작성
  friendList: state => state.friendList,
  roomList: state => state.roomList,
};

const actions = { // 비순차적, 비동기 로직 사용
  saveFriendList({ commit }, friendList) {
    commit('SET_FRIENDLIST', friendList)
  },

  saveRoomList({ commit }, roomList) {
    commit('SET_ROOMLIST', roomList)
  },
};

const mutations = { // state의 값에 접근하여 그 값을 바꿔줌
  SET_FRIENDLIST: (state, friendList) => state.friendList = friendList,
  SET_ROOMLIST: (state, roomList) => state.roomList = roomList,
};

export default {
  namespaced: true,
  state,
  getters,
  mutations,
  actions,
}







// export default createStore({
//   state: {
//     friendList: {},
//     roomList: {},
//   },
  
//   getters: {
//     // friendList: state => state.friendList,
//     // roomList: state => state.roomList,
//   },

//   mutations: {
//     SET_FRIENDLIST: (state, friendList) => state.friendList = friendList,
//     SET_ROOMLIST: (state, roomList) => state.roomList = roomList,
//   },

//   actions: {
//   },

//   modules: {
//   },
// })
