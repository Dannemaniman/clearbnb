<template>
  <div class="home-content">
    <GalleryComponent />
    <p>Nära dig</p>
    <Slider :images="houses2" />
    <p>I Omfånget</p>
    <Slider :images="houses2" />
    <p> Våra Förslag</p>
    <Slider :houses="houses" />
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
      houses2: [
        '../../public/images/House1.jpg',
        '../../public/images/House2.jpg',
        '../../public/images/House3.jpg',
        '../../public/images/House4.jpg',
        '../../public/images/House5.jpg',
        '../../public/images/House6.jpg',
        '../../public/images/House7.jpg',
        '../../public/images/House8.jpg',
      ],
    };
  },
  computed: {
    houses() {
      return this.$store.state.houses;
    },
  },
  methods: {
    searchByLocation() {},
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
