<template>
  <div>
    <button type="button" class="w3-button w3-theme mx-3" data-bs-toggle="modal" data-bs-target="#staticBackdrop" style="font-family: 'NeoDunggeunmo Code';">
      <i class="fa fa-pencil"></i>방만들기
    </button>

    <!-- Modal -->
    <div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="staticBackdropLabel">Modal title</h5>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <div class="modal-body">
            <p>방만들기 창임</p>
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
  import axios from 'axios'
  import drf from '@/api/drf'
  import router from '@/router'

  export default {
    name: 'MakeRoom',
    components: {
    },
    data() {
      return {
        num : 1,
      }
    },
    computed: {
    },
    methods: {
      createRoom(event) {
        // 방생성
        // /room, method: POST
        axios({
          url: drf.lobby.makeRoom(),
          method: 'post',
          data: { keyword: event.value, }
        })
        .then(res => {
          router.push({ name: 'wait', params: { roomnumber: res.roomnumber } })
        })
        .catch(err => {
          console.log(err)
        })
      }
    },
    created() {
      

      }
    }

</script>

<style>
</style>