<template>
  <section>
    <div class="header-bar">
      <h1>CONFIRM DETAILS</h1>
    </div>
    <h2>Booking</h2>
    <ListItem :item="info" />
    <form class="credit-modal" @submit.prevent="">
      <select>
        <option value="">Select your card</option>
        <option value="visa">Visa</option>
        <option value="mastercard">Mastercard</option>
        <option value="american-express">American Express</option>
      </select>
      <div class="card"></div>
      <input placeholder="Card Number" type="text" class="card-number" />
      <input
        placeholder="Card Expiration"
        type="text"
        class="card-expiration"
      />
      <input placeholder="Card CVV" type="text" class="card-cvv" />
      <button class="submit-button" @click="book">Pay</button>
    </form>
  </section>
</template>

<script>
import BookingModalVue from '../components/BookingModal.vue';
import ListItem from '../components/ListItem.vue';

export default {
  components: {
    ListItem,
  },
  methods: {
    book() {
      let booking = {
        bookerId: this.userId,
        houseId: this.info.house.id,
        chosenDate: [this.info.chosenDate.start, this.info.chosenDate.end],
      };
      this.$store.dispatch('book', booking);
    },
  },
  created() {
    console.log(this.$store.state.selectedHouse);
    console.log(this.$store.state.user.id);
  },
  data() {
    return {
      info: this.$store.state.selectedHouse,
      userId: this.$store.state.user.id,
    };
  },
};
</script>

<style scoped>
section {
  width: 70%;
  background-color: rgb(214, 214, 214);
  border-radius: 8px;
  margin: 0 auto;
  color: black;
}

.header-bar {
  height: 5rem;
  width: 100%;
  background-color: #6b6b6b;
  overflow: hidden;
  display: flex;
  justify-content: center;
  align-items: center;
}

.header-bar > h1 {
  text-align: center;
  color: whitesmoke;
}

.credit-modal {
  padding-top: 2rem;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  width: 70%;
  border-radius: 10px 10px 0 0;
  margin: 0 auto;
  border-bottom: 0;
  box-shadow: rgba(50, 50, 93, 0.25) 0px 50px 100px -20px,
    rgba(0, 0, 0, 0.3) 0px 30px 60px -30px,
    rgba(10, 37, 64, 0.35) 0px -2px 6px 0px inset;
  margin-top: 6rem;
  background-color: whitesmoke;
  position: relative;
}

.card {
  width: 20rem;
  height: 12rem;
  background-color: blue;
  position: absolute;
  border-radius: 10px;
  top: 7rem;
}

input {
  height: 2rem;
  outline: none;
  border-radius: 8px;
  border: 1p solid black;
  margin-bottom: 1rem;
}

.card-number {
  width: 15rem;
  position: relative;
}

.card-expiration {
  width: 9rem;
  margin-right: 2rem;
  position: relative;
  left: -2rem;
}

.card-cvv {
  width: 5rem;
  position: relative;
  top: -3rem;
  right: -5rem;
}

select {
  height: 2rem;
  width: 30%;
  outline: none;
  padding: 0.2rem;
  margin-bottom: 7rem;
}

.submit-button {
  background-color: green;
  height: 2.5rem;
  width: 6rem;
  color: black;
  font-weight: bold;
  border-radius: 10px;
  outline: none;
  margin-bottom: 4rem;
}
</style>
