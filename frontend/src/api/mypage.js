// 친구 관련 필요한 요청 정리
// 1. 친구 목록 받아오기
// 2. 친구 요청 보내기
// 3. 친구 삭제하기
// 4. 친구 닉네임으로 검색하기

import { apiInstance } from "./index.js";
const api = apiInstance();

async function getFriends(userid, success, fail) {
    await api.get(`/api/follow/${userid}`).then(success).catch(fail);
    // await api.get(`/api/follow`, {params: userid}).then(success).catch(fail);
}

async function followFriend(follow, success, fail) { // user에는 요청 보내는 사람과 대상이 되는 id 객체형식으로
    await api.post(`/api/follow`, JSON.stringify(follow)).then(success).catch(fail);
}

async function removeFriend(follow, success, fail) { // user에는 요청 보내는 사람과 대상이 되는 id 객체형식으로
    console.log("되나,,,")
    await api.delete(`/api/follow`, JSON.stringify(follow)).then(success).catch(fail);
    console.log(JSON.stringify(follow))
}

async function searchFriend(follow, success, fail) { // user에는 검색어, 오프셋(?), 페이지 정보를 넣음
    await api.get(`/api/user/list/`, { params: follow }).then(success).catch(fail);
}

// getFriend -> 친구 정보가 객체로 들어올듯
// followFriend, removeFriend -> 요청 잘 들어가면 라우터 푸쉬 -> mypage 화면
// searchFriend -> 검색 결과가 객체로 들어올듯

export {
    getFriends,
    followFriend,
    removeFriend,
    searchFriend,
}