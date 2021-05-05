<template>
  <div class="content">
    <h1>Search Results!</h1>
    <!-- <GalleryComponentItem v-for="house of houses" :key="house.city" /> -->
    <p>{{ houses }}</p>
  </div>
</template>

<script>
import GalleryComponentItem from '../components/GalleryComponentItem.vue';
export default {
  data() {
    return {
      houses: [],
    };
  },
  components: { GalleryComponentItem },
  computed: {
    /*  houses() {
      return this.$store.state.houses;
    }, */
    /*  houses() {
      if (this.$store.state.citySearch) {
        return this.$store.state.houses.filter((house) => {
          let city = house.city.toLowerCase();
          let search = this.$store.state.citySearch.toLowerCase();

          if (city.includes(search)) {
            return house;
          }
        });
      }
    }, */
  },
  created() {
    this.$store.state.houses.filter((house) => {
      let city = house.city.toLowerCase();
      let search = this.$store.state.citySearch.toLowerCase();

      if (city.includes(search)) {
        this.houses.push(house);
      }
    });
  },
};
</script>

<style></style>
