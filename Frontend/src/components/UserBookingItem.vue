<template>
  <div class="card">
    <h3>BookingID: {{ userBooking.id }}</h3>
    <h4>BookerID: {{ userBooking.bookerId }}</h4>
    <p>HouseID: {{ userBooking.houseId }}</p>
    <p>Dates: {{ userBooking.chosenDate }}</p>
    <button @click="deleteBooking(this.userBooking.id)">X</button>
  </div>
</template>

<script>
export default {
  props: ['userBooking'],
  methods: {
    deleteBooking(id) {
      console.log(id);
      this.$store.dispatch('deleteBooking', id);
      // window.location.reload();
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
