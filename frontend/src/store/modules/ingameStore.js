// 인게임 유저정보 관리 Store

// import {
//     roomList,
//     memberList,
//   } from "@/api/lobby.js";
  
  
  const ingameStore = {
    namespaced: true,
    state: {
      gameResult: {
        winJob: '',
        nickname: '',
        color: '',
      },
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
      gameResult(state) {
        return state.gameResult;
      },      
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
      SET_GAMERESULT: (state, gameResult) => state.gameResult = gameResult,      
    },
  
    actions: {
      setJoinMembers({ commit }, joinMembers) {
        commit('SET_JOINMEMBERS', joinMembers)
    },
      setGameResult({ commit }, gameResult) {
        commit('SET_GAMERESULT', gameResult)
    },
  }
}
  
  export default ingameStore;