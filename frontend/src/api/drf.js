// 백 주소
const HOST = 'http://localhost:8000'

const LOBBY = '/room'

export default {
    lobby: {
      setLobby: () => HOST + LOBBY,
      makeRoom: () => HOST + LOBBY,
      
    },
  }