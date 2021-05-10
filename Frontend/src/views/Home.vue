<template>
  <div class="home-content">
    <GalleryComponent :houses="houses"/>
    <div class="slider"><h1>SLAAAJDEER</h1></div>
    <div class="slider"><h1>SLAAAJDEER</h1></div>
    <Slider :images='houses' />
    <booking-modal></booking-modal>
  </div>
</template>

<script>
import SearchBar from '../components/SearchBar.vue';
import GalleryComponent from '../components/GalleryComponent.vue';
import Slider from "../components/Slider.vue"
import BookingModal from "../components/BookingModal.vue"

export default {
  components: {
    GalleryComponent,
    SearchBar,
    Slider,
    BookingModal
  },
  data() {
    return {
      houses: ["../../public/images/House1.jpg", "../../public/images/House2.jpg", "../../public/images/House3.jpg", "../../public/images/House4.jpg", "../../public/images/House5.jpg", "../../public/images/House6.jpg", "../../public/images/House7.jpg", "../../public/images/House8.jpg"]
    }
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
