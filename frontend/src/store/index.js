import { createStore } from "vuex"
import createPersistedState from "vuex-persistedstate";
import memberStore from "./modules/memberStore"
import lobbyStore from "./modules/lobbyStore"
import roomdataStore from "./modules/roomdataStore"
import mypageStore from "./modules/mypageStore"
import ingameStore from "./modules/ingameStore"


export default createStore({
  modules: {
    memberStore,
    lobbyStore,
    roomdataStore,
    mypageStore,
    ingameStore,
  },
  plugins: [
    createPersistedState({
      storage: sessionStorage,
    })
  ],
})