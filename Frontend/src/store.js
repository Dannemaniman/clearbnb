import { createStore } from 'vuex';

export default createStore({
  state: {
    houses: [],
    selectedHouse: [],
  },

  // this.$store.commit('mutationName')
  mutations: {
    setHouses(state, houses) {
      state.houses = houses;
    },
    addHouses(state, house) {
      state.houses.push(house);
    },
    setSelectedHouse(state, house) {
      state.selectedHouse = house;
    },
  },

  // this.$store.dispatch('actionName')
  actions: {
    async fetchHouses(store) {
      // state with response
      // store.commit('setRecipes')
      let res = await fetch('/rest/homes');
      let houses = await res.json();
      store.commit('setHouses', houses);
    },

    /*  async fetchHouseById(store, id) {
      let res = await fetch('/rest/houses/:${id}');
      let house = await res.json();
      store.commit('setSelectedHouse', house);
    }, */
  },
});
