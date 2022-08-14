<template>
<div class="profilePageBackground">
  <div v-if="whichPage" class="myProfileTestLayOut">
    <h2 class="profileName">내 정보</h2>
    <img class="myProfileImage" :src="`/homedesign/images/` + userInfo.imagePath">
    <div class="letMeIntroduce"><h3>{{ userInfo.intro }}</h3></div>
    <!-- modal btn -->
    <div class="modifyUserBtn" data-bs-toggle="modal" data-bs-target="#exampleModal"><h1>수정</h1></div>
    <!-- modal -->
    <div class="modal fade powerModal" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
          <div class="modal-dialog">
            <div class="modal-content modalDesign">
              <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">프로필 정보 수정</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
              </div>
              <div class="modal-body">
                <input style="color: black; width: 90%" type="text" v-model="user.intro">
                <div class="chooseImage">
                  <div class="profilePicTitle">
                    <h2 style="color: black">프로필 사진 선택</h2>
                  </div>
                  <button class="choicePic" @click="setMafiaPic"><h1>마피아</h1></button>
                  <button class="choicePic" @click="setCitizenPic"><h1>시민</h1></button>
                  <button class="choicePic" @click="setPolicePic"><h1>경찰</h1></button>
                  <button class="choicePic" @click="setDoctorPic"><h1>의사</h1></button>
                </div>
              </div>
              <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
                <button type="button" class="btn btn-primary" @click="userUpdate" data-bs-dismiss="modal">저장</button>
              </div>
            </div>
          </div>
        </div>
    <!-- modal fin -->

    <!-- <ul class="winRateTest">
      <li><h2>총 승률: 0%</h2></li>
      <li><h3>마피아 승률: 0%</h3></li>
      <li><h3>시민 승률: 0%</h3></li>
      <li><h3>의사 승률: 0%</h3></li>
      <li><h3>교주 승률: 0%</h3></li>
    </ul> -->
    <record-detail></record-detail>
  </div>

  <div v-if="!whichPage" class="myFriendTestLayout" key="trans2">
    <h2 class="profileName">친구목록</h2>
    <my-friend></my-friend>
  </div>

  <div @click="changePage" v-if="whichPage" class="goFriendPage">
  </div>
  <div @click="changePage" v-if="!whichPage" class="goMyprofilePage">
  </div>
</div>
</template>

<script>
import MyFriend from '@/components/Mypage/MyFriend.vue';
import RecordDetail from '@/components/Mypage/RecordDetail.vue';
import { mapActions } from 'vuex';
import { mapState } from 'vuex';
const mypageStore = "mypageStore";
const memberStore = "memberStore";

export default {
  components: {
    RecordDetail,
    MyFriend,
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  data() {
    return {
      whichPage: true,
      user: {},
    }
  },
  methods: {
    ...mapActions(mypageStore, ["getFriendsStore"]),
    ...mapActions(memberStore, ["modifyMember"]),
    changePage() {
      this.whichPage = !this.whichPage;
      console.log(this.whichPage);
      this.user = this.userInfo;
    },
    userUpdate() {
      console.log("지금")
      console.log(this.user.imagePath)
      this.modifyMember(this.user);
      // this.mvMyPage();
      // this.$router.go(); // 잠시 주석처리
    },
    setMafiaPic() {
      this.user.imagePath = this.image1;
      // alert("마피아 사진 선택. 수정 버튼을 눌러 프로필 사진을 바꿔보세요")
    },
    setCitizenPic() {
      this.user.imagePath = this.image2;
      // alert("시민 사진 선택. 수정 버튼을 눌러 프로필 사진을 바꿔보세요")
    },
    setPolicePic() {
      this.user.imagePath = this.image3;
      // alert("경찰 사진 선택. 수정 버튼을 눌러 프로필 사진을 바꿔보세요")
    },
    setDoctorPic() {
      this.user.imagePath = this.image4;
      // alert("의사 사진 선택. 수정 버튼을 눌러 프로필 사진을 바꿔보세요")
    },
  },
  async created() {
    console.log(this.userInfo);
    console.log(this.user);
    console.log("===============")
    await this.getFriendsStore(this.userInfo.id);
    console.log("친구정보 받아지나?")
    this.user = this.userinfo;
    console.log(this.userInfo);
    console.log(this.user);
  },
  watch: {
    userInfo() {
      this.user = this.userinfo;
    }
  }
}
</script>

<style>
.myProfileTestLayOut {
    position: absolute;
    height: 80vh;
    width: 100vh;
    top: 50%;
    left: 40%;
    transform: translate(-50%, -50%);
    background-image: url(../../../public/homedesign/images/mypage_background.png);
    background-size: cover;
    background-repeat: no-repeat;
    transition: all ease 1s;
}
.goFriendPage {
  cursor: pointer;
  position: absolute;
  top: 50%;
  left: 80%;
  transform: translate(-50%, -50%);
  height: 20vh;
  width: 20vh;
  background-image: url(../../../public/homedesign/images/goFriendPage.png);
  background-size: cover;
  background-repeat: no-repeat;
}
.goMyprofilePage {
  cursor: pointer;
  position: absolute;
  top: 50%;
  left: 20%;
  transform: translate(-50%, -50%);
  height: 20vh;
  width: 20vh;
  background-image: url(../../../public/homedesign/images/goMypage.png);
  background-size: cover;
  background-repeat: no-repeat;
}
.myFriendTestLayout {
  position: absolute;
  height: 80vh;
  width: 100vh;
  top: 50%;
  left: 60%;
  transform: translate(-50%, -50%);
  background-image: url(../../../public/homedesign/images/mypage_background.png);
  background-size: cover;
  background-repeat: no-repeat;
  transition: all ease 1s;
}
.profilePageBackground {
  height: 100vh;
  width: 100vw;
  background-image: url(../../../public/homedesign/images/mypage_mafia.gif);
  background-size: cover;
  background-repeat: no-repeat;
}
.profileName {
  position: absolute;
  top: 5%;
  left: 10%;
  color: black;
}
.myProfileImage {
  position: absolute;
  top: 20%;
  left: 10%;
  height: 35vh;
  width: 35vh;
  border-radius: 20px;
}
.letMeIntroduce {
  position: absolute;
  top: 70%;
  left: 10%;
  width: 60vh;
  height: 15vh;
  /* background-image: url(../../../public/homedesign/images/wait_mafia.gif); */
  border-radius: 10px;
  border: 2px solid black;
  background-size: cover;
  background-repeat: no-repeat;
}
.modifyUserBtn {
  cursor: pointer;
  position: absolute;
  text-align: center;
  top: 70%;
  left: 75%;
  width: 20vh;
  height: 7vh;
  border-radius: 10px;
  border: 2px solid black;
  /* background-image: url(../../../public/homedesign/images/wait_mafia.gif); */
}
.winRateTest {
  position: absolute;
  top: 15%;
  left: 50%;
}
.powerModal {
  z-index: 1890;
}
</style>