<template>
  <div>
    {{ user }}
  </div>  
</template>

<script>
export default {
  data() {
    return {
      user: null
    } 
  },

  created() {
    for(let user of this.$store.state.users) {
      if(user.id == this.$route.params.id) {
        this.user = user
      }
    }

  },
}
</script>

<style scoped>

</style>