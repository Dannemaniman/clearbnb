<template>
  <GalleryComponent />
  <div class="slider"><h1>SLAAAJDEER</h1></div>
  <div class="slider"><h1>SLAAAJDEER</h1></div>
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
.slider {
  margin: 5rem 0;
  color: #4b4b4b;
  font-size: 4rem;
  background-color: #a9a9a9;
  padding: 3rem 0;
  max-width: 100%;
}
</style>
