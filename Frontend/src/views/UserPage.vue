<template>
  <section v-if="loggedIn !== null" class="user-content">
    <div class="buttons">
      <button @click="openPage(1)" :class="{active: activeButton1 === true}">Bookings</button>
      <button @click="openPage(2)" :class="{active: activeButton2 === true}">Houses</button>
      <button @click="openPage(3)" :class="{active: activeButton3 === true}">Details</button>
    </div>
    <div>
      <UserBookings v-if="bookings"/>
    </div>
    <div>
      <UserHouses :userObjects="userObjects" v-if="houses"/>
    </div>
    <div>
      <UserDetails v-if="details"/>
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
      activeButton1: false,
      activeButton2: false,
      activeButton3: false
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
          this.activeButton1 = true;
          this.activeButton2 = false;
          this.activeButton3 = false;
          break;
        }
        case 2: {
          this.bookings = false;
          this.houses = true;
          this.details = false;
          this.getUserHouses();
          this.activeButton1 = false;
          this.activeButton2 = true;
          this.activeButton3 = false;
          break;
        }
        case 3: {
          this.bookings = false;
          this.houses = false;
          this.details = true;
          this.activeButton1 = false;
          this.activeButton2 = false;
          this.activeButton3 = true;
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
};
</script>

<style scoped>

.active {
  background-image: url('https://www.getaccept.com/hubfs/Product%20pages%202.0/Product%20tour/bottom%20wave.svg');
  background-repeat: no-repeat;
  background-size: contain;
  background-size: cover;
  filter:brightness(130%);
  background-color: transparent;
}

.user-content {
  display: flex;
  flex-direction: column;
}

button {
  width: 30.33%;
  height: 6rem;
  border-radius: 0px 0px 10px 10px;
  outline: none;
  font-weight: 400;
  cursor: pointer;
  border: 0;
  background-color: rgb(235, 235, 235);
  transition: all 0.2s ease;
  margin: auto 0.3rem;
  margin-top: 2rem;
}

button:hover, button:active {
  box-shadow: rgba(143, 143, 143, 0.35) 0px 5px 15px;
  background-color: #cfcfcf;
}

</style>
