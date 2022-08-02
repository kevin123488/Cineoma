import { createStore } from "vuex"
import createPersistedState from "vuex-persistedstate";
import memberStore from "./modules/memberStore"
import lobbyStore from "./modules/lobby"
import ingameStore from "./modules/ingame"
import mypage from "./modules/mypage"

export default createStore({
  modules: {
    memberStore,
    lobbyStore,
    ingameStore,
    mypage,
  },
  plugins: [
    createPersistedState({
      storage: sessionStorage,
    })
  ],
})