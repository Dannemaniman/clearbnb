import { createStore } from 'vuex';

export default createStore({
  // this.$store.state.variableName
  state: {
    houses: [],
    users: [],
    selectedHouse: [],
    citySearch: '',
    reviews: [],
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
    setSelectedHouse(state, house) {
      state.selectedHouse = house;
    },
    setCitySearch(state, city) {
      state.citySearch = city;
    },
    setReviews(state, reviews) {
      state.reviews = reviews;
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
    async fetchReviews(store) {
      let res = await fetch('/rest/reviews');
      let reviews = await res.json();

      store.commit('setReviews', reviews);
    },

    /*  async fetchHouseById(store, id) {
      let res = await fetch('/rest/houses/:${id}');
      let house = await res.json();
      store.commit('setSelectedHouse', house);
    }, */
  },
});
