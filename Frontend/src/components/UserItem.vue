<template>
  <div class="card">
    <img v-bind:src="user.image" alt="">
    <h3>{{ user.fullName }}</h3>
    <p>{{ user.userName }}</p>
    <p>{{ user.id }}</p>
    <p>{{ user.email }}</p>
  </div>
</template>

<script>
export default {
  props: ['user']
}
</script>

<style>

</style>