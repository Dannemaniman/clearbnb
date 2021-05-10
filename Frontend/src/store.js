import { createStore } from 'vuex';

export default createStore({
  state: {
    houses: [],
    searchlocation: '',
    user: null,
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
    setUser(state, user){
  state.user = user
      }
  },

  // this.$store.dispatch('actionName')
  actions: {

    async fetchHouses(store) {
      let res = await fetch('/rest/houses');
      let houses = await res.json();

      store.commit('setHouses', houses);
    },



   async logout(store) {
       let res = await fetch('/api/logout')
      
        store.commit('setUser', null);
        
    },

     async whoami(store) {
       let res = await fetch('/api/whoami')
       let user = await res.json()
       
        store.commit('setUser', user);
        
    },
     
   


    async register(store, credentails) {
      let res = await fetch('/api/register', {
        method: 'POST',
        body: JSON.stringify(credentails)
      });
      let loggedInUser = await res.json()
     
      store.commit('setUser', loggedInUser);
    },


     async login(store, credentails) {
      let res = await fetch('/api/login', {
        method: 'POST',
        body: JSON.stringify(credentails)
      });
      let loggedInUser = await res.json()
       
       store.commit('setUser', loggedInUser);
    }
  },
});
