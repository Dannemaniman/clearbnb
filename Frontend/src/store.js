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
    userBookings: [],
    users: {},
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
    setUsers(state, users) {
      state.users = users;
    },
    setBookings(state, bookings) {
      state.bookings = bookings;
      if (state.user == null) {
        return;
      } else {
        let userBookings = [];
        for (let booking of state.bookings) {
          if (state.user.id == booking.bookerId) {
            userBookings.push(booking);
          }
        }
        state.userBookings = userBookings;
      }
    },
    addBooking(state, booking) {
      state.bookings.push(booking);
    },
    deleteBooking(state, newMessage) {
      for (let booking of state.userBookings) {
        if (booking.id == newMessage.id) {
          let index = state.userBookings.indexOf(booking);
          state.userBookings.splice(index, 1);
        }
      }
    },
  },

  // this.$store.dispatch('actionNamehouses)s
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
      if ('Error' in loggedInUser) {
        console.log('Detta blidde inte bra', loggedInUser);
        alert('Bad credentials');
        return;
      }
      console.log('logged in user', loggedInUser);
      store.commit('setUser', loggedInUser);
    },
    async whoAmI(store) {
      let res = await fetch('/api/whoami');
      let user = await res.json();
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
    async book(store, confirmedBooking) {
      let res = await fetch('/rest/bookings', {
        method: 'POST',
        body: JSON.stringify(confirmedBooking),
      });

      let booking = await res.json();
      store.commit('addBooking', booking);
    },
    async storeHome(store, house) {
      store.commit('setSelectedHouse', house);
    },
    async createHouse(store, hostObject) {
      let res = await fetch('/rest/houses', {
        method: 'POST',
        body: JSON.stringify(hostObject),
      });

      let house = await res.json();
      console.log('You created', house);
      store.commit('addHouses', house);
    },

    async getSliderInfo() {
      let res = await fetch('/rest/best-houses');
      let info = await res.json();
    },

    async updateUser(store, userInfo) {
      console.log(userInfo);
      // let res = await fetch('/rest/user/:id', {
      //   method: 'PUT',
      //   body: JSON.stringify(userInfo)
      // })
    },
    async deleteBooking(store, id) {
      let res = await fetch('/rest/bookings/' + id, {
        method: 'DELETE',
      });

      let ok = await res.text();
      // console.log('Delete of', ok);
      // store.commit('deleteBooking', id);
    },
    async postReview(store, review) {
      let res = await fetch('/rest/post-review', {
        method: 'POST',
        body: JSON.stringify(review),
      });
    },
  },
});
