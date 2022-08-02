// 들어올 데이터:
// 활성화 된 방 정보(정렬기능 넣을거라서 정렬기준 저장 필요)
// 친구목록 데이터
// 

const lobbyStore = {
  namespaced: true,
  state: {
    friendList: {},
    roomList: {},
  },
  getters: {
    friendList: state => state.friendList,
    roomList: state => state.roomList,
  },
  mutations: {
    SET_FRIENDLIST: (state, friendList) => state.friendList = friendList,
    SET_ROOMLIST: (state, roomList) => state.roomList = roomList,
  },
  actions: {
    saveFriendList({ commit }, friendList) {
      commit('SET_FRIENDLIST', friendList)
    },
  
    saveRoomList({ commit }, roomList) {
      commit('SET_ROOMLIST', roomList)
    },
  },
};

export default lobbyStore;


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
