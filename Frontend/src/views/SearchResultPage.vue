<template>
  <div v-if="houses" class="content">
    <SearchModal @refined-search="gettingSearchObject" />

    <h1>{{ housesByCity.length }} Matched objects</h1>
    <div class="search-results">
      <SearchResultItem
        class="house"
        v-for="house of housesByCity"
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
      filteredHouses: [],
      housesByCity: null,
      refinedSearch: null,
    };
  },
  computed: {},
  components: { SearchResultItem, SearchModal },
  methods: {
    searchByPrice() {
      this.housesByCity.forEach((house) => {
        if (house.price <= this.searchObject.price) {
          this.filteredHouses.push(house);
        } else if (this.searchObject.price == null) {
          this.filteredHouses.push(house);
        }
        this.housesByCity = this.filteredHouses;
      });
    },
    searchByProperty() {},
    searchByBeds() {},
    searchByAmenities() {},
    searchByReview() {
      console.log(this.housesByCity);
      this.refinedSearch = [];
      this.filteredHouses = this.housesByCity;
      this.filteredHouses.forEach((house) => {
        if (house.reviews >= this.searchObject.review) {
          this.refinedSearch.push(house);
        } else if (this.searchObject.review == null) {
          this.refinedSearch.push(house);
        }
      });
      console.log(this.refinedSearch);
    },
    gettingSearchObject(payload) {
      this.searchObject = payload.searchObject;
      this.searchByPrice();
    },
  },

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
    this.housesByCity = this.houses;
    /* else {
      this.houses = houses;
    } */
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
</style>
