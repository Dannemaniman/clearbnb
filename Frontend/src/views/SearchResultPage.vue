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
      housesByCity: [],
      restoringHousesByCity: [],
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
      if (this.searchObject.price == null) {
        return houses;
      }
      console.log('hej');
      return houses.filter((house) => {
        house.price <= this.searchObject.price;
      });
    },
    searchByProperty(houses) {
      if (this.searchObject.property == null) {
        return houses;
      }
      return houses.filter((house) => {
        house.propertyType.includes(this.searchObject.property);
      });
    },
    searchByBeds(houses) {
      console.log('beds');
      if (this.searchObject.beds == null) {
        return houses;
      }
      return houses.filter((house) => {
        house.beds >= this.searchObject.beds;
      });
    },
    searchByAmenities(houses) {
      console.log('amenities');
      if (this.searchObject.amenities == null) {
        return houses;
      }
      houses.filter((house) => {
        this.searchObject.amenities.every((amenity) =>
          house.amenities.includes(amenity)
        );
      });
    },
    searchByReview(houses) {
      if (this.searchObject.review == null) {
        return houses;
      }
      return houses.filter((house) => {
        house.reviews >= this.searchObject.review;
      });
    },
    gettingSearchObject(payload) {
      this.searchObject = payload.searchObject;
      console.log(this.searchObject);
      /* Kalla på metoderna här? */
      console.log(this.searchResult);
      this.housesByCity = searchResult;
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
