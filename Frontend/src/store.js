import { createStore } from 'vuex';

export default createStore({
  state: {
    houses: [],
    cheapestHouses: [],
    bestHouses: [],
    selectedHouse: [],
    user: null,
    citySearch: '',
    reviews: [],
    replies: [],
    bookings: [],
    userBookings: [],
    users: {},
    uploadedNames: '',
    bookedDates: [],
  },

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
    setReplies(state, replies) {
      state.replies = replies;
    },
    setBookedDates(state, bookedDates) {
      state.bookedDates = bookedDates;
    },
    setUsers(state, users) {
      state.users = users;
    },
    setBookings(state, bookings) {
      state.bookings = bookings;
      let userBookings = [];
      if (state.user == null) {
        return;
      } else if (state.user.email === 'admin@admin') {
        state.userBookings = state.bookings;
      } else {
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
    addBestHouses(state, houses) {
      state.bestHouses = houses;
    },
    addCheapestHouses(state, houses) {
      state.cheapestHouses = houses;
    },
    setUsers(state, users) {
      state.users = users;
    },
    deleteBooking(state, id) {
      for (let booking of state.userBookings) {
        if (booking.id == id.id) {
          let index = state.userBookings.indexOf(booking);
          state.userBookings.splice(index, 1);
        }
      }
    },
    addUploadedNames(state, uploadNames) {
      state.uploadedNames = uploadNames;
    },
  },

  actions: {
    async fetchHouses(store) {
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
      store.commit('setUser', loggedInUser);
    },
    async login(store, credentials) {
      let res = await fetch('/api/login', {
        method: 'POST',
        body: JSON.stringify(credentials),
      });
      let loggedInUser = await res.json();
      if ('Error' in loggedInUser) {
        alert('Bad credentials');
        return;
      }
      store.dispatch('fetchBookings', store);
      store.commit('setUser', loggedInUser);
    },
    async whoAmI(store) {
      let res = await fetch('/api/whoami');
      let user = await res.json();
      store.commit('setUser', user);
    },
    async logout(store) {
      await fetch('/api/logout');
      store.commit('setUser', null);
    },
    async fetchUsers(store) {
      let res = await fetch('/rest/users');
      let users = await res.json();
      store.commit('setUsers', users);
    },
    async fetchReviews(store, id) {
      let res = await fetch(`/rest/reviews/${id}`);
      let reviews = await res.json();
      store.commit('setReviews', reviews);
    },

    async fetchReplies(store, id) {
      let res = await fetch(`/rest/replies/${id}`);
      let replies = await res.json();
      store.commit('setReplies', replies);
    },

    async fetchBookedDates(store, id) {
      let res = await fetch(`/rest/house/bookings/${id}`);
      let bookedDates = await res.json();
      store.commit('setBookedDates', bookedDates);
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
      store.commit('addHouses', house);
    },

    async uploadImage(store, images) {
      let uploadResult = await fetch('/api/uploads/', {
        method: 'POST',
        body: images,
      });
      let uploadNames = await uploadResult.json();
      store.commit('addUploadedNames', uploadNames);
    },

    async fetchBestHouses(store) {
      let res = await fetch('/rest/best-houses');
      let info = await res.json();
      store.commit('addBestHouses', info);
    },
    async fetchCheapestHouses(store) {
      let res = await fetch('/rest/cheapest-houses');
      let info = await res.json();
      store.commit('addCheapestHouses', info);
    },

    async updateUser(store, userInfo) {
      let res = await fetch('/rest/users/' + this.state.user.id, {
        method: 'PUT',
        body: JSON.stringify(userInfo),
      });
      let updatedUser = await res.json();
      store.commit('setUser', updatedUser);
    },
    async deleteBooking(store, id) {
      let res = await fetch('/rest/bookings/' + id, {
        method: 'DELETE',
      });

      let ok = await res.text();
    },
    async postReview(store, review) {
      let res = await fetch('/rest/post-review', {
        method: 'POST',
        body: JSON.stringify(review),
      });
    },

    async postReply(store, review) {
      let res = await fetch('/rest/post-reply', {
        method: 'POST',
        body: JSON.stringify(review),
      });
    },
  },
});
