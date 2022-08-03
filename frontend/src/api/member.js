// 회원 관리쪽에서 필요한 요청
// 로그인, 회원가입, 회원정보 수정

import { apiInstance } from "./index.js";
const api = apiInstance();

async function login(user, success, fail) {
    await api.post(`/api/user/login`, JSON.stringify(user)).then(success).catch(fail);
}

async function findById(userid, success, fail) {
    api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
    await api.get(`/api/user/info/${userid}`).then(success).catch(fail);
}

async function modifyMemberInfo(user) {
    await api.put(`/api/user`, JSON.stringify(user));
}

async function signUpMember(user, success, fail) {
    await api.post(`/api/user`, JSON.stringify(user)).then(success).catch(fail);
}

export {
    login,
    findById,
    modifyMemberInfo,
    signUpMember,
}