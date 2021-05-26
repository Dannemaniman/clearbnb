<template>
  <section class="modal-container">
    <button class="button" @click="popModal">Refine search</button>
    <transition name="fade" appear>
      <div class="modal-overlay" v-if="showModal" @click="closeModal"></div>
    </transition>
    <transition name="slide" mode="out-in" appear>
      <div class="modal" v-if="showModal">
        <div class="flexbox">
          <div class="selectors">
            <div>
              <label>Property: </label>
              <select v-model="searchObject.property">
                <option v-for="property in properties" :key="property">
                  {{ property }}
                </option>
              </select>
            </div>
            <div>
              <label>Max price: </label>
              <select v-model="searchObject.price">
                <option v-for="price in prices" :key="price">
                  {{ price }}
                </option>
              </select>
            </div>
            <div>
              <label>Min review: </label>
              <select v-model="searchObject.review">
                <option v-for="review in reviews" :key="review">
                  {{ review }}
                </option>
              </select>
            </div>
            <div class="guest-number">
              <li class="select-item">
                <p class="select-label">Beds:</p>
                <div class="button-container">
                  <button @click="decrement">-</button>
                  <p>{{ counter }}</p>
                  <button @click="increment">+</button>
                </div>
              </li>
            </div>
          </div>
          <div class="checkboxes">
            <span class="row" v-for="amenity in amenities" :key="amenity">
              <span class="checkbox-label"> {{ amenity }} </span>
              <input
                type="checkbox"
                :value="amenity"
                v-model="searchObject.amenities"
              />
            </span>
          </div>

          <div class="selector-text">
            <label>Property:</label>
            <span> {{ searchObject.property }}</span>
            <label>Max price:</label> <span> {{ searchObject.price }}</span>
            <label>Min review:</label> <span> {{ searchObject.review }}</span>
            <label>Beds:</label> <span>{{ counter }}</span>
          </div>
          <div class="amenities">
            <p>Amenities:</p>
            <Amenities :amenities="searchObject.amenities" />
          </div>
        </div>
        <div class="search">
          <button class="button" @click="sendSearch">Search</button>
        </div>
      </div>
    </transition>
  </section>
</template>

<script>
import Amenities from './Amenities.vue';
export default {
  data() {
    return {
      showModal: false,
      searchObject: {
        property: '',
        price: '',
        review: '',
        amenities: [],
        beds: '',
      },
      properties: ['', 'Apartment', 'House', 'Bed-n-breakfast'],
      prices: ['', '500', '700', '900', '1200'],
      reviews: ['', '1', '2', '3', '4', '5'],
      counter: 0,
      amenities: [
        'Bed',
        'Breakfast',
        'Camera',
        'CCTV',
        'Dinner',
        'Elevator',
        'Fridge',
        'Heating',
        'Iron',
        'Mirror',
        'Parking',
        'Room Service',
        'Sink',
        'Tea',
        'Television',
        'Toothbrush',
        'Towel',
        'Wifi',
      ],
    };
  },
  components: { Amenities },
  methods: {
    popModal() {
      this.showModal = true;
    },
    closeModal() {
      this.showModal = false;
    },
    increment() {
      this.counter++;
      this.searchObject.beds++;
    },
    decrement() {
      if (this.counter !== 0) {
        this.counter--;
        this.searchObject.beds--;
      }
    },
    sendSearch() {
      this.$emit('refined-search', { searchObject: this.searchObject });
      this.showModal = false;
    },
  },
};
</script>

<style scoped>
.modal-container {
  margin-top: 2rem;
}

.button {
  appearance: none;
  outline: none;
  border: none;
  background: none;
  cursor: pointer;
  margin-top: 0.5rem;
  display: inline-block;
  padding: 7px 15px;
  background-image: linear-gradient(to right, rgb(254 149 0), rgb(254 135 0));
  border-radius: 4px;
  color: #fff;
  font-size: 18px;
  font-weight: 400;
  box-shadow: 1px 3px 5px rgb(0 0 0 / 80%);
  transition: 0.2s ease-out;
}
.button:hover {
  transform: scale(1.01);
  box-shadow: 2px 5px 5px rgb(0 0 0 /80%);
}

