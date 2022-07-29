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

  export default {
    name: 'FriendList',
    components: {
      FriendItem,
    },
    data() {
      return {
        items: [1, 2, 3, 4, 5, 6, 8, 9, 10, 11, 12, 13, 14, 15]
      }
    },
    computed: {
    },
    methods: {
 
    }}

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