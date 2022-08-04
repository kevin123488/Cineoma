  <template>
  <div class="friendItem">
    <!-- <h1 style="font-family: 'NeoDunggeunmo Code';">친구 컴포넌트</h1> -->
    <div class="friendItemImage">
      <h3>
        여기 이미지 {{ friendItem.imagePath }}
      </h3>
      <button @click="deleteFriend">친구삭제</button>
      <!-- 얘 클릭하면 친구의 마이페이지로 이동할 수 있도록 해야함 -->
    </div>

  </div>
</template>

<script>
import { mapState, mapActions } from 'vuex';
const mypageStore = "mypageStore";

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
      ...mapState(mypageStore, ["userInfo"]),
    },
    methods: {
      ...mapActions(mypageStore, ["removeFriendStore"]),
      async deleteFriend() {
        let follow = {
          id: this.userInfo.id,
          friendId: this.friendItem.id,
        };
        await this.removeFriendStore(follow);
        this.$router.go(); // 삭제 요청 들어가고 실행된 후 새로고침하여 바뀐 정보 반영
      }
    }}

</script>

<style>
.friendItem {
  color: black;
  background-color: white;
  border-radius: 20px;
  display: flex;
  margin: 10px 10px 10px 10px;
}
</style>