// 인게임 유저정보 관리 Store

// import {
//     roomList,
//     memberList,
//   } from "@/api/lobby.js";
  
  
  const ingameStore = {
    namespaced: true,
    state: {
      isDay: true,
      missionClass: -1,
      missionCnt: 0,
      ifWin: false,
      gameResult: {
        winJob: 'mafia',
        nickname: '박제학',
        color: '',
      },
      absoluteTime: "",
      job: "mission",
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
      isDay(state) {
        return state.isDay
      },
      missionClass(state) {
        return state.missionClass
      },      
      missionCnt(state) {
        return state.missionCnt
      },
      ifWin(state) {
        return state.ifWin;
      },            
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
      SET_IFWIN: (state, ifWin) => state.ifWin = ifWin,
      SET_MISSIONCNT: (state, missionCnt) => state.missionCnt = missionCnt,
      SET_MISSIONCLASS: (state, missionClass) => state.missionClass = missionClass,
      SET_ISDAY: (state, isDay) => state.isDay = isDay,
    },
  
    actions: {
      setJoinMembers({ commit }, joinMembers) {
        commit('SET_JOINMEMBERS', joinMembers)
    },
      setGameResult({ commit }, gameResult) {
        commit('SET_GAMERESULT', gameResult)
    },
      setIfWin({ commit }, ifWin) {
        commit('SET_IFWIN', ifWin)
    },   
      setMissionCnt({ commit }, missionCnt) {
        commit('SET_MISSIONCNT', missionCnt)
    }, 
      setMissionClass({ commit }, missionClass) {
        commit('SET_MISSIONCLASS', missionClass)
    },           
      setIsDay({ commit }, isDay) {
        commit('SET_ISDAY', isDay)
    },        
  },
};
  
export default ingameStore;