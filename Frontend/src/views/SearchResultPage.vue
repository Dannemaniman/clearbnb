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
    /* Får in housesByCity från searchBar / create.
      Köra arrayen genom alla filter och sedan rendera.
      price, property, beds, amenities, reviews.
       */

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
      console.log(arr);
      return arr;
    },
    searchByBeds(houses) {
      if (true) {
        return houses;
      }
      return houses.filter((house) => {
        house.beds >= this.searchObject.beds;
      });
    },
    searchByAmenities(houses) {
      if (true) {
        return houses;
      }
      houses.filter((house) => {
        this.searchObject.amenities.every((amenity) =>
          house.amenities.includes(amenity)
        );
      });
    },
    searchByReview(houses) {
      if (this.searchObject.review == '') {
        return houses;
      }
      let arr = [];
      houses.forEach((house) => {
        if (house.reviews >= this.searchObject.review) {
          arr.push(house);
        }
      });
      return arr;
    },
    gettingSearchObject(payload) {
      this.searchObject = payload.searchObject;
      console.log(this.searchObject);
      /* Kalla på metoderna här? */

      console.log(this.searchResult);
      this.refinedSearchResult = this.searchResult;
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
