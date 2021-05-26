<template>
  <div class="card">
    <router-link class="house" :to="/house/ + house.id">
      <img v-bind:src="house.images[0]" alt="" />
    </router-link>
    <h3>{{ house.title }}</h3>
    <p>{{ house.address }}</p>
  </div>
</template>

<script>
export default {
  props: ['house'],
};
</script>

<style scoped>
.card {
  border-radius: 10px;
  border: 1px solid black;
  background-color: rgb(235, 235, 235);
  color: black;
  margin: 10px;
}

img {
  width: 150px;
  height: 150px;
  border-radius: 10px;
  margin-top: 1rem;
}
</style>
