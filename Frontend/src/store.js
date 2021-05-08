import { createStore } from 'vuex';

export default createStore({
  state: {
    houses: [],
    searchlocation : '',
  },

  // this.$store.commit('mutationName')
  mutations: {
    setHouses(state, houses) {
      state.houses = houses;
    },
    addHouses(state, house) {
      state.houses.push(house);
    },
      searchCity(state, city) {
      state.searchlocation = city;
    },
  },

  // this.$store.dispatch('actionName')
  actions: {
    async fetchHouses(store) {
      // fetch recipes and update
      // state with response
      // store.commit('setRecipes')

      let res = await fetch('/rest/houses');
      let houses = await res.json();

      store.commit('setHouses', houses);
    },
  },
});
