

<template>

  <div v-if="houses" class="content">
    <div class="search-results">
      <SearchItem
        class="house"
        v-for="house of houses"
        :key="house.id"
        :house="house"
      />
    </div>
   
  </div>
</template>

<script>
import SearchItem from '../components/SearchItem.vue';
export default {
  data() {
    return {
      house: null,
      houses: [],
    };
  },
  components: {
     SearchItem 
     },
  

   async created() {
    let res = await fetch('/rest/houses');
   let houses = await res.json();

    houses.filter((house) => {
      let city = house.city.toLowerCase();
      let search = this.$store.state.searchlocation.toLowerCase();


      if (city.includes(search)) {
        this.houses.push(house);
      }
    });
  },
}

</script>

<style scoped>

</style>
