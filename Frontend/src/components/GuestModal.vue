<template>
  <section class="guest-container" @click="popModal">
    <div class="guest-header">
      <p>GUESTS</p>
      <p>{{ adultCounter + childCounter + seniorCounter }} guest</p>
    </div>
    <div v-if="showModal" class="guest-modal">
      <ul>
        <li class="select-item">
          <p class="select-label">Adults</p>
          <div class="button-container">
            <button @click="decrement('adult')">-</button>
            <p>{{ aCounter }}</p>
            <button :disabled="stopIncrement" @click="increment('adult')">
              +
            </button>
          </div>
        </li>
        <li class="select-item">
          <div class="select-label">
            <p>Children</p>
            <p>Ages 2-12</p>
          </div>
          <div class="button-container">
            <button @click="decrement('child')">-</button>
            <p>{{ cCounter }}</p>
            <button :disabled="stopIncrement" @click="increment('child')">
              +
            </button>
          </div>
        </li>
        <li class="select-item">
          <div class="select-label">
            <p>Seniors</p>
            <p>Ages 65+</p>
          </div>
          <div class="button-container">
            <button @click="decrement('senior')">-</button>
            <p>{{ sCounter }}</p>
            <button :disabled="stopIncrement" @click="increment('senior')">
              +
            </button>
          </div>
        </li>
      </ul>
      <p class="guestmodal-info">
        A maximum of {{ maxGuests }} guests. Infants dont't count towards the
        number of guests.
      </p>
      <button class="leave-button" @click="closeModal">Close</button>
    </div>
  </section>
</template>

<script>
export default {
  props: [
    'changeCounter',
    'adultCounter',
    'childCounter',
    'seniorCounter',
    'guests',
    'maxGuests',
  ],
  computed: {
    aCounter() {
      return this.adultCounter;
    },
    cCounter() {
      return this.childCounter;
    },
    sCounter() {
      return this.seniorCounter;
    },
    stopIncrement() {
      return this.guests >= this.maxGuests ? true : false;
    },
  },
  data() {
    return {
      showModal: false,
    };
  },
  methods: {
    popModal() {
      this.showModal = true;
    },
    closeModal(e) {
      this.showModal = false;
      e.stopPropagation();
    },
    increment(type) {
      this.$emit('increment', type);
    },
    decrement(type) {
      this.$emit('decrement', type);
    },
  },
};
</script>

<style scoped>
.guest-container {
  margin: 0;
  width: 100%;
  border: 1px solid black;
  padding-top: 0.5rem;
  margin-bottom: 1rem;
  position: relative;
}

.guest-container:active,
.guest-container:hover {
  cursor: pointer;
  background-color: rgba(240, 220, 45, 0.1);
}

.guest-header {
  display: flex;
  flex-direction: column;
  width: 100%;
  border-radius: 10px;
  z-index: 1;
  position: relative;
}

p {
  margin: 0;
}

.guest-header p:first-of-type {
  text-align: left;
  font-size: 0.8rem;
  font-weight: bold;
}

.guest-header p:last-of-type {
  text-align: left;
}

.guest-modal {
  background-color: whitesmoke;
  position: absolute;
  width: 20rem;
  top: -0.2rem;
  right: -0.2rem;
  border: 2px solid black;
  border-radius: 10px;
  cursor: default;
}

.select-item {
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  padding-bottom: 2rem;
}

.button-container {
  display: flex;
  flex-direction: row;
  justify-content: right;
  align-content: right;
  align-items: right;
  position: relative;
  right: 10px;
}

.button-container p {
  align-self: center;
  margin: auto 0.8rem;
}

button {
  height: 2rem;
  width: 2rem;
  border-radius: 2rem;
  outline: none;
  cursor: pointer;
}

ul {
  margin-top: 4rem;
  padding: 0;
}

li {
  list-style: none;
  display: flex;
  flex-direction: row;
  padding-left: 1rem;
}

.select-label {
  flex-grow: 4;
  text-align: left;
}

.select-label p:last-of-type {
  font-size: 0.8rem;
}

.guestmodal-info {
  padding: 2rem;
  padding-top: 0;
  font-size: 0.7rem;
}

.leave-button {
  border-radius: 0;
  width: 4rem;
  margin-bottom: 1rem;
  cursor: pointer;
}
</style>
