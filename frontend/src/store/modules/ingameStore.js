// 인게임 유저정보 관리 Store

// import {
//     roomList,
//     memberList,
//   } from "@/api/lobby.js";
  
  
  const ingameStore = {
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
  
  export default ingameStore;