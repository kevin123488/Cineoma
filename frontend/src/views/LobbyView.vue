<template>
<div class="bg-secondary lobby-background" style="padding-top:20px;">

<!--  -->
<!--  -->
<!-- 로비창 이식 -->

<div class="w3-container w3-content lobby-undernavbar" style="max-width:1400px;">    
  <div class="w3-row">

    <!-- <div id="test1"><div id="test2">asdf</div></div> -->
    <!-- 방 관련 부분 -->
    <div class="w3-col m8">
      
      <!-- 방검색, 만들기, 새로고침 부분 -->

      <div class="w3-row">
        <div class="w3-col m12">
          <div class="">
            <div id="lobbyTop" class="w3-container w3-card w3-round mx-5 my-3">
            <div class="w3-container w3-roun my-3">
              <input id ="searchBar" v-on:keyup.enter="searchRoom()" contenteditable="true" type="text" style="opacity: 0.7;" class="w3-border w3-padding w3-card" placeholder="방 제목:" v-model="searchRoomKeyword">
              <button v-on:click="searchRoom()" type="button" class="w3-button w3-theme text-deepdarkpurple" style="font-family: 'NeoDunggeunmo Code'; font-size: 20px;">
                <i class="fa fa-map-pin"></i>방검색
              </button>
              <make-room class="text-deepdarkpurple" style="display: inline;"></make-room>
              <button type="button" class="w3-button w3-theme" style="font-family: 'NeoDunggeunmo Code';">
                <a href="/lobby" class="text-deepdarkpurple" style="font-size: 20px;"><i class="fa fa-refresh "></i>새로고침</a>
              </button>
              <button v-on:click="testvote()" type="button" class="w3-button w3-theme text-deepdarkpurple" style="font-family: 'NeoDunggeunmo Code';">
                트랜지션 테스트
              </button>
            </div>
            </div>
          </div>
        </div>
      </div>

      <br>
      <!--  -->
      <div id="scroll" class="overflow-auto" style="height: 500px;">
        <!-- <p class="sticky-top"></p> -->
        <div id="lobbyMid" class="w3-container w3-card w3-round sticky-top mx-5 py-3" style="">
            <h4 class="mx-4 text-purple" style="font-size: 28px;">Activated Room List</h4>
        </div>

        <!-- 들어가기모달 -->
        <div class="modal" id="enterRoomModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true" style="margin-top: 200px;">
          <div class="modal-dialog">
            <div class="modal-content lobbyModal">
              <div class="modal-header">
                <h3 class="modal-title" style="color: white; margin-left: 50px;">{{ enterRoomData.roomTitle }}</h3>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
              </div>

              <div class="modal-body" style="color: white; margin-left: 50px;">
                <p>함께할 멤버</p>
                <div v-for="(member, index) in memberList" :key="index">
                  <p>{{ member.nickname }}</p>
                </div>
                <input v-if="enterRoomData.isPassword" contenteditable="true" type="text" class="w3-border w3-padding" placeholder="비밀번호를 입력해주세요:" v-model="password">
              </div>
              <div class="modal-footer">
              <!-- <button v-on:click="searchRoom()" type="button" class="w3-button w3-theme text-deepdarkpurple" style="font-family: 'NeoDunggeunmo Code'; font-size: 20px;">
                <i class="fa fa-map-pin"></i>방검색
              </button> -->
                <button class="w3-button w3-theme w3-white" style="font-family: 'NeoDunggeunmo Code'; font-size: 20px; border-radius: 4px;" data-bs-dismiss="modal"><i class='fa fa-close'></i>나가기</button>

                <button v-on:click="tryEnterRoom(enterRoomData)" class="w3-button w3-theme w3-white" style="font-family: 'NeoDunggeunmo Code'; font-size: 20px; border-radius: 4px;" data-bs-dismiss="modal"><i class='fa fa-arrow-right'></i>입장하기</button>

              </div>
          </div>
        </div>
          <!-- <div class="w3-row-padding" style="margin:0 -16px">
            <div class="w3-half">
              <img src="/w3images/lights.jpg" style="width:100%" alt="Northern Lights" class="w3-margin-bottom">
            </div>
            <div class="w3-half">
              <img src="/w3images/nature.jpg" style="width:100%" alt="Nature" class="w3-margin-bottom">
            </div>
          </div>
        <button type="button" class="w3-button w3-theme-d1 w3-margin-bottom"><i class="fa fa-thumbs-up"></i> Like</button> 
        <button type="button" class="w3-button w3-theme-d2 w3-margin-bottom"><i class="fa fa-comment"></i> Comment</button>  -->
        </div>

          <!-- 방 정보 시작 -->
          <div v-for="(room, index) in roomList" :key="index" id="rooms" class="w3-container w3-card w3-white w3-round  mx-5 my-4"><br>
            <span class="w3-right w3-opacity">생성시간: 10 min</span>
            <h2 id="title" class="px-4 text-purple">{{ room.roomTitle }}</h2><h3 class="px-4 text-purple">({{ room.memberCnt }} / 5)</h3>
            <hr class="">

            <!-- 들어가기버튼 -->
            <p class="mx-3 text-deepdarkpurple" data-bs-toggle="modal" data-bs-target="#enterRoomModal" @click="openEnterRoom(room)" style=" font-family: 'NeoDunggeunmo Code'; font-size: 24px;">
              <i class="fa fa-pencil"></i>들어가기
            </p>
        </div>
        <!-- 방정보 끝 -->
        
      </div>
    </div>
    
    <!-- 친구창 -->
    <div class="w3-col m4">

      <!-- 공지창 -->
      <div id="notice" class="w3-round  w3-center">
        <div class="w3-container" style="margin-left: 30px; margin-right: 30px">
          <br>
          <br>
          <p class="my-1">Upcoming Events:</p>
          <p class="my-1"><strong>어제는 시민이였던 내가 오늘은 마피아?!</strong></p>
          <p class="my-1"><strong>release</strong></p>
          <p class="my-1">8/19 09:00</p>
          <p class="my-1"><button class="w3-button w3-block w3-theme-l4">최종발표</button></p>
        </div>
      </div>
      <br>
      
      <div id="scroll" class="overflow-auto my-3" style="height: 400px; margin-left: 100px; margin-right: 100px;">

        <div id="" class="w3-card w3-round w3-white w3-center sticky-top">
          <div class="w3-container my-3">
            <h3>Friend List</h3>
          </div>
        </div>

        <div v-for="(friend, index) in friends" :key="index" class="w3-card w3-round w3-white w3-center">
          <div class="w3-container lobbyFriend">
            <h3>{{ friend.nickname }}</h3>
            <span>프로필 이미지? </span>
            <p></p>
            <div class="w3-row w3-opacity">
              <!-- <div class="w3-half">
                <button class="w3-button w3-block w3-green w3-section" title="Accept"><i class="fa fa-check"></i></button>
              </div>
              <div class="w3-half">
                <button class="w3-button w3-block w3-red w3-section" title="Decline"><i class="fa fa-remove"></i></button>
              </div> -->
            </div>
          </div>
          <br>
        </div>
      
      </div>
      <br>
      
      <!-- <div class="w3-card w3-round w3-white w3-padding-32 w3-center">
        <p><i class="fa fa-bug w3-xxlarge"></i></p>
      </div> -->
      
    <!-- End Right Column -->
    </div>
    
  </div>
