<template>
  <div v-if="user">
    <h1>{{ user.fullName }}</h1>
    <p>{{ user.userName }}</p>
    <p>{{ user.email }}</p>
    <p>{{ user.userHouses }}</p>
    <p>{{ user.userBookings }}</p>
  </div>
</template>

<script>
export default {
  data() {
    return {
      user: null,
    };
  },

  async created() {
    let id = this.$route.params.id;
    let res = await fetch('/rest/users/' + id);
    let user = await res.json();
    this.user = user;
    console.log(user);
  },
};
</script>

<style scoped></style>
