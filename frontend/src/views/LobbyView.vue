  <template>
<div class="bg-secondary" style="padding-top:20px;">

<!--  -->
<!--  -->
<!-- 로비창 이식 -->


<div class="w3-container w3-content" style="max-width:1400px;">    
  <!-- The Grid -->
  <div class="w3-row">

    
    <!-- Middle Column -->
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
              <button type="button" class="w3-button w3-theme mx-3" data-bs-toggle="modal" data-bs-target="#staticBackdrop" style="font-family: 'NeoDunggeunmo Code';">
                <i class="fa fa-pencil"></i>방만들기
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

        <div class="w3-container w3-card w3-white w3-round w3-margin"><br>
          <span class="w3-right w3-opacity">생성시간: 10 min</span>
          <h4>방 제목</h4><br>
          <hr class="w3-clear">
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

        <div class="w3-container w3-card w3-white w3-round w3-margin"><br>
          <span class="w3-right w3-opacity">생성시간: 5 min</span>
          <h4>방 제목</h4><br>
          <hr class="w3-clear">
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

        <div class="w3-container w3-card w3-white w3-round w3-margin"><br>
          <span class="w3-right w3-opacity">생성시간: 10 min</span>
          <h4>방 제목</h4><br>
          <hr class="w3-clear">
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

      </div>

    <!-- End Middle Column -->
    </div>
    
    <!-- Right Column -->
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
    
  <!-- End Grid -->
  </div>
  
<!-- End Page Container -->
</div>


<!-- 로비창 이식 끝 -->
<!--  -->
<!--  -->


    <!-- Modal -->
    <div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="staticBackdropLabel">Modal title</h5>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <div class="modal-body">
            <make-room></make-room>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
            <router-link :to="{ name: 'wait', params: { roomnumber: num } }" 
            class="w3-bar-item w3-button"><button type="button" class="btn btn-primary" data-bs-dismiss="modal">방만들기</button></router-link>
            
          </div>
        </div>
      </div>
    </div>
    
    
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


  export default {
    name: 'LobbyView',
    components: {
        MakeRoom,
        FriendList,
        RoomList,
    },
    data() {
      return {
        num : 1,
      }
    },
    computed: {
    },
    methods: {
 
    }}

</script>
<style>
input::placeholder {
  font-family: 'NeoDunggeunmo Code';
}
#scroll::-webkit-scrollbar {
  width: 0px;
  background-color: black;
}
</style>