<template>
  <UserBookingItem
    v-for="(userBooking, index) of userBookings"
    v-bind:key="index"
    v-bind:booking="userBooking"
  />
</template>

<script>
import UserBookingItem from './UserBookingItem.vue';
export default {
  components: {
    UserBookingItem,
  },

  computed: {
    bookings() {
      return this.$store.state.bookings;
    },
  },

  data() {
    return {
      user: null,
      userBookings: [],
    };
  },

  async created() {
    let userId = this.$route.params.id;
    let userRes = await fetch('/rest/users/' + userId);
    let user = await userRes.json();
    this.user = user;

    let userBookings = [];
    for (let booking of this.bookings) {
      if (this.user.id == booking.bookerId) {
        userBookings.push(booking);
      }
    }
    this.userBookings = userBookings;
  },
};
</script>

<style></style>
