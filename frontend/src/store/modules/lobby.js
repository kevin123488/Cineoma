import { createStore } from 'vuex'


// 들어올 데이터:
// 활성화 된 방 정보(정렬기능 넣을거라서 정렬기준 저장 필요)
// 친구목록 데이터
// 


export default createStore({
  state: {
    friendList: {},
    roomList: {},
  },
  
  getters: {
    // friendList: state => state.friendList,
    // roomList: state => state.roomList,
  },

  mutations: {
    SET_FRIENDLIST: (state, friendList) => state.friendList = friendList,
    SET_ROOMLIST: (state, roomList) => state.roomList = roomList,
  },

  actions: {
  },

  modules: {
  },
})
