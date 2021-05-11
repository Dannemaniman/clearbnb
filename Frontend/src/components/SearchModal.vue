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
          </div>
        </div>
        <div class="checkboxes">
          <div>
            <label>Bed</label>
            <input
              type="checkbox"
              value="Bed"
              v-model="searchObject.amenities"
            />
          </div>
          <div>
            <label>Breakfast</label>
            <input
              type="checkbox"
              value="Breakfast"
              v-model="searchObject.amenities"
            />
          </div>
          <div>
            <label>Fridge</label>
            <input
              type="checkbox"
              value="Fridge"
              v-model="searchObject.amenities"
            />
          </div>
          <div>
            <label>Heating</label>
            <input
              type="checkbox"
              value="Heating"
              v-model="searchObject.amenities"
            />
          </div>
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
        <div class="selector-text">
          <label>Property:</label>
          <span> {{ searchObject.property }}</span
          ><br />
          <label>Max price:</label> <span> {{ searchObject.price }}</span
          ><br />
          <label>Min review:</label> <span> {{ searchObject.review }}</span
          ><br />
          <label>Beds:</label> <span>{{ counter }}</span>
        </div>
        <Amenities :amenities="searchObject.amenities" />
      </div>
      <button class="button" @click="sendSearch">Search</button>
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
      properties: ['Torp', 'Villa', 'Lägenhet', 'Sommarstuga'],
      prices: ['1000', '2000', '3000', '4000'],
      reviews: ['1', '2', '3', '4', '5'],
      counter: 0,
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
  padding: 10px 20px;
  background-image: linear-gradient(to right, #6b6b6b, #808080);
  border-radius: 8px;

  color: #fff;
  font-size: 18px;
  font-weight: 700;

  box-shadow: 3px 3px rgba(0, 0, 0, 0.4);
  transition: 0.4s ease-out;
}
.button:hover {
  box-shadow: 6px 6px rgba(0, 0, 0, 0.6);
}

.modal-overlay {
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  z-index: 98;
  background-color: rgba(0, 0, 0, 0);
}

.modal {
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -80%);
  z-index: 99;

  max-width: 520px;
  background-color: #e9e9e9;
  border-radius: 16px;
  padding: 25px;
  color: #000;
}
.flexbox {
  display: flex;
  flex-wrap: wrap;
}
.flexbox > * {
  flex: 1 1 50%;
}

/*   */

.selectors {
  display: flex;
  flex-direction: column;
  align-items: flex-end;
}
.selectors select {
  background: #a9a9a9;
  border: 1px solid #9f9f9f;
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
.selectors select:hover select:focus {
  outline: none;
  background-color: #a9a9a9;
  border: 1px solid #4b4b4b;
}
.selectors div {
  margin: 0.2rem 0;
}
.checkboxes {
  display: flex;
  flex-direction: column;
  align-items: flex-end;
  padding: 0 3rem;
}

.guest-number {
  display: flex;
  flex: 1 1 100%;
  flex-direction: column;
}

.select-label {
  flex-grow: 4;
  text-align: left;
  display: inline-flex;
  align-items: center;
  margin-left: 4rem;
}

.select-label p:last-of-type {
  font-size: 0.8rem;
  margin-left: 1rem;
}

li {
  list-style: none;
  display: flex;
  flex-direction: row;
  padding-left: 1rem;
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
  display: inline;
  background-color: #c7c7c7;
  padding: 0.5rem 0;
  border-radius: 10px 0 0 0;
  text-align: left;
}

.amenities {
  background-color: #c7c7c7;
  padding: 0.5rem 0;
  border-radius: 0 10px 0 0;
  height: 90px;
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
  transition: transform 1s;
}
.slide-enter {
  transform: translateY(-100vh) translateX(-50%);
}

.slide-leave-to {
  transform: translateY(-100vh) translateX(-50%);
}
</style>
