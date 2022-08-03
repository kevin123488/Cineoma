// 친구 관련 필요한 요청 정리
// 1. 친구 목록 받아오기
// 2. 친구 요청 보내기
// 3. 친구 삭제하기
// 4. 친구 닉네임으로 검색하기

import { apiInstance } from "./index.js";
const api = apiInstance();

async function getFriends(userid) {
    await api.get(`/follow/${userid}`);
}

async function followFriend(user) { // user에는 요청 보내는 사람과 대상이 되는 id 객체형식으로
    await api.post(`/follow`, JSON.stringify(user));
}

async function followRemove(user) { // user에는 요청 보내는 사람과 대상이 되는 id 객체형식으로
    await api.delete(`/follow`, JSON.stringify(user));
}