  <template>
  <div class="friendList">
    <div><h3>친구목록</h3></div>
    <div v-for="(friend, index) in friends" :key="index">
      <friend-item :friendItem = friend></friend-item>
    </div>
  </div>
</template>

<script>
import FriendItem from '@/components/Mypage/FriendItem.vue'
import { mapState } from 'vuex';
const mypageStore = "mypageStore";

  export default {
    name: 'FriendList',
    components: {
      FriendItem,
    },
    data() {
      return {
        items: [1, 2, 3, 4, 5, 6, 8, 9, 10, 11, 12, 13, 14, 15],
        friends: [],
      }
    },
    computed: {
      ...mapState(mypageStore, ["friendList"]),
    },
    methods: {
    },
    created() {
      this.friends = this.friendList;
    },
    mounted() {
    },
  }

</script>

<style>
.friendList {
  overflow: auto;
  height: 500px;
  width: 40%;
  border-radius: 20px;
  /* border: 5px solid white; */
  padding: 10px 10px 10px 10px;
  color: whitesmoke;
}
.friendList::-webkit-scrollbar {
  width: 2px;
}
</style>