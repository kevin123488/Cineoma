<template>
  <div class="container">
    <h1 style="font-family: 'NeoDunggeunmo Code';" class="mt-0">{{ user.nickname }}의 프로필입니다</h1>
    <div class="myProfileLayout">
      <div class="profileDiv">
        <div class="profilePic">
          <!-- <p style="text-align: center;">{{ user.imagePath }}</p> -->
          <img style="width: 290px; height: 290px; border-radius: 10px;" :src="`/homedesign/images/` + userInfo.imagePath">
        </div>
        <div class="introduce">
            <span style="font-family: 'NeoDunggeunmo Code';">{{ user.intro }}</span>
        </div>

        <!-- 모달 시작 -->
        <!-- Button trigger modal -->
        <button type="button" class="introBtn" data-bs-toggle="modal" data-bs-target="#exampleModal">
          <span>수정</span>
        </button>
        <!-- Modal -->
        <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
              </div>
              <div class="modal-body">
                <input style="color: black; width: 90%" type="text" v-model="user.intro">
                <div class="chooseImage">
                  <h2 style="color: black">프로필 사진 선택</h2>
                  <button @click="setMafiaPic"><h1>마피아</h1></button>
                  <button @click="setCitizenPic"><h1>시민</h1></button>
                  <button @click="setPolicePic"><h1>경찰</h1></button>
                  <button @click="setDoctorPic"><h1>의사</h1></button>
                </div>
              </div>
              <div class="modal-footer">
                <button type="button" @click="cancellation" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
                <button type="button" class="btn btn-primary" @click="userUpdate">저장</button>
              </div>
            </div>
          </div>
        </div>
        <!-- 모달 끝 -->
      </div>
      <hr>
      <div class="recordEtc">
        <record-detail></record-detail>
      </div>
    </div>
  </div>
</template>

<script>
  import RecordDetail from '@/components/Mypage/RecordDetail.vue'
  import { mapState, mapActions } from 'vuex';
  const memberStore = "memberStore";

  export default {
    name: 'MyProfile',
    components: {
      RecordDetail,
    },
    computed: {
      ...mapState(memberStore, ["userInfo", "isLogin"]),
    },
    data() {
      return {
        num : 1,
        user: {},
        image1: "profile_mafia.jpg",
        image2: "mypage_mafia.gif",
        image3: "wait_mafia.gif",
        image4: "mypage_mafia_record2.gif",
      }
    },
    created() {
      this.user = this.userInfo; // 얘 나중에 주석 풀어야 함. 얘 있으면 프론트만 켰을
      console.log(this.userInfo);
      console.log(this.isLogin);
      // console.log(this.record);
      // 때 확인 불가라 일단 주석처리 해둔 것
      // 그리고 이미지 경로 수정하는 부분 어떻게 넣을지 생각해야함
      // 모달 띄워서 선택? 아님 클릭할때마다 뭐로 바뀌게?
      // 모달 띄워서 사진 몇 장 보여준 다음 특정 사진 클릭하면 그 imagePath로 수정하면 될듯
    },
    methods: {
      ...mapActions(memberStore, ["modifyMember"]),
      userUpdate() {
        console.log("지금")
        console.log(this.user.imagePath)
        this.modifyMember(this.user);
        // this.mvMyPage();
        this.$router.go(); // 잠시 주석처리
      },
      mvMyPage() {
        this.$router.push({ name: "profile" });
      },
      cancellation() {
        this.$router.go();
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
    }

</script>

<style>
  .w3-bar.w3-black.w3-hide-small .w3-bar-item.w3-button {
    display: inline-block;
    height: 40px;
  } 
  .w3-bar.w3-black.w3-hide-small .w3-bar-item.w3-right {
    height: 40px;
    padding-top: 6px;
    padding-right: 5px;
    margin: 0 0;
  }
  .w3-bar.w3-black.w3-hide-small .w3-bar-item.w3-right > i {
    position: relative;
    top: -2px;
  }
  .w3-bar.w3-black.w3-hide-small .fa {
    /* height: 30px; */
    margin: 0;
    /* padding-top: 6px; 
    margin: 0 0; */
  }
  .auto-parent {
    position: absolute; 
    top: 45px; right:20px; 
    height: 50px; width: 50px;
  }
  .auto {
    position: absolute; 
    top: -16px; left: -201px;
  }
  .auto > .auto-child:nth-child(1) {
    padding-top: 3px;
  }
  .auto-child {
    padding-left: 4px;
    padding-top: 1px;
    width: 220px;
  }
  .profileDiv {
    /* margin: auto; */
    height: 100%;
    width: 40%;
  }
  .myProfileLayout {
    display: flex;
    margin: auto;
    justify-content: space-around;
    height: 100%;
    /* background-image: url(../../../public/homedesign/images/mypage_mafia_record3.gif); */
    border-radius: 30px;
    border: 5px solid white;
    background-size: cover;
    box-shadow: 5px 5px 5px 5px rgb(81, 124, 157);
  }
  .profilePic {
    border-radius: 20px;
    color: black;
    margin: auto;
    margin-top: 50px;
    margin-bottom:50px; 
    width: 300px;
    height: 300px;
    /* background-image: url(../../../public/homedesign/images/profile_mafia.jpg); */
    border: 5px solid white;
    background-size: cover;
    cursor: pointer;
    box-shadow: 3px 3px 3px 3px rgb(92, 108, 168);
  }
  .introduce {
    height: 100px;
    width: 100%;
    overflow: auto;
    -ms-overflow-style: none;
    /* background-image: url(../../../public/homedesign/images/introduce.png); */
    background-color: aqua;
    background-size: cover;
    border-radius: 30px;
    text-align: center;
    margin: auto;
    opacity: 0.5;
    color: black;
    box-shadow: 3px 3px 3px 3px rgb(92, 108, 168);
  }
  .introduce::-webkit-scrollbar {
    display: none;
  }
  .recordEtc {
    width: 40%;
  }
  .introBtn {
    color: black;
    float: right;
    border-radius: 10px;
  }
  .profileImage {
    width: 300px;
    height: 300px;
    border-radius: 70px;
  }
</style>