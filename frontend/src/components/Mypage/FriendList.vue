  <template>
  <div class="friendList">
    <div><h3>친구목록</h3></div>
    <div v-for="item in items" :key="items[item-1]">
      <friend-item></friend-item>
    </div>
  </div>
</template>

<script>
import FriendItem from '@/components/Mypage/FriendItem.vue'
// import http from '../../common/axios' // 나중에 주석 풀자

  export default {
    name: 'FriendList',
    components: {
      FriendItem,
    },
    data() {
      return {
        items: [1, 2, 3, 4, 5, 6, 8, 9, 10, 11, 12, 13, 14, 15],
        friends: [], // created 될 때 받을 friend들의 정보(imagePath, nickname, 가능하다면 승률까지 객체 형식으로 받아서 사용하자)
      }
    },
    computed: {
    },
    methods: {
      // async followDelete(id) { // 나중에 삭제 버튼 만들어서 ㄱ. @click followDelete(id)
      //   console.log(id)
      //   let fdObj = {
      //     userId: this.$store.accounts.state.id, // 요청하는 유저 id
      //     deleteId: id, // 삭제대상 유저 id
      //   }
      //   console.log(fdObj)
      //   try {
      //     await http.delete("/follow", fdObj)
      //   } catch (error) {
      //     console.log(error)
      //   }
      // },
    },
    // created() {
    //   this.items = [1, 2, 3, 4]
    //   console.log("created!!!")
    // },
    // mounted() {
    //   let userId = this.$store.accounts.state.id
    //   let { data } = http.get("/follow", userId);
    //   console.log(data)
    //   this.friends = data
    // }
  }

</script>

<style>
.friendList {
  overflow: auto;
  height: 500px;
  width: 40%;
  border-radius: 20px;
  border: 5px solid white;
  padding: 10px 10px 10px 10px;
}
.friendList::-webkit-scrollbar {
  width: 2px;
}
</style>