import { createStore } from "vuex"
import createPersistedState from "vuex-persistedstate";
import accounts from "./modules/accounts"
import lobbyStore from "./modules/lobby"
import ingameStore from "./modules/ingame"
import mypage from "./modules/mypage"

export default createStore({
  modules: {
    accounts,
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