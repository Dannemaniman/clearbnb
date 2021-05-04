<template>
  <h1>{{ user.fullName }}</h1>
  <p>{{ user.userName }}</p>
  <p>{{ user.email }}</p>
  <p>{{ user.userHouses }}</p>
  <p>{{ user.userBookings }}</p>
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