  <template>
<div class="bg-secondary lobby-background" style="padding-top:20px;">

<!--  -->
<!--  -->
<!-- 로비창 이식 -->

<div class="w3-container w3-content lobby-undernavbar" style="max-width:1400px;">    
  <div class="w3-row">

    
    <!-- 방 관련 부분 -->
    <div class="w3-col m8">
    
      <div class="w3-row-padding">
        <div class="w3-col m12">
          <div class="w3-card w3-round w3-white">
            <div class="w3-container p-3">
              <!-- <h6 class="w3-opacity">Social Media template by w3.css</h6> -->
              <input contenteditable="true" type="text"  class="w3-border w3-padding" placeholder="방 제목:">
              <button type="button" class="w3-button w3-theme" style="font-family: 'NeoDunggeunmo Code';">
                <i class="fa fa-map-pin"></i>방검색
              </button>
              <!-- 방검색 어떻게할까? -->
              <!-- 일단  -->
              <make-room style="display: inline;"></make-room>
              <button type="button" class="w3-button w3-theme" style="font-family: 'NeoDunggeunmo Code';">
                <a href="/lobby" style="font-size: 1em;"><i class="fa fa-refresh"></i>새로고침</a>
              </button>
              <hr>
              <p>방 정렬할 라디오바</p>
            </div>
          </div>
        </div>
      </div>
      <br>
      <div id="scroll" class="overflow-auto" style="height: 500px;">
        <!-- <p class="sticky-top"></p> -->
        <div class="w3-container w3-card w3-white w3-round sticky-top" style="margin: 0px 16px 16px 16px;"><br>
            <h4>Activated Room List</h4>
            <br>
        </div>

        <!-- 들어가기모달 -->
        <div class="modal" id="enterRoomModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true" style="margin-top: 200px;">
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header">
                <h5 class="modal-title" id="makeRoomModalLabel">{{ enterRoomData.roomTitle }}</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
              </div>

              <div class="modal-body">
                <p>함께할 멤버</p>
                <div v-for="(member, index) in memberList" :key="index">
                  <p>{{ member.nickname }}</p>
                </div>
                <input v-if="enterRoomData.isPassword" contenteditable="true" type="text" class="w3-border w3-padding" placeholder="비밀번호를 입력해주세요:" v-model="password">
              </div>
              <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>

                <button v-on:click="clickRoom(enterRoomData.roomNo)" type="button" class="btn btn-primary" data-bs-dismiss="modal">입장하기</button>

              </div>
          </div>
        </div>
        <p>참여 인원 정보 넣으면 어떨까</p>
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
          <div v-for="(room, index) in roomList" :key="index" class="w3-container w3-card w3-white w3-round w3-margin"><br>
            <span class="w3-right w3-opacity">생성시간: 10 min</span>
            <h4>{{ room.roomTitle }} ({{ room.memberCnt }} / 5)</h4><br>
            <hr class="w3-clear">

            <!-- 들어가기버튼 -->
            <button type="button" class="w3-button w3-theme mx-3" data-bs-toggle="modal" data-bs-target="#enterRoomModal" @click="openEnterRoom(room)" style="font-family: 'NeoDunggeunmo Code';">
              <i class="fa fa-pencil"></i>들어가기
            </button>


        </div>
        <!-- 방정보 끝 -->
        
      </div>

    <!-- End Middle Column -->
    </div>
    
    <!-- 친구창 -->
    <div class="w3-col m4">

      <div class="w3-card w3-round w3-white w3-center">
        <div class="w3-container">
          <p>Upcoming Events:</p>
          <p>공지에 적은 내용 재탕하던지(이벤트 이름)</p>
          <p><strong>Holiday</strong></p>
          <p>Friday 15:00(이벤트 날짜)</p>
          <p><button class="w3-button w3-block w3-theme-l4">Info(이벤트 정보)</button></p>
        </div>
      </div>
      <br>
      
      <div id="scroll" class="overflow-auto" style="height: 400px;">

        <div class="w3-card w3-round w3-white w3-center sticky-top">
          <div class="w3-container">
            <p>유저네임's Friend List</p>
          </div>
        </div>

        <div class="w3-card w3-round w3-white w3-center">
          <div class="w3-container">
            <h3>친구 프로필</h3>
            <span>친구 이름</span>
            <p>온, 오프 표시나 게임중 표시</p>
            <div class="w3-row w3-opacity">
              <div class="w3-half">
                <button class="w3-button w3-block w3-green w3-section" title="Accept"><i class="fa fa-check"></i></button>
              </div>
              <div class="w3-half">
                <button class="w3-button w3-block w3-red w3-section" title="Decline"><i class="fa fa-remove"></i></button>
              </div>
            </div>
          </div>
          <br>
        </div>

        <div class="w3-card w3-round w3-white w3-center">
          <div class="w3-container">
            <h3>친구 프로필</h3>
            <span>친구 이름</span>
            <p>온, 오프 표시나 게임중 표시</p>
            <div class="w3-row w3-opacity">
              <div class="w3-half">
                <button class="w3-button w3-block w3-green w3-section" title="Accept"><i class="fa fa-check"></i></button>
              </div>
              <div class="w3-half">
                <button class="w3-button w3-block w3-red w3-section" title="Decline"><i class="fa fa-remove"></i></button>
              </div>
            </div>
          </div>
          <br>
        </div>

        <div class="w3-card w3-round w3-white w3-center">
          <div class="w3-container">
            <h3>친구 프로필</h3>
            <span>친구 이름</span>
            <p>온, 오프 표시나 게임중 표시</p>
            <div class="w3-row w3-opacity">
              <div class="w3-half">
                <button class="w3-button w3-block w3-green w3-section" title="Accept"><i class="fa fa-check"></i></button>
              </div>
              <div class="w3-half">
                <button class="w3-button w3-block w3-red w3-section" title="Decline"><i class="fa fa-remove"></i></button>
              </div>
            </div>
          </div>
          <br>
        </div>
      
      </div>
      <br>
      
      <div class="w3-card w3-round w3-white w3-padding-32 w3-center">
        <p><i class="fa fa-bug w3-xxlarge"></i></p>
      </div>
      
    <!-- End Right Column -->
    </div>
    
  </div>
