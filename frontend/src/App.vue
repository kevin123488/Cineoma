<template>
  <nav-header v-if="showNav" class='fixed-top' style="z-index: 1000;">
  </nav-header>
  <router-view/>
</template>


<script>
  import NavHeader from '@/components/Nav/NavHeader.vue'

  export default {
    name: 'App',
    components: { NavHeader },
    data() {
      return {
        showNav: true,
      }
    },
    methods: {
      scrollEvent() {
        if (scrollY <= 10) {
          this.showNav = true;
          console.log("지금 보여야됨")
        } else {
          this.showNav = false;
        }
      }
    },
    created() {
    },
    mounted() {
      document.addEventListener("scroll", this.scrollEvent);
    },
    unmounted() {
      document.removeEventListener("scroll", this.scrollEvent);
    }
  }


</script>


<style>

</style>
