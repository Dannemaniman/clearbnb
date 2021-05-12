import { createStore } from 'vuex';

export default createStore({
  state: {
    houses: [],
    searchlocation: '',
    user: null
   
   
  },
  
  getters: {
    user: (state) => {
     return state.user
    }
    
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
    setUser(state, user) {
      state.user = user
    },
    
   
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

        // remove user from state
        store.commit('setUser', null)
      },

      async user(store) {
        let res = await fetch('/api/whoami', {
           method: 'GET',
          headers: { 'Content-Type': 'application/json' },
          credentials: 'include',
        })
        let user = await res.json()
        console.log(user);

        store.commit('setUser', user)
      },

      async register(store, credentials) {
        let res = await fetch('/api/register', {
          method: 'POST',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify(credentials)
        })

        let loggedInUser = await res.json()

        console.log('registered user', loggedInUser)

        store.commit('setUser', loggedInUser)
      },

      async login(store, credentials) {
        let res = await fetch('/api/login', {
          method: 'POST',
          headers: { 'Content-Type': 'application/json' },
          credentials: 'include',
          body: JSON.stringify(credentials)
        })

        let loggedInUser = await res.json()

        console.log('logged in user', loggedInUser)

        store.commit('setUser', loggedInUser)
      }
    }
  });
