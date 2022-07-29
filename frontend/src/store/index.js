import { createStore } from "vuex"
import createPersistedState from "vuex-persistedstate";
import accounts from "./modules/accounts"
import lobby from "./modules/lobby"
import ingame from "./modules/ingame"
import mypage from "./modules/mypage"

export default createStore({
  modules: {
    accounts,
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