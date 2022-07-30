  <template>
  <div class="friendItem">
    <!-- <h1 style="font-family: 'NeoDunggeunmo Code';">친구 컴포넌트</h1> -->
    <div class="friendItemImage">
      <h3>
        여기 이미지
      </h3>
      <!-- 얘 클릭하면 친구의 마이페이지로 이동할 수 있도록 해야함 -->
    </div>

  </div>
</template>

<script>

  export default {
    name: 'FriendItem',
    components: {
    },
    data() {
      return {
      }
    },
    computed: {
    },
    methods: {
 
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