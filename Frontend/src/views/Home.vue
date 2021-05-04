<template>
  <div class="home-content">
    <SearchBar />
    <GalleryComponent />
    <div class="slider"><h1>SLAAAJDEER</h1></div>
    <div class="slider"><h1>SLAAAJDEER</h1></div>
  </div>
</template>

<script>
import SearchBar from '../components/SearchBar.vue';
import GalleryComponent from '../components/GalleryComponent.vue';

export default {
  components: {
    GalleryComponent,
    SearchBar,
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

.slider {
  margin: 5rem 0;
  color: #4b4b4b;
  font-size: 4rem;
  background-color: #a9a9a9;
  padding: 3rem 0;
  max-width: 100%;
}
</style>
