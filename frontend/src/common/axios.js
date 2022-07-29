import axios from "axios";

export default axios.create({
  baseURL: "http://localhost:8080",
  headers: {
    "Content-Type": "application/json",
  },
  withCredentials: true, // default: false, false인 경우 sessionId를 매번 신규로 발급함 => server session을 사용할 수 없음
});
