  <template>
  <div><h1>친구 페이지</h1></div>
  <div class="friendLayout mt-5">

    <!-- <hr>
    <friend-item></friend-item> -->

    <hr>
    <friend-list></friend-list>

    <hr>
    <friend-search></friend-search>
  </div>
</template>

<script>
  // import FriendItem from '@/components/Mypage/FriendItem.vue'
  import FriendList from '@/components/Mypage/FriendList.vue'
  import FriendSearch from '@/components/Mypage/FriendSearch.vue'

  export default {
    name: 'MyFriend',
    components: {
      // FriendItem,
      FriendList,
      FriendSearch
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
  .friendLayout {
    display: flex;
    justify-content: space-evenly;
    padding: 30px 10px 30px 10px;
    background-image: url(../../../public/homedesign/images/mafia_dots.png);
    border-radius: 30px;
  }
</style>