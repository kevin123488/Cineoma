<template>
  <div class="container">
    <h1 style="font-family: 'NeoDunggeunmo Code';" class="mt-0">{{ nickname }}의 프로필입니다</h1>
    <div class="myProfileLayout">
      <div class="profileDiv">
        <div class="profilePic">
          <p style="text-align: center;">프로필 사진 {{ imagePath }}</p>
        </div>
        <div class="introduce">
            <span style="font-family: 'NeoDunggeunmo Code';">자기소개가 들어갈 공간 {{ intro }}</span>
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
                <input style="color: black;" type="text" v-model="inputIntro">
              </div>
              <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary" @click="submitIntro">Save changes</button>
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
  // import http from "../../common/axios" // 나중에 주석 풀자

  export default {
    name: 'MyProfile',
    components: {
      RecordDetail,
    },
    data() {
      return {
        num : 1,
        nickname: "",
        imagePath: "",
        intro: "", // 자기소개 부분인데 테이블에 이름 어떻게 들어가 있었는지 기억ㄴ
        inputIntro: "",
        winCitizen: "",
        winMafia: "",
        winDoctor: "",
        winMid: "", // 중립직업
        winAll: "",
      }
    },
    computed: {
    },
    methods: {
      // 나중에 주석 풀자
      // async submitIntro() {
      //   // this.introduce = this.inputIntro // 확인용
      //   // 수정 직후 적용되는걸 확인하려면 router push 이용해서 마이페이지를 다시 로드해줘야 할듯
      //   // 아님 watch 이용해서 자기소개 수정될때마다 다시 요청받는것도 가능
      //   // 위에 주석처리해둔 부분은 나중에 추가해도 됨 일단 요청 보내고 들어오는거 확인하고 난 뒤에 ㄱ
      //   // 회원정보 수정시 필요한 부분: id, nickname, intro, password, imagePath
      //   let newUser = {
      //     // email: this.$store.accounts.state.email,
      //     id: this.$store.accounts.state.id,
      //     imagePath: this.$store.accounts.state.imagePath,
      //     intro: this.inputIntro,
      //     // myPoint: this.$store.accounts.state.myPoint,
      //     nickname: this.$store.accounts.state.nickname,
      //     // no: null, // no는 넣어줄 필요 없음
      //     password: this.$store.accounts.state.password,
      //     // userName: this.$store.accounts.state.userName,
      //   }
      //   // 이 떄 넣어주는 객체의 구조를 어떻게 할지 정해야 함
      //   // 잘못된 구조로 넣어주면 에러뜸
      //   // 아이디만 넣어주면 유저를 찾을 수 있음
      //   // 그 유저의 intro에 해당하는 부분을 넘겨주는 intro 값으로 수정하면 됨
      //   console.log(newUser)
      //   await http.put(`/user/${newUser.id}`, newUser)
      //   .then(function() {
      //     alert("자기소개 수정 완료!!")
      //   })
      // }
    },
    // 나중에 주석 풀자 // 이 부분은 응답 오는 형식보고 다시 짜보자
    // created() {
    //     let userId = this.$store.accounts.state.id
    //     console.log(userId)
    //     let { data } = http.get(`/user/${userId}`)
    //     this.nickname = data.nickname
    //     this.imagePath = data.imagePath
    //     this.intro = data.intro
    //     this.winCitizen = data.winCitizen
    //     this.winMafia = data.winMafia
    //     this.winDoctor = data.winDoctor
    //     this.winMid = data.winMid
    //     this.winAll = data.winAll
    // 결과적으로 위의 승률관련된 정보들은 mypage 스토어에 저장하여 다른 컴포넌트(RecordDetail.vue 파일)에서 사용할 수 있게 해야함
    //   }
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
    /* background-image: url(../../../public/homedesign/images/intro_movie.mp4); */
    border-radius: 30px;
    border: 5px solid white;
  }
  .profilePic {
    border-radius: 20px;
    color: black;
    background-color: white;
    margin: auto;
    margin-top: 50px;
    margin-bottom: 50px;
    width: 250px;
    height: 250px;
  }
  .introduce {
    margin-top: 100px;
    border-radius: 20px;
    border: 5px solid white;
    height: 100px;
    overflow: auto;
    -ms-overflow-style: none;
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
</style>