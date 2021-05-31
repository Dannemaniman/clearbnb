<template>
  <div class="home-content">
    <Spinner v-if="showSpinner" />
    <div v-else>
      <GalleryComponent />
      <p class="greeting">
        <span>We care!</span> <br />
        We willingly break our backs to serve your every need. <br /><br />
        <span class="small-information">
        We bend over backwards in order to help you find the most suitable accomodation you can dream of.
        <img src="../../public/icons/successful.png" class="bezel-image"/>
        </span>
      </p>
      <Slider title="All Homes" :houses="houses" />
      <Slider img="../../public/icons/badge.png" title="Best Graded Homes" :houses="bestHouses" />
      <Slider img="../../public/icons/new.png" title="Economically Comfortable Housing" :houses="cheapestHouses" />
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
      cheapestHouses: null,
      bestHouses: null,
      showSpinner: true,
    };
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

.bezel-image {
  height: 40px;
  width: 40px;
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

.greeting .small-information {
  font-size: 2rem;
}

</style>
