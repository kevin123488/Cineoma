import {
  getFriends,
  followFriend,
  removeFriend,
  searchFriend,
} from "@/api/mypage.js";

const mypageStore = {
  namespaced: true,
  state: {
    friendList: "",
    friendSearchList: "",
    isThereSearch: true,
    followNum: 0,
  },
  getters: {

  },
  mutations: {
    SET_FRIENDS: (state, friends) => {
      state.friendList = friends;
      state.followNum = friends.length;
    },
    SET_FRIENDS_SEARCH: (state, friends) => {
      state.friendSearchList = friends;
    },
    SET_SEARCH_ANSWER: (state, ans) => {
      state.isThereSearch = ans;
    },
    SET_FOLLOWERS_NUM_PLUS: (state, num) => {
      state.followNum += num;
    },
    SET_FOLLOWERS_NUM_MINUS: (state, num) => {
      state.followNum -= num;
    }
  },
  actions: {
    async getFriendsStore({ commit }, userid) {
      await getFriends(
        userid,
        (response) => { // 나중에 콘솔 찍어서 response 어떤 형식인지 확인
          if (response.data.length != 0) { 
            console.log(response)
            console.log(response.data)
            console.log(response.data.length)
            // response.data. 일수도, response. 일수도 있음
            commit("SET_FRIENDS", response.data);
          } else {
            commit("SET_FRIENDS", response.data);
            console.log("친구 없음")
          }
        },
        (error) => {
          console.log(error);
        },
      );
    },
    // actions에 commit 안써도 되나? 안되면 follow, remove 로직은
    // 화면 구성하는 파일로 옮겨두도록 하겠음
    async followFriendStore({ commit }, user) {
      await followFriend(
        user,
        (response) => {
          if (response.data.message === "success") {
            console.log("팔로우 성공")
            commit("SET_FOLLOWERS_NUM_PLUS", 1);
          } else {
            // console.log("팔로우 실패")
          }
        },
        (error) => {
          console.log(error);
        },
      );
    },

    async removeFriendStore({ commit }, user) {
      console.log("팔취 들어가나?")
      console.log(user);
      await removeFriend(
        user,
        (response) => {
          console.log("응답 어떻게 오나?")
          console.log(response);
          if (response.data.message === "success") {
            console.log("팔삭 성공")
            commit("SET_FOLLOWERS_NUM_MINUS", 1);
          } else {
            // console.log("팔삭 실패")
          }
        },
        (error) => {
          console.log(error);
        },
      );
    },

    async searchFriendStore({ commit }, follow) {
      await searchFriend(
        follow,
        (response) => {
          if (response.data.length != 0) {
            console.log("검색 들어왔냐?")
            commit("SET_FRIENDS_SEARCH", response.data);
            console.log(response.data);
            commit("SET_SEARCH_ANSWER", true);
          } else {
            console.log("검색결과 없음");
            commit("SET_SEARCH_ANSWER", false);
            commit("SET_FRIENDS_SEARCH", response.data);
          }
        },
        (error) => {
          console.log(error);
          commit("SET_SEARCH_ANSWER", false);
        },
      );
    },

  },
};

export default mypageStore;

// import { createStore } from 'vuex'

// export default createStore({
//   state: {
//   },
//   getters: {
//   },
//   mutations: {
//   },
//   actions: {
//   },
//   modules: {
//   }
// })