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
          <div class="checkboxes">
            <div>
              <label>Wifi</label>
              <input
                type="checkbox"
                value="Wifi"
                v-model="searchObject.amenities"
              />
            </div>
            <div>
              <label>Television</label>
              <input
                type="checkbox"
                value="Television"
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
              <label>Toothbrush</label>
              <input
                type="checkbox"
                value="Toothbrush"
                v-model="searchObject.amenities"
              />
            </div>
          </div>

          <div class="selector-text">
            <label>Property:</label>
            <span> {{ searchObject.property }}</span
            ><br />
            <label>Max price:</label> <span> {{ searchObject.price }}</span
            ><br />
            <label>Min review:</label> <span> {{ searchObject.review }}</span>
          </div>
          <div class="amenities">
            <p>Amenities:</p>
            <Amenities :amenities="searchObject.amenities" />
          </div>
        </div>
        <button class="button" @click="closeModal">Search</button>
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
        city: '',
        property: '',
        price: '',
        review: '',
        amenities: [],
      },
      properties: ['Sexnäste', 'Villa', 'Lägenhet', 'Sommarstuga'],
      prices: ['1000', '2000', '3000', '4000'],
      reviews: ['1/5', '2/5', '3/5', '4/5', '5/5'],
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
  transform: translate(-50%, -50%);
  z-index: 99;

  max-width: 550px;
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

.selector-text {
  display: flex;
  flex-direction: column;
  display: inline;
  margin-top: 1rem;
  background-color: #c7c7c7;
  padding: 1rem 0;
  border-radius: 10px 0 0 0;
}
.amenities {
  margin-top: 1rem;
  background-color: #c7c7c7;
  padding: 1rem 0;
  border-radius: 0 10px 0 0;
}
.amenities p {
  margin-top: 0;
}

label {
  margin-right: 0.2rem;
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
