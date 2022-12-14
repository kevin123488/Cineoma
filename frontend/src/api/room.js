// 로비 방정보 요청
// 방 만들때 요청
// 방 들어갈때 나갈때 요청
// 방 삭제할때 요청

import { apiInstance } from "./index.js";
const api = apiInstance();

// 로비 방정보 요청
async function roomList(success, fail) {
    api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
    await api.get(`/api/room`).then(success).catch(fail);
}

// 방 만들때 요청 (roomInfo{ roomTitle, password, size })
async function roomMake(roomInfo, success, fail) {
    api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
    await api.post(`/api/room`, JSON.stringify(roomInfo)).then(success).catch(fail);
}

// 방 삭제할때 요청
async function roomDelete(roomNo, success, fail) {
    api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
    await api.delete(`/api/room/${roomNo}`).then(success).catch(fail);
}

// 방 들어갈때, 나갈때 요청 roomInfo = { no: int, password: { password: int } }
async function roomEnter(roomInfo, success, fail) {
    console.log('=========입장하기요청임========')
    console.log(roomInfo.no)
    console.log(roomInfo.info)

    api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
    await api.put(`/api/room/${roomInfo.no}`, JSON.stringify(roomInfo.info)).then(success).catch(fail);
}


export {
    roomList,
    roomMake,
    roomDelete,
    roomEnter,
}