</div>

<!-- 로비창 이식 끝 -->
<!--  -->
<!--  -->
    



  </div>
</template>

<script>
import MakeRoom from '@/components/Lobby/MakeRoom.vue'
import { mapActions, mapGetters, mapState } from 'vuex'
const lobbyStore = "lobbyStore"
const roomdataStore = "roomdataStore"
const mypageStore = "mypageStore"
const memberStore = "memberStore"

// import axios from 'axios'
// import drf from '@/api/drf'
// import router from '@/router'

export default {
  name: 'LobbyView',
  components: {
      MakeRoom,
  },
  data() {
    return {
      num: 1,
      enterRoomData: {},
      enterModalVisible: false,
      searchRoomKeyword: '',
      friends: [],
      // showFriendList: false
      roomNo: -1,
      password: ''
    }
  },
  watch: {
    userInfo () {
      console.log('userInfo 보고있다 ')
      try {
        this.getFriendsStore(this.userInfo.id)
      }
      catch (error) {
        this.$router.push("/") 
      }
    },
    friendList () {
      this.friends = this.friendList;
    }      
  },
  // 로그인판별, 친구리스트, 방리스트 수정함수 불러오기위함
  computed: {
    ...mapState(mypageStore, ["friendList"]),
    ...mapState(memberStore, ["userInfo"]),
    ...mapGetters(lobbyStore, [
      'roomList',
      'memberList'
    ]),
    ...mapGetters(roomdataStore, [
      'roomTitle',
      'isCaptain',
      'isConnected',
    ]),
  },
  methods: {
    ...mapActions(lobbyStore, [
      'getRoomList',
      'getMemberList',
    ]),
    ...mapActions(mypageStore, [
      'getFriendsStore',
    ]),
    ...mapActions(roomdataStore, [
      'enterRoom',
      'saveRoomTitle',
      'saveIsCaptain',
    ]),

    testvote() {
      this.$router.push("/testvote") 
    },

    openEnterRoom(room) {
      // 이쪽 랜더링 잘 안될수도 있을듯
      // 안되면 watch 달아보기
      console.log(room.no)
      this.getMemberList(room.no)
      this.enterRoomData = room
      this.enterModalVisible = true
      // this.memberList = room.memberList
      // console.log(this.memberList)
    },

    searchRoom() {
      console.log(this.searchRoomKeyword)

      const rooms = document.querySelectorAll('#rooms')
      rooms.forEach(room => {
        const titleTag = room.querySelector('#title')
        if (titleTag.innerText.includes(this.searchRoomKeyword)){
          room.style.display = 'block'
        }
        else {
          room.style.display = 'none'
        }
      });
    },

    tryEnterRoom(roomInfo) {
      roomInfo.info = { id: this.userInfo.id, no: roomInfo.no, password: this.password, }
      this.enterRoom(roomInfo)
    }
  },

  created() {

    // 방장아님 표시
    this.saveIsCaptain(false)

    // store에 방목록 세팅
    this.getRoomList()
    if (this.userInfo !== null) {
      try {
        console.log('이거다')
        this.getFriendsStore(this.userInfo.id)

        console.log('아닌가')
      
      }
      catch (error) {
        console.log('실행됨?')
        this.$router.push("/") 
      }
    }
    // store에 친구목록 세팅
    // console.log(this.userInfo)
    // console.log('왜 출력안해줌?')
  },

  mounted() {
    console.log(this.userInfo)
    console.log('연결되있나??')
    console.log(this.isConnected)
    // console.log(this.friendList)
    // console.log(this.roomList[0])
    // console.log(this.isCaptain)
  }
}
</script>
<style>
input::placeholder {
  font-family: 'NeoDunggeunmo Code';
  font-size: 20px;
}
#scroll::-webkit-scrollbar {
  width: 0px;
  background-color: black;
}
.lobby-background {
  background-image: url(../../public/homedesign/images/intro_movie.mp4);
  background-repeat: no-repeat;
  background-size: cover;
}
.lobby-undernavbar {
  margin-top: 70px;
}
.text-purple {
  color: rgb(228, 198, 255);
}
.text-darkpurple {
  color: rgb(141, 94, 183);
}
.text-deepdarkpurple {
  color: rgb(79, 48, 103);
}
.white-border {
  display: flex;
  margin: auto;
  justify-content: space-around;
  border-radius: 30px;
  border: 5px solid white;
}
.lobbyModal {
  background-image: url(../../public/homedesign/images/lobbyroom.gif);
  border-radius: 30px;
  background-size: cover;  
}
#rooms {
  background-image: url(../../public/homedesign/images/lobby_room_v2.gif);
  background-repeat : no-repeat;
  background-size: 100% 100%;
}
/* #lobbyTop {
  background-image: url(../../public/homedesign/images/lobby_top.png);
  background-repeat : no-repeat;
    background-size: 100% 100%;
} */
#lobbyMid {
  background-image: url(../../public/homedesign/images/lobby_mid_v2.gif);
  background-repeat : no-repeat;
  background-size: 100% 100%;
}
#lobbyFriendTitle {
  background-image: url(../../public/homedesign/images/lobby_friendtitle.png);
  background-repeat : no-repeat;
  background-size: 100% 100%;
}
#lobbyFriend {
  background-image: url(../../public/homedesign/images/lobby_friend.png);
  background-repeat : no-repeat;
  background-size: 100% 100%;
}
#notice {
  background-image: url(../../public/homedesign/images/notice_cloud.png);
  background-repeat : no-repeat;
  background-size: 100% 100%;
  height: 250px;
}
</style>