</div>

<!-- 로비창 이식 끝 -->
<!--  -->
<!--  -->
    
    <hr>
    <friend-list></friend-list>

    <hr>
    <room-list></room-list>


  </div>
</template>

<script>
  import MakeRoom from '@/components/Lobby/MakeRoom.vue'
  import FriendList from '@/components/Lobby/FriendList.vue'
  import RoomList from '@/components/Lobby/RoomList.vue'
  import { mapActions, mapGetters } from 'vuex'
  const lobbyStore = "lobbyStore"
  const roomdataStore = "roomdataStore"
  // import axios from 'axios'
  // import drf from '@/api/drf'
  import router from '@/router'

  export default {
    name: 'LobbyView',
    components: {
        MakeRoom,
        FriendList,
        RoomList,
    },
    data() {
      return {
        num: 1,
        enterRoomData: {},
        enterModalVisible: false,
        memberList: [],
      }
    },

    // 로그인판별, 친구리스트, 방리스트 수정함수 불러오기위함
    computed: {
      // ...mapState("roomdataStore", ["roomTitle"]),
      ...mapGetters(lobbyStore, [
        'friendList',
        'roomList',
      ]),
      ...mapGetters(roomdataStore, [
        'roomTitle',
        'isCaptain'
      ]),
    },
    methods: {
      ...mapActions(lobbyStore, [
        'getRoomList',
    ]),
      ...mapActions(roomdataStore, [
        'saveRoomTitle',
        'saveIsCaptain',
    ]),

    clickRoom(roomNo) {
      console.log(roomNo)
      router.push({ name: 'wait', params: { roomnumber: roomNo } })
    },

    openEnterRoom(room) {
      this.enterRoomData = room
      this.enterModalVisible = true
      this.memberList = room.memberList
      console.log(this.memberList)
    }
    
    },

    created() {
      this.saveIsCaptain(false)
      // store에 방목록 세팅
      this.getRoomList()
    },

    mounted() {
      console.log(this.friendList)
      console.log(this.roomList[0])
      console.log(this.isCaptain)
    }
    }


</script>
<style>
input::placeholder {
  font-family: 'NeoDunggeunmo Code';
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
</style>