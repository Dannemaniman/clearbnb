<template>
  <div v-if="houses" class="content">
    <SearchModal @refined-search="gettingSearchObject" />
    <h1>{{ refinedSearchResult.length }} Matched objects</h1>
    <div class="search-results">
      <SearchResultItem
        class="house"
        v-for="house of refinedSearchResult"
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
      housesByCity: [],
      refinedSearchResult: [],
    };
  },
  computed: {
    searchResult: function () {
      return this.searchByPrice(
        this.searchByProperty(
          this.searchByBeds(
            this.searchByAmenities(this.searchByReview(this.housesByCity))
          )
        )
      );
    },
  },
  components: { SearchResultItem, SearchModal },
  methods: {
    searchByPrice(houses) {
      if (this.searchObject.price == '') {
        return houses;
      }
      let arr = [];
      houses.forEach((house) => {
        if (house.price <= this.searchObject.price) {
          arr.push(house);
        }
      });
      return arr;
    },
    searchByProperty(houses) {
      if (this.searchObject.property == '') {
        return houses;
      }
      let arr = [];
      houses.forEach((house) => {
        if (house.propertyType == this.searchObject.property) {
          arr.push(house);
        }
      });
      return arr;
    },
    searchByBeds(houses) {
      if (this.searchObject.beds == 0) {
        return houses;
      }
      let arr = [];
      houses.forEach((house) => {
        if (house.accommodation.beds >= this.searchObject.beds) {
          arr.push(house);
        }
      });
      return arr;
    },
    searchByAmenities(houses) {
      if (this.searchObject.amenities.length == 0) {
        return houses;
      }
      let arr = [];
      houses.forEach((house) => {
        if (
          this.searchObject.amenities.every((elem) =>
            house.amenities.includes(elem)
          )
        ) {
          arr.push(house);
        }
      });
      return arr;
    },
    searchByReview(houses) {
      if (this.searchObject.review == '') {
        return houses;
      }
      let arr = [];
      houses.forEach((house) => {
        if (house.review >= this.searchObject.review) {
          arr.push(house);
        }
      });
      return arr;
    },
    gettingSearchObject(payload) {
      this.searchObject = payload.searchObject;
      this.refinedSearchResult = this.searchResult;
    },
    setHouseReviews() {
      this.$store.state.houses.forEach(async (house) => {
        await this.$store.dispatch('fetchReviews', house.id);

        if (this.$store.state.reviews.length == 0) {
          return '';
        }
        let total = 0;
        this.$store.state.reviews.forEach(function (review) {
          let grade = review.grade;
          total += grade;
        });
        let average = total / this.$store.state.reviews.length;
        let rounded = average.toFixed(2);
        house.review = Number(rounded);
      });
      console.log(this.$store.state.houses);
    },
  },

  async created() {
    await this.$store.dispatch('fetchHouses');
    this.setHouseReviews();
    /* let res = await fetch('/rest/houses');
    let houses = await res.json(); */
    let houses = this.$store.state.houses;
    console.log(houses);

    houses.filter((house) => {
      let city = house.city.toLowerCase();
      let search = this.$store.state.citySearch.toLowerCase();
      if (city.includes(search)) {
        this.houses.push(house);
      }
    });
    this.refinedSearchResult = this.houses;
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
