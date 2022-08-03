import { createStore } from "vuex"
import createPersistedState from "vuex-persistedstate";
import memberStore from "./modules/memberStore"
import lobbyStore from "./modules/lobby"
import ingameStore from "./modules/ingame"
import mypageStore from "./modules/mypageStore"

export default createStore({
  modules: {
    memberStore,
    lobbyStore,
    ingameStore,
    mypageStore,
  },
  plugins: [
    createPersistedState({
      storage: sessionStorage,
    })
  ],
})