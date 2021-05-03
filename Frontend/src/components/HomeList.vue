<template>
  <HomeItem
   v-for="home of homes"
   v-bind:key="home.id"
   v-bind:home="home" 
  />
</template>

<script>
import HomeItem from './HomeItem.vue'

export default {
  components: {
    HomeItem
  },
  
  computed: {
    homes() {
      return this.$store.state.homes
    }
  }
  
}
</script>

<style>

</style>