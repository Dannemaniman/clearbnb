<template v-if="this.userBookings">
  <ListItem 
    v-for="(userBookedHome, index) of userBookedHomes" 
    :key="index"
    :houseId="userBookedHome.id"
    :chosenDate="chosenDate"
    :imgSrc="userBookedHome.images[0]"
    :title="userBookedHome.title"
    :address="userBookedHome.address"
    :city="userBookedHome.city"
  />
</template>
<script>
import UserBookingItem from './UserBookingItem.vue';
import ListItem from "./ListItem.vue"
export default {
  components: {
    UserBookingItem,
    ListItem
  },
  computed: {
    userBookedHomes()  {
      let homes = this.$store.state.houses.filter((house)=> {
        for(let booking of this.userBookings){
          if(house.id === booking.houseId){
            this.chosenDate.start = new Date(booking.chosenDate[0])
            this.chosenDate.end = new Date(booking.chosenDate[1])
            return house
          }
        }
       })
      return homes
      } 
  },
  data() {
    return {
      user: this.$store.state.user,
      userBookings: this.$store.state.userBookings,
      chosenDate: {
        start: null,
        end: null
      }
    };
  },
  }

</script>

<style></style>
