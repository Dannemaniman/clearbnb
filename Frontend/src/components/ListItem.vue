<template>
  <article ref="listItem">
    <img :src="imgSrc" />
    <p class="item-type">{{ title }}</p>
    <p class="item-address">Address: {{ address }}, {{ city }}</p>
    <p class="item-date">Date: {{ chosenDate.start }} - {{ chosenDate.end }}</p>
    <p v-if="guests" class="item-guests">Guests: {{ guests.totalGuests }}</p>
    <p v-if="price" class="item-price">To Pay: {{ price }}</p>
    <button class="cancel-button" @click="deleteBooking(houseId)">
      Cancel
    </button>
    <button v-if="reviewable" class="review-button" @click="popReviewModal">
      Review
    </button>
  </article>
  <ReviewModal
    :houseId="houseId"
    v-if="showReviewModal"
    @dismiss="popReviewModal"
  />
</template>

<script>
import ReviewModal from './ReviewModal.vue';

export default {
  props: [
    'imgSrc',
    'title',
    'address',
    'city',
    'chosenDate',
    'houseId',
    'price',
    'guests',
  ],
  components: {
    ReviewModal,
  },
  data() {
    return {
      showReviewModal: false,
    };
  },
  computed: {
    reviewable() {
      let currentDate = new Date().toLocaleDateString();
      return this.chosenDate.end < currentDate ? true : false;
    },
  },
  methods: {
    popReviewModal() {
      this.showReviewModal = !this.showReviewModal;
    },
    deleteBooking(id) {
      this.$store.dispatch('deleteBooking', id);
    },
  },
};
</script>

<style scoped>
article {
  display: grid;
  grid-template-columns: 20% 40% 20% 20%;
  grid-template-rows: 33.33% 33.33% 33.33%;
  box-shadow: rgba(50, 50, 93, 0.25) 0px 2px 5px -1px,
    rgba(0, 0, 0, 0.3) 0px 1px 3px -1px;
  width: 90%;
  height: 150px;
  margin: 0 auto;
  border: 1px solid transparent;
  border-radius: 8px;
  transition: all 0.2s ease;
}

article:hover,
article:active {
  box-shadow: rgb(38, 57, 77) 0px 20px 30px -10px;
  border-bottom: 1px solid black;
}

img {
  height: 150px;
  width: 150px;
  border-radius: 8px 0px 0px 8px;
}

.cancel-button {
  grid-column-start: 4;
  grid-row-start: 1;
  justify-self: end;
  height: 3rem;
  width: 4rem;
  border-top: none;
  border-right: none;
  outline: none;
  border-radius: 0 8px 0 10px;
  cursor: pointer;
  background-image: linear-gradient(
    147deg,
    rgb(254, 149, 0) 0%,
    rgb(254, 120, 0) 84%
  );
  border: #4b4b4b;
}

.review-button {
  grid-column-start: 4;
  grid-row-start: 2;
  justify-self: end;
  height: 3rem;
  width: 4rem;
  top: 3.5rem;
  border-top: none;
  border-right: none;
  outline: none;
  border-radius: 10px 0 0 10px;
  cursor: pointer;
  background-image: linear-gradient(
    147deg,
    rgb(254, 149, 0) 0%,
    rgb(254, 120, 0) 84%
  );
  border: #4b4b4b;
}

button:hover,
button:active {
  background-image: linear-gradient(
    147deg,
    rgb(185, 108, 0) 0%,
    rgb(228, 108, 3) 84%
  );
  color: #4b4b4b;
}

.item-type {
  font-weight: bold;
  font-size: 120%;
  margin: 1rem 1rem 1rem 0;
  justify-self: start;
}

.item-address {
  margin: 0.5rem;
  grid-column-start: 2;
  justify-self: start;
}

.item-date {
  margin: 0.5rem;
  grid-column-start: 2;
  justify-self: start;
}

.item-guests {
  margin: 0.5rem;
  grid-column-start: 3;
  grid-row-start: 2;
  justify-self: start;
}

.item-price {
  margin: 0.5rem;
  grid-column-start: 3;
  grid-row-start: 3;
  justify-self: start;
  font-weight: bold;
}

@media (max-width: 1000px) {
  article {
    display: grid;
    grid-template-rows: repeat(30, 1rem);
    grid-template-columns: 5% 90% 5%;
    height: 30rem;
  }

  img {
    border-radius: 8px;
    grid-column-start: 2;
    grid-row-start: 7;
    justify-self: center;
  }

  .item-type {
    grid-column-start: 2;
    grid-row-start: 2;
    justify-self: center;
    margin-left: 1rem;
  }

  .item-address {
    grid-column-start: 2;
    grid-row-start: 17;
    grid-row-end: 18;
    justify-self: center;
    margin: 0;
  }

  .item-date {
    grid-column-start: 2;
    grid-row-start: 20;
    grid-row-end: 21;
    justify-self: center;
    margin: 0;
  }

  .item-guests {
    grid-column-start: 2;
    grid-row-start: 23;
    grid-row-end: 24;
    justify-self: center;
    margin: 0;
  }

  .item-price {
    grid-column-start: 2;
    grid-row-start: 25;
    grid-row-end: 26;
    justify-self: center;
    font-weight: bold;
    margin: 0;
  }

  .cancel-button {
    grid-column-start: 2;
    grid-row-start: 27;
    justify-self: center;
    margin-left: 5rem;
    border-radius: 8px;
  }

  .review-button {
    grid-column-start: 2;
    grid-row-start: 27;
    margin-top: 0;
    margin-right: 5rem;
    border-radius: 8px;
    justify-self: center;
  }
}
</style>
