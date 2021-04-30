<template>
  <div class="home-content">
    <h1>Clear-BnB</h1>
    <form @submit.prevent="searchByLocation">
      <input type="text" placeholder="Enter location..." />
      <button type="submit" class="btn">Search</button>
    </form>
    <GalleryComponent />
    <div class="slider"><h1>SLAAAJDEER</h1></div>
    <div class="slider"><h1>SLAAAJDEER</h1></div>
  </div>
</template>

<script>
import GalleryComponent from '../components/GalleryComponent.vue';
export default {
  components: {
    GalleryComponent,
  },
  methods: {
    searchByLocation() {},
  },
};
</script>

<style scoped>
.home-content {
  padding: 0 0.5rem;
}
h1 {
  margin-top: 6rem;
}
form {
  margin-top: 4rem;
}
.slider {
  margin: 5rem 0;
  color: #4b4b4b;
  font-size: 4rem;
  background-color: #a9a9a9;
  padding: 3rem 0;
  max-width: 100%;
}
</style>
