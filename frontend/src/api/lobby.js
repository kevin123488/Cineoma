// 로비 방정보 요청
// 친구목록 요청

import { apiInstance } from "./index.js";
const api = apiInstance();

async function roomList(success, fail) {
    api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
    await api.get(`/room`).then(success).catch(fail);
}



export {
    roomList,

}