<template>
  <div class="home-content">
    <Spinner v-if="showSpinner" />
    <div v-else>
      <GalleryComponent />
      <p>Nära dig</p>
      <!--  <Slider :houses="houses" />
    <p>I Omfånget</p> -->
      <Slider :houses="houses" />
      <p>Våra Förslag</p>
      <Slider :houses="houses" />
      <MapComponent />
    </div>
  </div>
</template>

<script>
import SearchBar from '../components/SearchBar.vue';
import GalleryComponent from '../components/GalleryComponent.vue';
import Slider from '../components/Slider.vue';
import MapComponent from '../components/MapComponent.vue';
import Spinner from '../components/Spinner.vue';

export default {
  components: {
    GalleryComponent,
    SearchBar,
    Slider,
    MapComponent,
    Spinner,
  },
  data() {
    return {
      houses: null,
      showSpinner: true,
    };
  },
  async created() {
    setTimeout(async () => {
      let housesData = await this.$store.state.houses;
      this.houses = housesData;
      this.showSpinner = false;
    }, 1000);
  },
};
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
