<template>
  <div class="detail-content">
    <h1>{{ home.title }}</h1>
    <img :src="home.images" alt="" />
    <h2>{{ home.address }}</h2>
    <h2>{{ home.description }}</h2>
    <Amenities :amenities="home.amenities" />
  </div>
</template>

<script>
import Amenities from '../components/Amenities.vue';
export default {
  components: { Amenities },
  data() {
    return {
      home: null,
    };
  },
  /*  computed: {
    home() {
      return this.$store.state.selectedHouse;
    },
  }, */
  beforeMount() {
    for (let home of this.$store.state.houses) {
      if (home.id == this.$route.params.id) {
        this.home = home;
      }
    }
  },
  /*  async created() {
    let id = this.$route.params.id;
    const response = await fetch(`/rest/houses/${id}`);
    const data = await response.json();
    this.home = data;
  }, */
};
</script>

<style></style>
