  <template>
  <div class="friendList">
    <div></div>
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
      // this.friends = this.friendList;
    },
    mounted() {
    },
    watch: {
      friendList (newMsg) {
        this.friends = this.friendList;
        console.log(newMsg);
      }
    }
  }

</script>

<style>
.friendList {
  overflow: auto;
  height: 60vh;
  width: 40vh;
  border-radius: 20px;
  /* border: 5px solid white; */
  padding: 10px 10px 10px 10px;
  color: whitesmoke;
  /* background-image: url(../../../public/homedesign/images/mypage_mafia_record3.gif); */
  /* border: 5px solid white; */
  /* box-shadow: 3px 3px 3px 3px rgb(92, 108, 168); */
}
.friendList::-webkit-scrollbar {
  width: 2px;
}
</style>