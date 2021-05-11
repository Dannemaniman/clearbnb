import { createStore } from 'vuex';

export default createStore({
  // this.$store.state.variableName
  state: {
    houses: [],
    selectedHouse: [],
    user: null,
    citySearch: '',
    reviews: [],
    bookings: [],
  },

  // this.$store.commit('mutationName', data)
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
    setUser(state, user) {
      state.user = user;
    },
    setCitySearch(state, city) {
      state.citySearch = city;
    },
    setReviews(state, reviews) {
      state.reviews = reviews;
    },
    setBookings(state, bookings) {
      state.bookings = bookings;
    },
    addBooking(state, booking) {
      state.bookings.push(booking);
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
    async fetchBookings(store) {
      let res = await fetch('/rest/bookings');
      let bookings = await res.json();

      store.commit('setBookings', bookings);
    },
    async book(store, info) {
      let res = await fetch('/rest/bookings', {
        method: 'POST',
        body: JSON.stringify(info),
      });

      let booking = await res.json();

      console.log('You booked', booking);

      store.commit('addBooking', booking);
    },

    /*  async fetchHouseById(store, id) {
      let res = await fetch('/rest/houses/:${id}');
      let house = await res.json();
      store.commit('setSelectedHouse', house);
    }, */
  },
});
