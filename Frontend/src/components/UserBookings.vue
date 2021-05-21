<template>
  <UserBookingItem
    v-for="(userBooking, index) of userBookings"
    v-bind:key="index"
    v-bind:userBooking="userBooking"
  />
</template>

<script>
import UserBookingItem from './UserBookingItem.vue';
export default {
  components: {
    UserBookingItem,
  },
  data() {
    return {
      user: this.$store.state.user,
      userBookings: this.$store.state.userBookings,
    };
  },
};
</script>

<style></style>
