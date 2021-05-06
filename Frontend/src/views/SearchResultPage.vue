<template>
  <div v-if="houses" class="content">
    <h1>Search button/bar component</h1>

    <h1>{{ houses.length }} Matched objects</h1>
    <div class="search-results">
      <SearchResultItem
        class="house"
        v-for="house of houses"
        :key="house.id"
        :house="house"
      />
    </div>
    <!-- <p>{{ houses }}</p> -->
  </div>
</template>

<script>
import SearchResultItem from '../components/SearchResultItem.vue';
export default {
  data() {
    return {
      houses: [],
    };
  },
  components: { SearchResultItem },
  methods: {},

  async created() {
    let res = await fetch('/rest/houses');
    let houses = await res.json();

    houses.filter((house) => {
      let city = house.city.toLowerCase();
      let search = this.$store.state.citySearch.toLowerCase();
      if (city.includes(search)) {
        this.houses.push(house);
      }
    });
  },

  /*   created() {
    this.$store.state.houses.filter((house) => {
      let city = house.city.toLowerCase();
      let search = this.$store.state.citySearch.toLowerCase();

      if (city.includes(search)) {
        this.houses.push(house);
      }
    });
  }, */
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
</style>
