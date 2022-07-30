<template>
    <div>
      <!-- <h1 style="font-family: 'NeoDunggeunmo Code';">승률 등등</h1> -->
      <div class="w3-half" style="width: 100%;">
        <ul class="w3-ul w3-white w3-center w3-hover-opacity-off">
        <!-- <ul class="w3-ul w3-white w3-center w3-opacity w3-hover-opacity-off"> hover -->
          <li class="w3-dark-grey w3-xlarge w3-padding-32"><h3>전적</h3></li>
          <li class="w3-padding-16"><span>시민 승률: </span></li>
          <li class="w3-padding-16"><span>마피아 승률: </span></li>
          <li class="w3-padding-16"><span>의사 승률: </span></li>
          <li class="w3-padding-16"><span>경찰 승률: </span></li>
          <li class="w3-padding-16">
            <h3>총 승률: </h3>
            <span class="w3-opacity">per month</span>
          </li>
          <li class="w3-light-grey w3-padding-24">
            <button class="w3-button w3-white w3-padding-large w3-hover-black"><span>Sign Up</span></button>
          </li>
        </ul>
      </div>
    </div>
</template>

<script>
// mypage store에 있는 승률 정보를 가져와서 여기서 보여주면 됨

  export default {
    name: 'RecordDetail',
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
</style>