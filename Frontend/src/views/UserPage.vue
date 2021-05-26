<template>
  <section v-if="loggedIn !== null" class="user-content">
    <div class="buttons">
      <button @click="openPage(1)">Bookings</button>
      <button @click="openPage(2)">Houses</button>
      <button @click="openPage(3)">Details</button>
    </div>
    <div>
      <UserBookings v-if="bookings" />
    </div>
    <div>
      <UserHouses :userObjects="userObjects" v-if="houses" />
    </div>
    <div>
      <UserDetails v-if="details" />
    </div>
  </section>
</template>

<script>
import UserBookings from '../components/UserBookings.vue';
import UserHouses from '../components/UserHouses.vue';
import UserDetails from '../components/UserDetails.vue';

export default {
  components: {
    UserBookings,
    UserHouses,
    UserDetails,
  },

  data() {
    return {
      bookings: false,
      houses: false,
      details: true,
      userObjects: '',
    };
  },
  computed: {
    loggedIn() {
      this.$store.state.user ? true : this.$router.push('/');
    },
  },
  methods: {
    openPage(value) {
      switch (value) {
        case 1: {
          this.bookings = true;
          this.houses = false;
          this.details = false;
          break;
        }
        case 2: {
          this.bookings = false;
          this.houses = true;
          this.details = false;
          this.getUserHouses();
          break;
        }
        case 3: {
          this.bookings = false;
          this.houses = false;
          this.details = true;
          break;
        }
      }
    },
    getUserHouses() {
      let userID = this.$store.state.user.id;
      let houses = this.$store.state.houses;
      let userHouses = [];
      for (let house of houses) {
        if (userID == house.ownerId) {
          userHouses.push(house);
        }
      }
      this.userObjects = userHouses;
    },
  },
  created() {
    //this.$store.dispatch('fetchBookings');
  },
};
</script>

<style scoped>
.user-content {
  display: flex;
  flex-direction: column;
}

button {
  width: 33.33%;
  height: 6rem;
  border-radius: 0px 0px 10px 10px;
  outline: none;
  font-weight: 400;
  cursor: pointer;
  background-color: rgb(235, 235, 235);
  transition: all 0.2s ease;
}

button:hover {
  box-shadow: rgba(0, 0, 0, 0.35) 0px 5px 15px;
  background-color: #a9a9a9;
}

button:active {
  background-color: #a9a9a9;
}
</style>
