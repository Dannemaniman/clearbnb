<template>
  <section>
    <header>
      <h1>1,350 kr <span class="span-night">/ night</span></h1>
      <h2>★ 4.25 <span class="span-night">(23 Reviews)</span></h2>
    </header>
    <article>
      <Calender @setDate="setDate" />
      <GuestModal
        @increment="increment"
        @decrement="decrement"
        :adultCounter="adultCounter"
        :childCounter="childCounter"
        :seniorCounter="seniorCounter"
      />
      <button @click="popPage">Check Availability</button>
    </article>
    <footer>
      <div class="price-bar">
        <p>1,500 kr x 1 night</p>
        <p>1,500 kr</p>
      </div>
      <div class="service-bar">
        <p>Service fee</p>
        <p>265 kr</p>
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
    //lägg computed som räknar ut antal dagar som är mellan 2 new Date()
    totalPrice() {
      return (
        this.adultCounter * this.prices['adult'] +
        this.childCounter * this.prices['child'] +
        this.seniorCounter * this.prices['senior']
      );
    },
  },
  data() {
    return {
      adultCounter: 0,
      childCounter: 0,
      seniorCounter: 0,
      prices: {
        adult: 250,
        child: 125,
        senior: 150,
      },
      chosenDate: {
        start: null,
        end: null,
      },
    };
  },
  methods: {
    popPage() {
      let guests = this.adultCounter + this.childCounter + this.seniorCounter;
      if (guests == 0) {
        alert('Choose how many guests that are staying');
      } else if (this.chosenDate.start == null || this.chosenDate.end == null) {
        alert('Choose a date to check in and one to check out');
      } else {
        if (this.$store.state.user !== null) {
          let chosenObject = {
            guests: {
              totalGuests: guests,
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
section {
  border-radius: 10px;
  box-shadow: rgb(0 0 0 / 12%) 0px 6px 16px;
  background-color: whitesmoke;
  width: fit-content;
  padding: 1rem;
  color: black;
  min-width: 14rem;
  position: relative;
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