.modal-overlay {
  position: absolute;
  height: 150vh;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  z-index: 98;
  background-color: rgba(0, 0, 0, 40%);
}

.modal {
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -60%);
  z-index: 99;
  box-shadow: 1px 3px 5px rgb(0 0 0 / 80%);
  max-width: 50rem;
  background-color: white;
  border-radius: 16px;
  padding: 25px;
  color: #000;
  max-height: calc(100vh - 210px);
  overflow-y: auto;
}
.flexbox {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
}
.flexbox > * {
  margin: 0.3rem;
  min-height: 14rem;
  border-radius: 5px;
  flex-shrink: 1;
  min-width: 22rem;
}

/*   */

.selectors {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  width: 48%;
  box-shadow: 0px 0px 5px rgb(0 0 0);
  padding: 1rem 6rem;
}
.selectors select {
  background: white;
  border: 1px solid rgb(254, 149, 0);
  cursor: pointer;
  border-radius: 4px;
  -webkit-appearance: none;
  -moz-appearance: none;
  background-image: url('data:image/svg+xml;utf8, <svg xmlns="http://www.w3.org/2000/svg" width="100" height="50"><polygon points="0,0 100,0 50,50" style="fill:%23626163;"/></svg>');
  background-position: right 5px top 50%;
  background-repeat: no-repeat;
  background-size: 0.8rem;
  padding: 0.2rem;
  width: 7rem;
}
.selectors select:hover,
select:focus {
  outline: cadetblue;
  background-color: whitesmoke;
  border: 1px solid rgb(254, 149, 0);
}
.selectors div {
  margin: 0.2rem 0;
}
.checkboxes {
  display: table;
  width: 48%;
  box-shadow: 0px 0px 5px rgb(0 0 0 / 80%);
  min-height: 14rem;
  border-radius: 5px;
  text-align: start;
  padding: 2rem 0.5rem;
}
.checkboxes .row {
  width: 42%;
  display: inline-block;
  text-align: right;
}
.row .checkbox-label {
  padding-right: 1em;
  display: inline-block;
}

.guest-number {
  display: flex;
  flex-direction: column;
}

.select-label {
  flex-grow: 4;
  text-align: left;
  display: inline-flex;
  align-items: center;
  margin-right: 1rem;
}

.select-label p:last-of-type {
  font-size: 0.8rem;
  margin-left: 1rem;
}

li {
  list-style: none;
  display: flex;
  flex-direction: row;
}

.button-container {
  display: flex;
  flex-direction: row;
  justify-content: right;
  align-items: center;
  margin-right: 4rem;
}

.button-container p {
  align-self: center;
  margin: 0 0.8rem;
}

.button-container button {
  height: 1.5rem;
  width: 1.5rem;
  border-radius: 2rem;
  outline: none;
  cursor: pointer;
}

.selector-text {
  display: flex;
  flex-direction: column;
  background-color: white;
  padding: 0.5rem 0;
  width: 48%;
  box-shadow: 0px 0px 5px rgb(0 0 0 / 80%);
  padding: 1rem 0.5rem;
}

.amenities {
  background-color: white;
  padding-top: 0.4rem;
  min-height: 14rem;
  width: 48%;
  box-shadow: 0px 0px 5px rgb(0 0 0 / 80%);
  padding: 1rem 0.5rem 0 0.5rem;
}

.amenities p {
  margin: 0;
}

label {
  margin: 0.4rem;
  font-weight: 700;
}

.slide-enter-active,
.slide-leave-active {
  transition: transform 0.5s;
}
.slide-enter {
  transform: translateY(-100vh) translateX(-50%);
}

.slide-leave-to {
  transform: translateY(-100vh) translateX(-50%);
}
</style>
