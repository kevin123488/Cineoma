<template>
  <div class="friendSearch">
    <!-- <h3>친구 검색</h3> -->
    <!-- <h1 style="font-family: 'NeoDunggeunmo Code';">친구검색이요</h1> -->
    <div class="search-box">
        <!-- <p>search . . .</p> -->
      <div class="search-txt-div">
        <input v-model="searchWord" @keyup.enter="friendSearch" class="search-txt" type="text" placeholder="search . . ." style="font-family: 'NeoDunggeunmo Code';">
      </div>
    </div>
    <hr class="mt-1 mb-0">
    <div class="search-result"><p class="searchPlaceholder" v-if="!isSearched">친구의 닉네임을 입력해주세요 . . .</p>
      <div v-for="(result, index) in searchResult" :key="index">
        <div class="searchResultItem">
          <div>
            이미지 {{ result.imagePath }}
          </div>
          <div @click="friendFollow(result.id)">
            친구추가 버튼 {{ result.nickname }} 이거로 접근해서 내 id랑 대상 id 넣어서 요청
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapActions, mapMutations, mapState } from 'vuex';
const mypageStore = "mypageStore";
const memberStore = "memberStore";

  export default {
    name: 'FriendSearch',
    components: {
    },
    data() {
      return {
        isSearched: false,
        searchWord: "",
        searchResult: [], // 친구들의 정보가 리스트의 형태로 들어올듯
      }
    },
    computed: {
      ...mapState(mypageStore, ["isThereSearch", "friendSearchList"]),
      ...mapState(memberStore, ["userInfo"]),
    },
    methods: {
      ...mapActions(mypageStore, ["searchFriendStore", "followFriendStore", "getFriendsStore"]),
      ...mapMutations(mypageStore, ["SET_FRIENDS_SEARCH"]),
      async friendSearch() {
        // this.SET_FRIENDS_SEARCH([]); // 검색시 store의 검색 결과를 초기화 할 필요가 있음
        // let word = this.searchWord;
        // let page = 1; // 첫 엔터 눌렀을 때 얘기임
        let follow = {
          word: this.searchWord,
        }
        await this.searchFriendStore(follow); // mypageStore의 friendSearchList에 검색 결과가 담김
        this.isSearched = this.isThereSearch;
        this.searchWord = "";
        this.searchResult = this.friendSearchList;
        console.log(this.isSearched);
      },
      async friendFollow(id) {
        let follow = {
          myId: this.userInfo.id,
          followId: id,
        }
        console.log(follow);
        await this.followFriendStore(follow);
        // this.$router.go();
        this.getFriendsStore(this.userInfo.id);
      }
    }
    }

</script>

<style>
.friendSearch {
  /* background-color: white; */
  /* border: 5px solid white; */
  opacity: 0.65;
  color: whitesmoke;
  border-radius: 20px;
  width: 40%;
}
.search-box {
  display: flex;
  justify-content: space-evenly;
  margin: auto;
  margin-top: 10px;
  border-radius: 100px;
  background-color: black;
  color: white;
  width: 90%;
  height: 50px;
}
.search-txt {
  border: none;
  background: none;
  outline: none;
  float: left;
  padding: 0;
  color: white;
  background-color: black;
  font-size: 16px;
  transition: 0.4s;
  line-height: 35px;
  width: 100%;
  height: 100%;
  border-radius: 1000px;
  margin: 0px 0px 0px 0px;
}
.search-txt-div {
  width: 70%;
}
.search-btn {
  height: 100%;
  width: 50%;
}
.search-result {
  width: 100%;
  margin-top: 10px;
  border: 5px solid white;
  border-radius: 20px;
  background-color: black;
  height: 80%;
  overflow: auto;
  color: white;
  text-align: center;
}
.searchPlaceholder {
  opacity: 1;
}
.searchResultItem {
  display: flex;
  justify-content: space-between;
  color: black;
  background-color: white;
  border-radius: 20px;
  display: flex;
  margin: 10px 10px 10px 10px;
  opacity: 0.7;
}
/* .search-box {
  margin: auto;
    width: 90%;
    transform: translate(-50%, -50%);
    background: #2f3640;
    height: 55px;
    border-radius: 40px;
    padding: 10px;
    background-color: yellowgreen;
}

.search-box:hover > .search-txt {
    width: 50%;
    padding: 0 6px;
} */

/* .search-btn {
    color: white;
    float: right;
    width: 40px;
    height: 35px;
    border-radius: 50%;
    background: black;
    display: flex;
    justify-content: center;
    align-items: center;
} */

/* .search-txt {
    border: none;
    background: none;
    outline: none;
    float: left;
    padding: 0;
    color: white;
    font-size: 16px;
    transition: 0.4s;
    line-height: 35px;
    width: 0px;
} */
</style>