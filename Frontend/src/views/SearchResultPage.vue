<template>
  <div class="content">
    <h1>Search button/bar component</h1>

    <h1>{{ houses.length }} Matched objects</h1>
    <div class="search-results">
      <div
        @click="goToDetails"
        class="house"
        v-for="house of houses"
        :key="house.city"
        :house="house"
      >
        <article @click="goToDetails">
          <img :src="house.images[0]" />
          <h2>{{ house.propertyType }}</h2>
          <h3>Review 3/5 (39 reviews)</h3>
          <h2>{{ house.city }}</h2>
          <h3>{{ house.address }}</h3>
        </article>
      </div>
    </div>
    <!-- <p>{{ houses }}</p> -->
  </div>
</template>

<script>
import GalleryComponentItem from '../components/GalleryComponentItem.vue';
export default {
  data() {
    return {
      houses: [],
      house: '',
    };
  },
  components: { GalleryComponentItem },
  computed: {},
  methods: {
    goToDetails() {
      this.$router.push('/house/' + this.house.id);
    },
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

<style scoped>
h1 {
  margin-top: 5rem;
}
.search-results {
  display: flex;
  flex-wrap: wrap;
  padding: 10px;
  justify-content: space-around;
  margin-top: 4rem;
}
.house {
  width: 45%;
}
img {
  width: 100%;
  height: 25rem;
  border-radius: 2rem;
}
</style>
