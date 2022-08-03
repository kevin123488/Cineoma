import { createStore } from "vuex"
import createPersistedState from "vuex-persistedstate";
import memberStore from "./modules/memberStore"
import lobbyStore from "./modules/lobbyStore"
import roomdataStore from "./modules/roomdataStore"
import mypageStore from "./modules/mypageStore"

export default createStore({
  modules: {
    memberStore,
    lobbyStore,
    roomdataStore,
    mypageStore,
  },
  plugins: [
    createPersistedState({
      storage: sessionStorage,
    })
  ],
})