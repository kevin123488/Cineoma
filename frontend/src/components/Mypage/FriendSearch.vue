<template>
  <div class="friendSearch">
    <!-- <h3>친구 검색</h3> -->
    <!-- <h1 style="font-family: 'NeoDunggeunmo Code';">친구검색이요</h1> -->
    <div class="search-box">
        <!-- <p>search . . .</p> -->
      <div class="search-txt-div">
        <input class="search-txt" type="text" placeholder="search . . ." style="font-family: 'NeoDunggeunmo Code';">
      </div>
    </div>
    <hr class="mt-1 mb-0">
    <div class="search-result"><p>친구의 닉네임을 입력해주세요 . . .</p></div>
  </div>
</template>

<script>
// import http from '../../common/axios'

  export default {
    name: 'FriendSearch',
    components: {
    },
    data() {
      return {
      }
    },
    computed: {
    },
    methods: {
      // 나중에 객체 넘겨주는 부분의 이름 협의 필요
      // async followFriend(id) {
      //   console.log(id)
      //   let ffObj = {
      //     userId: this.$store.accounts.state.id,
      //     followId: id,
      //   }
      //   console.log(ffObj)
      //   try {
      //     await http.POST("/follow", ffObj)
      //   } catch (error) {
      //     console.log(error);
      //   }
      // }
    }}

</script>

<style>
.friendSearch {
  /* background-color: white; */
  /* border: 5px solid white; */
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