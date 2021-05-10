<template>
  <form @submit.prevent="searchByLocation">
    <input v-model="searchCity" type="text" placeholder="Enter location..." />
    <button class="btn">Search</button>
  </form>
</template>

<script>
export default {
  data() {
    return {
      searchCity: '',
    };
  },
  methods: {
    searchByLocation() {
      let searchCity = this.searchCity;
      this.$store.commit('setCitySearch', searchCity);
      this.$router.push('/search-results');
    },
  },
};
</script>

<style scoped>
form {
  margin-top: 4rem;
}
input {
  display: inline-block;
  padding: 0.3em 1.2em;
  margin-right: 1rem;
  border: 0.16em solid rgba(255, 255, 255, 0);
  border-radius: 1rem;
  box-sizing: border-box;
  text-decoration: none;
  font-weight: 300;
  color: #808080;
  transition: all 0.2s;
}
.btn {
  display: inline-block;
  padding: 0.3em 0.4em;
  margin: 0 0.1em 0.1em 0;
  border: 0.16em solid rgba(255, 255, 255, 0);
  border-radius: 7px;
  box-sizing: border-box;
  text-decoration: none;
  font-weight: 300;
  color: #808080;
  text-align: center;
  transition: all 0.2s;
}
.btn:hover {
  border-color: #4b4b4b;
}
</style>
