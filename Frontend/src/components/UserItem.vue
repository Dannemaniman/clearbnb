<template>
  <div @click="goToUser" class="card">
    <img v-bind:src="user.image" alt="">
    <h3>{{ user.fullName }}</h3>
    <p>{{ user.userName }}</p>
    <p>{{ user.id }}</p>
    <p>{{ user.email }}</p>
  </div>
</template>

<script>
export default {
  props: ['user'],

  methods: {
    goToUser() {
      this.$router.push('/user/' + this.user.id);
    }
  }
}
</script>

<style scoped>
.card {
  border: 2px solid grey;
  margin-bottom: 5px;
  border-radius: 7px;
  cursor: pointer;
}
</style>