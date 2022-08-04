// 들어올 데이터:
// 활성화 된 방 정보(정렬기능 넣을거라서 정렬기준 저장 필요)
// 친구목록 데이터
// 이 파일 필요없어서 지울수도 있슴

import {
  roomList,
  memberList,
} from "@/api/lobby.js";


const lobbyStore = {
  namespaced: true,
  state: {
    friendList: 'a',
    roomList: [
      {
      roomTitle: '방제1',
      roomNo: 11,
      memberCnt: 2,
      isPassword: 0,
      },
      {
        roomTitle: '방제2',
        roomNo: 12,
        memberCnt: 3,
        isPassword: 1,
      },
      {
        roomTitle: '방제3',
        roomNo: 13,
        memberCnt: 4,
        isPassword: 1,
      }
    ],
    memberList: [
      {
        nickname: 'nickname1',
        imagePath: 'imagePath1',
      },
      {
        nickname: 'nickname2',
        imagePath: 'imagePath2',
      },
      {
        nickname: 'nickname3',
        imagePath: 'imagePath3',
      },  
    ]
  },

  getters: {
    friendList(state) {
      return state.friendList;
    },
    roomList(state) {
      return state.roomList;
    },
    memberList(state) {
      return state.memberList;
    },
  },

  mutations: {
    SET_FRIENDLIST: (state, friendList) => state.friendList = friendList,
    SET_ROOMLIST: (state, roomList) => state.roomList = roomList,
    SET_MEMBERLIST: (state, memberList) => state.memberList = memberList,
  },

  actions: {
    async getRoomList({ commit }) {
      await roomList(
        (response) => {
          console.log('방정보 불러오기')
          console.log(response.data)
          commit('SET_ROOMLIST', response.data)
        },
        () => {},
      );
    },

    async getMemberList({ commit }, roomNo ) {
      await memberList(
        roomNo,
        (response) => {
          console.log(response.data.memberList)
          commit('SET_MEMBERLIST', response.data.memberList)
        },
        () => {},
      );
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
