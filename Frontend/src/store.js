import { createStore } from 'vuex';

export default createStore({
  // this.$store.state.variableName
  state: {
    homes: [],
  },

  // this.$store.commit('mutationName', data)
  mutations: {
    setHomes(state, homes) {
      state.homes = homes;
    },
    addHome(state, home) {
      state.homes.push(home);
    }
  },

  // this.$store.dispatch('actionName')
  actions: {
    async fetchHomes(store) {
      // fetch homes and update state with response
      // store.commit('setRecipes')

      let res = await fetch('/rest/homes');
      let homes = await res.json();
      
      store.commit('setHomes', homes);
    }
  },
});
