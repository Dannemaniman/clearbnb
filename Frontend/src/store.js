import { createStore } from 'vuex';

export default createStore({
  // this.$store.state.variableName
  state: {
    houses: [],
    users: [],
    selectedHouse: [],
    user: null,
  },

  // this.$store.commit('mutationName', data)
  mutations: {
    setHouses(state, houses) {
      state.houses = houses;
    },
    addHouses(state, house) {
      state.houses.push(house);
    },
    setUsers(state, users) {
      state.users = users;
    },
    addUser(state, user) {
      state.users.push(user);
    },
    setUser(state, user) {
      state.user = user;
    },
    setSelectedHouse(state, house) {
      state.selectedHouse = house;
    },
  },

  // this.$store.dispatch('actionNamehouses
  actions: {
    async fetchHouses(store) {
      // fetch house and update state with response
      // store.commit('setHouse')
      let res = await fetch('/rest/houses');
      let houses = await res.json();

      store.commit('setHouses', houses);
    },
    async fetchUsers(store) {
      let res = await fetch('/rest/users');
      let users = await res.json();

      store.commit('setUsers', users);
    },

    async register(store, credentials) {
      let res = await fetch('/api/register', {
        method: 'POST',
        body: JSON.stringify(credentials),
      });

      let loggedInUser = await res.json();

      console.log('registered user', loggedInUser);

      store.commit('setUser', loggedInUser);
    },
    async login(store, credentials) {
      let res = await fetch('/api/login', {
        method: 'POST',
        body: JSON.stringify(credentials),
      });

      let loggedInUser = await res.json();

      console.log('logged in user', loggedInUser);

      store.commit('setUser', loggedInUser);
    },
    async whoAmI(store) {
      let res = await fetch('/api/whoami');
      let user = await res.json();
      console.log(user);
      store.commit('setUser', user);
    },
    async logout(store) {
      let res = await fetch('/api/logout');
      console.log('logged out');
      store.commit('setUser', null);
    },

    /*  async fetchHouseById(store, id) {
      let res = await fetch('/rest/houses/:${id}');
      let house = await res.json();
      store.commit('setSelectedHouse', house);
    }, */
  },
});
