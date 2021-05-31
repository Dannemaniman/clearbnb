<template>
  <div class="booking-card">
    <UserHouseItem
      v-for="(house, index) of userHouses"
      v-bind:key="index"
      v-bind:house="house"
    />
    <h3>Start: {{ startDate }}</h3>
    <h3>End: {{ endDate }}</h3>
    <p>BookingID: {{ userBooking.id }}</p>
    <button class="cancel" @click="deleteBooking(this.userBooking.id)">
      X
    </button>
  </div>
</template>

<script>
import UserHouseItem from './UserHouseItem.vue';
export default {
  props: ['userBooking'],

  components: {
    UserHouseItem,
  },

  data() {
    return {
      userHouses: [],
      startDate: new Date(this.userBooking.chosenDate[0]).toLocaleDateString(),
      endDate: new Date(this.userBooking.chosenDate[1]).toLocaleDateString(),
    };
  },

  created() {
    let houses = [];
    for (let house of this.$store.state.houses) {
      if (house.id == this.userBooking.houseId) {
        houses.push(house);
      }
    }
    this.userHouses = houses;
  },

  methods: {
    deleteBooking(id) {
      this.$store.dispatch('deleteBooking', id);
    },
  },
};
</script>

<style>

.cancel {
  height: 20px;
  margin: 10px;
}
</style>
