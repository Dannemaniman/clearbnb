<template>
  <div class="card">
    <h3>BookingID: {{ booking.id }}</h3>
    <h4>BookerID: {{ booking.bookerId }}</h4>
    <p>HouseID: {{ booking.houseId }}</p>
    <p>Dates: {{ booking.chosenDate }}</p>
    <button @click="deleteBooking(this.booking.id)">X</button>
  </div>
</template>

<script>
export default {
  props: ['booking'],
  methods: {
    deleteBooking(id) {
      console.log(id);
      this.$store.dispatch('deleteBooking', id);
    },
  },
};
</script>

<style>
.card {
  border-radius: 10px;
  border: 1px solid black;
  background-color: rgb(235, 235, 235);
  color: black;
  margin: 10px;
}
</style>
