<template>
  <div class="home-content">
    <GalleryComponent />
    <p>Nära dig</p>
    <!-- <Slider :houses="houses" />
    <p>I Omfånget</p>
    <Slider :houses="houses" />
    <p> Våra Förslag</p>
    <Slider :houses="houses" /> -->
  </div>
</template>

<script>
import SearchBar from '../components/SearchBar.vue';
import GalleryComponent from '../components/GalleryComponent.vue';
import Slider from '../components/Slider.vue';

export default {
  components: {
    GalleryComponent,
    SearchBar,
    Slider,
  },
  data() {
    return {
      houses: null,
    };
  },
  async created() {
      setTimeout((async () => {
        let housesData = await this.$store.state.houses
        this.houses = housesData
      }), 1000)
  },
}
</script>

<style scoped>
.home-content {
  padding: 0 0.5rem;
}

p {
  font-size: 2rem; 
  color: black;
  font-weight: thin;
}
</style>
