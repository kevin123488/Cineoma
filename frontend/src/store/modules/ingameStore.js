// 인게임 유저정보 관리 Store

// import {
//     roomList,
//     memberList,
//   } from "@/api/lobby.js";
  
  
  const ingameStore = {
    namespaced: true,
    state: {
      absoluteTime: "",
      job: "",
      joinMembers: [
        {
          id: 'int',
          nicname: 'string',
          color: 'string',
        },
        {
          id: 'int',
          nicname: 'string',
          color: 'string',
        },
        {
          id: 'int',
          nicname: 'string',
          color: 'string',
        },  
      ]
    },
  
    getters: {
      absoluteTime(state) {
        return state.absoluteTime;
      },
      job(state) {
        return state.job;
      },
      joinMembers(state) {
        return state.joinMembers;
      },
    },
  
    mutations: {
      SET_JOINMEMBERS: (state, joinMembers) => state.joinMembers = joinMembers,
    },
  
    actions: {
      setJoinMembers({ commit }, joinMembers) {
            console.log('게임시작하고 멤버세팅')
            console.log(joinMembers)
            commit('SET_JOINMEMBERS', joinMembers)
    }
  }
}
  
  export default ingameStore;