import { createStore } from "vuex"
import createPersistedState from "vuex-persistedstate";
import memberStore from "./modules/memberStore"
import lobby from "./modules/lobby"
import ingame from "./modules/ingame"
import mypage from "./modules/mypage"

export default createStore({
  modules: {
    memberStore,
    lobby,
    ingame,
    mypage,
  },
  plugins: [
    createPersistedState({
      storage: sessionStorage,
    })
  ],
})