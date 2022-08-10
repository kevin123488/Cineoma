  <template>
  <div class="friendItem">
    <!-- <h1 style="font-family: 'NeoDunggeunmo Code';">친구 컴포넌트</h1> -->
      <!-- <h3>
        여기 이미지 {{ friendItem.imagePath }}
      </h3> -->
      <img class="friendPic" :src="`/homedesign/images/` + friendItem.imagePath" onerror="this.style.display='none'">
      <h3>
        {{ friendItem.nickname }}
      </h3>
      <button class="deleteButton" @click="deleteFriend">친구삭제</button>
      <!-- 친구목록 -->
      <!-- 얘 클릭하면 친구의 마이페이지로 이동할 수 있도록 해야함 -->
  </div>
</template>

<script>
import { mapState, mapActions } from 'vuex';
// import { removeFriend } from "@/api/mypage.js";
const mypageStore = "mypageStore";
const memberStore = "memberStore";

  export default {
    name: 'FriendItem',
    props: {
      friendItem: {
        type: Object,
      } 
    },
    components: {
    },
    data() {
      return {
      }
    },
    computed: {
      ...mapState(memberStore, ["userInfo"]),
    },
    methods: {
      ...mapActions(mypageStore, ["removeFriendStore", "getFriendsStore"]),
      async deleteFriend() {
        let follow = {
          myId: this.userInfo.id,
          followId: this.friendItem.id,
        };
        await this.removeFriendStore(follow);
        // await removeFriend(follow);
        console.log(follow);
        this.getFriendsStore(this.userInfo.id);
        // this.$router.go(); // 삭제 요청 들어가고 실행된 후 새로고침하여 바뀐 정보 반영
      }
    }}

</script>

<style>
.friendItem {
  color: black;
  background-color: white;
  border-radius: 20px;
  display: flex;
  justify-content: space-between;
  margin: 10px 10px 10px 10px;
  opacity: 0.7;
  border: 2px solid white;
}
.friendPic {
  width: 100px;
  height: 50px;
  border-radius: 20px 0px 0px 20px;
  margin-right: 5px;
}
.deleteButton {
  border-radius: 0px 20px 20px 0px;
}
</style>