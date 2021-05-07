<template>
  <section class="modal-container">
    <button class="button" @click="popModal">Refine search</button>
    <transition name="fade" appear>
      <div class="modal-overlay" v-if="showModal" @click="closeModal"></div>
    </transition>
    <transition name="slide" mode="out-in" appear>
      <div class="modal" v-if="showModal">
        <h1>Sök tjofräs</h1>
        <form>
          <div class="selectors">
            <div>
              <label>Property type:</label>
              <select v-model="searchObject.property">
                <option v-for="property in properties" :key="property">
                  {{ property }}
                </option>
              </select>
            </div>
            <div>
              <label>Max price:</label>
              <select v-model="searchObject.price">
                <option v-for="price in prices" :key="price">
                  {{ price }}
                </option>
              </select>
            </div>
            <div>
              <label>Min review:</label>
              <select v-model="searchObject.review">
                <option v-for="review in reviews" :key="review">
                  {{ review }}
                </option>
              </select>
            </div>
          </div>
          <div class="checkboxes">
            <h4>Must have amenities:</h4>
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
        </form>
        <div class="preview">
          <h3>Chosen:</h3>
          <p>Property type: {{ searchObject.property }}</p>
          <p>Max price: {{ searchObject.price }}</p>
          <p>Min review: {{ searchObject.review }}</p>
          <p>Amenities: {{ searchObject.amenities }}</p>
        </div>

        <button class="button" @click="closeModal">Search</button>
      </div>
    </transition>
  </section>
</template>

<script>
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

  display: inline-block;
  padding: 15px 25px;
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
  transform: translate(-50%, -70%);
  z-index: 99;

  width: 100%;
  max-width: 700px;
  background-color: #fff;
  border-radius: 16px;

  padding: 25px;
}

.modal h1 {
  color: #222;
  font-size: 32px;
  font-weight: 900;
  margin-bottom: 15px;
}
.modal p {
  color: #666;
  font-size: 18px;
  font-weight: 400;
  margin-bottom: 15px;
}

/* .fade-enter-active,
.fade-leave-active {
  transition: opacity 0.5s;
} */

/* .fade-enter,
.fade-leave-to {
  opacity: 0;
} */

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
