<template>
  <div class="home-content">
    <Spinner v-if="showSpinner" />
    <div v-else>
      <Typewriter />
      <GalleryComponent />
      <p class="greeting">
        <span>We care!</span> <br />
        We break our backs to serve your every need.
      </p>

      <Slider title="I Omfånget" :houses="houses" />
      <Slider title="Bäst I Betyg" :houses="bestHouses" />
      <Slider title="Billigaste" :houses="cheapestHouses" />
      <MapComponent :inDetail="false" />
    </div>
  </div>
</template>

<script>
import SearchBar from '../components/SearchBar.vue';
import GalleryComponent from '../components/GalleryComponent.vue';
import Slider from '../components/Slider.vue';
import MapComponent from '../components/MapComponent.vue';
import Spinner from '../components/Spinner.vue';
import Typewriter from '../components/Typewriter.vue';

export default {
  components: {
    GalleryComponent,
    SearchBar,
    Slider,
    MapComponent,
    Spinner,
    Typewriter,
  },
  data() {
    return {
      houses: null,
      cheapestHouses: null,
      bestHouses: null,
      showSpinner: true,
    };
  },
  updated() {
    //set houses
  },
  async created() {
    setTimeout(async () => {
      let housesData = await this.$store.state.houses;
      this.cheapestHouses = await this.$store.state.cheapestHouses;
      this.bestHouses = await this.$store.state.bestHouses;
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

.greeting {
  font-size: 3rem;
  font: bolder;
  margin-bottom: 7rem;
  line-height: 100%;
  text-shadow: 2px 2px 0px #ffffff, 2px 2px 0px rgba(0, 0, 0, 0.164);
}

.greeting span {
  font-size: 5rem;
}
</style>
