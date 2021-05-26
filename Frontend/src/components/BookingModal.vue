<template>
  <section class="booking-modal-section">
    <header>
      <h1>{{ home.price }}kr <span class="span-night">/ night</span></h1>
      <h2>
        ★ {{ reviewScore }}
        <span class="span-night"> {{ reviewAmount }}</span>
      </h2>
    </header>
    <article>
      <Calender @setDate="setDate" />
      <GuestModal
        @increment="increment"
        @decrement="decrement"
        :adultCounter="adultCounter"
        :childCounter="childCounter"
        :seniorCounter="seniorCounter"
        :guests="guests"
        :maxGuests="maxGuests"
      />
      <button @click="popPage">Check Availability</button>
    </article>
    <footer>
      <div class="price-bar">
        <p>{{ perNight }} x {{ numberOfNights }} {{ nightString }}</p>
        <p></p>
      </div>
      <div class="service-bar">
        <p>Service fee</p>
        <p>{{ serviceFee }} kr</p>
      </div>
      <hr />
      <div class="total-bar">
        <p>Total</p>
        <p>{{ totalPrice }}</p>
      </div>
    </footer>
  </section>
</template>

<script>
import Calender from '../components/Calender.vue';
import GuestModal from '../components/GuestModal.vue';

export default {
  props: ['home'],
  components: {
    Calender,
    GuestModal,
  },
  computed: {
    guests() {
      let guests = this.adultCounter + this.childCounter + this.seniorCounter;
      return guests;
    },
    reviewAmount() {
      let reviewAmount = this.$store.state.reviews.length;
      if (reviewAmount == 0) {
        return '';
      }
      return `(${reviewAmount} reviews)`;
    },
    reviewScore() {
      if (this.$store.state.reviews.length == 0) {
        return 'No Reviews';
      }
      let total = 0;
      this.$store.state.reviews.forEach(function (review) {
        let grade = review.grade;
        total += grade;
      });
      let average = total / this.$store.state.reviews.length;
      let rounded = average.toFixed(2);
      return rounded;
    },
    //lägg computed som räknar ut antal dagar som är mellan 2 new Date()
    totalPrice() {
      return (
        this.numberOfNights *
          (this.adultCounter * this.prices['adult'] +
            this.childCounter * this.prices['child'] +
            this.seniorCounter * this.prices['senior']) +
        this.serviceFee
      );
    },
    perNight() {
      return (
        this.prices.adult * this.adultCounter +
        this.prices.child * this.childCounter +
        this.prices.senior * this.seniorCounter
      );
    },
    nightString() {
      return this.numberOfNights > 1 ? 'nights' : 'night';
    },
    serviceFee() {
      return Math.round(this.home.price * 0.15 * this.numberOfNights);
    },
    numberOfNights() {
      console.log(this.chosenDate.end - this.chosenDate.start);
      if (this.chosenDate.start && this.chosenDate.end) {
        let date1 = new Date(this.chosenDate.start);
        let date2 = new Date(this.chosenDate.end);

        let time = date2.getTime() - date1.getTime();

        // To calculate the no. of days between two dates
        var days = time / (1000 * 3600 * 24);

        return days;
      } else {
        return 1;
      }
    },
  },
  data() {
    return {
      adultCounter: 1,
      childCounter: 0,
      seniorCounter: 0,
      prices: {
        adult: Number(this.home.price),
        child: this.home.price * (this.home.childDiscount / 100),
        senior: this.home.price * (this.home.seniorDiscount / 100),
      },
      chosenDate: {
        start: null,
        end: null,
      },
      maxGuests: this.home.accommodation.beds,
    };
  },
  methods: {
    popPage() {
      if (this.guests == 0) {
        alert('Choose how many guests that are staying');
      } else if (this.chosenDate.start == null || this.chosenDate.end == null) {
        alert('You have to choose check-in and check-out dates');
      } else {
        if (this.$store.state.user !== null) {
          let chosenObject = {
            guests: {
              totalGuests: this.guests,
              adult: this.adultCounter,
              child: this.childCounter,
              senior: this.seniorCounter,
            },
            price: this.totalPrice,
            chosenDate: this.chosenDate,
            house: this.home,
          };
          this.$store.dispatch('storeHome', chosenObject);
          this.$router.push('/confirm');
        } else {
          this.$router.push('/login-page');
        }
      }
    },
    increment(type) {
      if (type === 'adult') {
        this.adultCounter++;
      } else if (type === 'child') {
        this.childCounter++;
      } else {
        this.seniorCounter++;
      }
    },
    decrement(type) {
      if (type === 'adult' && this.adultCounter !== 0) {
        this.adultCounter--;
      } else if (type === 'child' && this.childCounter !== 0) {
        this.childCounter--;
      } else if (type === 'senior' && this.seniorCounter !== 0) {
        this.seniorCounter--;
      }
    },
    setDate(date, current) {
      if (current === 1) {
        this.chosenDate.start = date;
      } else {
        this.chosenDate.end = date;
      }
    },
  },
};
</script>

<style scoped>
.booking-modal-section {
  border-radius: 10px;
  /* box-shadow: rgb(0 0 0 / 12%) 0px 6px 16px; */
  background-color: rgb(233, 233, 233);
  width: fit-content;
  padding: 1rem;
  color: black;
  min-width: 16rem;
  position: relative;
  max-height: 30rem;
  /* overflow: hidden; */
  margin-top: 4rem;
}

h2 {
  font-size: 1.2rem;
}

button {
  height: 2rem;
  background-color: red;
  color: white;
  border-radius: 10px;
  outline: none;
  cursor: pointer;
}

article {
  display: flex;
  flex-direction: column;
}

.price-bar {
  display: flex;
  flex-direction: row;
}

.price-bar p:last-of-type {
  text-align: right;
  flex-grow: 2;
}
.service-bar {
  display: flex;
  flex-direction: row;
}

.service-bar p:last-of-type {
  text-align: right;
  flex-grow: 2;
}

.total-bar {
  display: flex;
  flex-direction: row;
}

.total-bar p:last-of-type {
  text-align: right;
  flex-grow: 2;
}
</style>
