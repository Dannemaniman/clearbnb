<template>
  <section>
    <ErrorModal
      errorMessage="Invalid fields!"
      @closeModal="closeModal"
      v-if="showError === true"
    />
    <div class="header-bar">
      <h1>Confirm Details</h1>
    </div>
    <ListItem
      :houseId="bookingInfo.house.id"
      :imgSrc="bookingInfo.house.images[0]"
      :title="bookingInfo.house.title"
      :address="bookingInfo.house.address"
      :city="bookingInfo.house.city"
      :chosenDate="bookingInfo.chosenDate"
      :price="bookingInfo.price"
      :guests="bookingInfo.guests"
    />
    <Spinner v-if="showSpinner" />
    <div class="credit-container" v-else>
      <form class="credit-modal" @submit.prevent="">
        <select v-model="cardType">
          <option selected="selected" value="Select your card">
            Select your card
          </option>
          <option value="visa">Visa</option>
          <option value="mastercard">Mastercard</option>
          <option value="american-express">American Express</option>
        </select>
        <credit-card :cardType="cardType" :cardInfo="cardInfo"></credit-card>
        <button class="submit-button" @click="book">Pay</button>
      </form>
    </div>
  </section>
</template>

<script>
import ListItem from '../components/ListItem.vue';
import CreditCard from '../components/CreditCard.vue';
import Spinner from '../components/Spinner.vue';
import ErrorModal from '../components/ErrorModal.vue';

export default {
  components: {
    ListItem,
    CreditCard,
    Spinner,
    ErrorModal,
  },
  data() {
    return {
      bookingInfo: this.$store.state.selectedHouse,
      userId: this.$store.state.user.id,
      cardType: 'Select your card',
      cardInfo: {
        cardNumber: '',
        cardCVV: '',
        validThruMonth: '',
        validThruYear: '',
      },
      showSpinner: false,
      showError: false,
    };
  },
  methods: {
    book() {
      if (
        this.cardType === 'Select your card' ||
        this.cardInfo.cardNumber === '' ||
        this.cardInfo.cardCVV === '' ||
        this.cardInfo.validThruMonth === '' ||
        this.cardInfo.validThruYear === ''
      ) {
        this.showError = true;
        return;
      }
      let booking = {
        bookerId: this.userId,
        houseId: this.bookingInfo.house.id,
        chosenDate: [
          new Date(this.bookingInfo.chosenDate.start),
          new Date(this.bookingInfo.chosenDate.end),
        ],
        price: this.bookingInfo.price,
        guests: this.bookingInfo.guests,
      };
      this.$store.dispatch('book', booking);
      this.showSpinner = true;
      setTimeout(() => {
        this.$store.dispatch('fetchBookings');
      }, 300);
      setTimeout(() => {
        this.$router.push('/user/' + this.userId);
        this.showSpinner = false;
      }, 1000);
    },
    closeModal() {
      this.showError = false;
    },
  },
};
</script>

<style scoped>
section {
  width: 100%;
  border-radius: 8px;
  margin: 2rem auto 2rem auto;
  color: #4b4b4b;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.26);
}

.header-bar {
  height: 5rem;
  width: 100%;
  background-color: rgb(254, 149, 0);
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.26);
  overflow: hidden;
  display: flex;
  border-radius: 8px 8px 0 0;
  justify-content: center;
  align-items: center;
  margin-bottom: 2rem;
}

.header-bar > h1 {
  text-align: center;
  color: #4b4b4b;
}

.credit-container {
  padding-bottom: 2rem;
}

.credit-modal {
  padding-top: 2rem;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  width: 90%;
  border-radius: 8px;
  margin: 0 auto;
  border-bottom: 0;
  box-shadow: rgba(50, 50, 93, 0.25) 0px 50px 100px -20px,
    rgba(0, 0, 0, 0.3) 0px 30px 60px -30px,
    rgba(10, 37, 64, 0.35) 0px -2px 6px 0px inset;
  margin-top: 4rem;
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

select {
  height: 2rem;
  width: 30%;
  outline: none;
  border: none;
  border-bottom: 1px solid rgb(0, 0, 0);
  padding: 0.2rem;
  margin-bottom: 1.5rem;
}

.submit-button {
  height: 2rem;
  width: 10rem;
  color: #4b4b4b;
  background-image: linear-gradient(
    147deg,
    rgb(254, 149, 0) 0%,
    rgb(254, 120, 0) 84%
  );
  border-radius: 4px;
  border: #4b4b4b;
  outline: none;
  cursor: pointer;
  text-decoration: none;
  font-size: 0.9rem;
  margin-bottom: 3rem;
}

button:hover {
  background-image: linear-gradient(
    147deg,
    rgb(185, 108, 0) 0%,
    rgb(228, 108, 3) 84%
  );
  color: #4b4b4b;
}
</style>
