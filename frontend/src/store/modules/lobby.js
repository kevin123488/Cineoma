// 들어올 데이터:
// 활성화 된 방 정보(정렬기능 넣을거라서 정렬기준 저장 필요)
// 친구목록 데이터
// 

const lobbyStore = {
  namespaced: true,
  state: {
    friendList: "ㅇㅇ",
    roomList: [
      { 
        title: '1번방제',
        roomNum: 1,
        members: {
          member1: 'info1',
          member2: 'info2',
          member3: 'info3',
          member4: 'info4',
          member5: 'info5',
        }
      },
      { 
        title: '2번방제',
        roomNum: 2,
        members: {
          member1: 'info1',
          member2: 'info2',
          member3: 'info3',
          member4: 'info4',
          member5: 'info5',
          }
      },
      { 
        title: '3번방제',
        roomNum: 3,
        members: {
          member1: 'info1',
          member2: 'info2',
          member3: 'info3',
          member4: 'info4',
          member5: 'info5',
          }
      },
      { 
        title: '4번방제',
        roomNum: 4,
        members: {
          member1: 'info1',
          member2: 'info2',
          member3: 'info3',
          member4: 'info4',
          member5: 'info5',
          }
      },
      { 
        title: '5번방제',
        roomNum: 5,
        members: {
          member1: 'info1',
          member2: 'info2',
          member3: 'info3',
          member4: 'info4',
          member5: 'info5',
          }
      },
    ],
  },
  getters: {
    friendList(state) {
      return state.friendList;
    },
    roomList(state) {
      return state.roomList;
    },
    // friendList: state => state.friendList,
    // roomList: state => state.roomList,
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
