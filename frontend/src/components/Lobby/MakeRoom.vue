<template>
  <div>
    <button type="button" class="w3-button w3-theme mx-3" data-bs-toggle="modal" data-bs-target="#makeRoomModal" style="font-family: 'NeoDunggeunmo Code';">
      <i class="fa fa-pencil"></i>방만들기
    </button>

    <!-- Modal -->
    <div class="modal fade" style="margin-top: 200px;" id="makeRoomModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="makeRoomModalLabel">방만들기</h5>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <div class="modal-body">
            <p>방 제목과 비밀번호를 설정해주세요</p>
            <input contenteditable="true" type="text" class="w3-border w3-padding" placeholder="방 제목:" v-model="subject">
            <input contenteditable="true" type="text" class="w3-border w3-padding" placeholder="비밀번호(선택):" v-model="password">
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
            <router-link :to="{ name: 'wait', params: { roomnumber: num } }" 
            class="w3-bar-item w3-button">
            <button v-on:click="createRoom" type="button" class="btn btn-primary" data-bs-dismiss="modal">방만들기</button>
            </router-link>
          </div>
        </div>
      </div>
    </div>

  </div>
</template>


<script>
  // import axios from 'axios'
  // import drf from '@/api/drf'
  // import router from '@/router'
  import { mapActions, mapGetters, } from 'vuex'
  const roomdataStore = "roomdataStore"
  const memberStore = "memberStore"

  export default {
    name: 'MakeRoom',
    components: {
    },
    data() {
      return {
        num : 1,
        subject: "",
        password: "",

      }
    },

    computed: {
      ...mapGetters(memberStore, [
        'isLogin',
      ]),
    },

    methods: {
      ...mapActions(roomdataStore, [
        'saveRoomTitle',
        'saveIsCaptain',
    ]),

      createRoom() {
        // 방생성
        // /room, method: POST
        this.saveIsCaptain(true)
        this.saveRoomTitle(this.subject)
        // axios({
        //   url: drf.lobby.makeRoom(),
        //   method: 'post',
        //   headers: '토큰',
        //   data: { subject: this.subject,
        //           password: this.password,
        //   }
        // })
        // .then(res => {
        //   router.push({ name: 'wait', params: { roomnumber: res.roomnumber } })
        // })
        // .catch(err => {
        //   console.log(err)
        // })
      }
    },
    created() {
      

      }
    }

</script>

<style>
</style>