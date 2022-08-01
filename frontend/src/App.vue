<template>
  <nav-header class='fixed-top' style="z-index: 2000;">
  </nav-header>
  <router-view/>
</template>


<script>
  import NavHeader from '@/components/Nav/NavHeader.vue'

  export default {
    name: 'App',
    components: { NavHeader },
    methods: {
    },
    created() {
    }
  }


</script>


<style>

</style>
