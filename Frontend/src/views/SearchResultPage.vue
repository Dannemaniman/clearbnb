<template>
  <div v-if="houses" class="content">
    <SearchModal @refined-search="refineSearch" />

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
import SearchModal from '../components/SearchModal.vue';
export default {
  data() {
    return {
      houses: [],
      searchObject: null,
      filteredHouses: null,
    };
  },
  components: { SearchResultItem, SearchModal },
  methods: {
    refineSearch(payload) {
      this.searchObject = payload.searchObject;
      this.filteredHouses = [];
      this.houses.forEach((house) => {
        if (house.price <= searchObject.price && house.price !== null) {
          console.log('Hej');
          filteredHouses.push(house);
        }
      });
    },
  },

  async created() {
    if (!this.filteredHouses) {
      let res = await fetch('/rest/houses');
      let houses = await res.json();

      houses.filter((house) => {
        let city = house.city.toLowerCase();
        let search = this.$store.state.citySearch.toLowerCase();
        if (city.includes(search)) {
          this.houses.push(house);
        }
      });
    } else {
      this.houses = this.filteredHouses;
    }
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
