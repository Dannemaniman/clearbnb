<template>
  <section>
    <h1>User Bookings</h1>
    <div v-if="userBookedHomes.length > 0">
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
    </div>
    <div v-else style="font-size: 2rem;">You have no booked objects!</div> 
  </section>
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

<style scoped>
  section {
    padding: 2rem;
  }

  div {
    background-color: rgb(235, 235, 235);
    padding: 2rem;
    box-shadow: rgba(38, 57, 77, 0.3) 0px 20px 30px -10px;
  }

  h1 {
    margin: 3.5rem auto 4rem auto;
    font-size: 3rem;
  }


</style>
