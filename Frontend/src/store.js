import { createStore } from 'vuex';

export default createStore({
  state: {
    homes: [],
  },

  // this.$store.commit('mutationName')
  mutations: {
    setHomes(state, homes) {
      state.homes = homes;
    },
    addHomes(state, homes) {
      state.homes.push(homes);
    },
  },

  // this.$store.dispatch('actionName')
  actions: {
    async fetchHomes(store) {
      // fetch recipes and update
      // state with response
      // store.commit('setRecipes')

      let res = await fetch('/rest/homes');
      let homes = await res.json();
      
      store.commit('setHomes', homes);
    },
  },
});
