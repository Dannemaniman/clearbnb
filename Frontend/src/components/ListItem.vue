<template>
  <article ref="listItem">
    <img :src="imgSrc" />
    <div class="item-info">
      <p class="item-type">{{ title }}</p>
      <p class="item-adress">{{ address }}, {{ city }}</p>
      <p class="item-date">
        Date: {{ chosenDate.start.toLocaleDateString() }} - {{ chosenDate.end.toLocaleDateString() }}
      </p>
    </div>
    <button v-if="reviewable" class="review-button" @click="popReviewModal">
      Review
    </button>
    <button class="cancel-button" @click="deleteBooking(houseId)">
      Cancel
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
  props: ['imgSrc', 'title', 'address', 'city', 'chosenDate', 'houseId'],
  components: {
    ReviewModal,
  },
  created(){
    console.log(typeof chosenDate)
  },
  data() {
    return {
      showReviewModal: false,
    };
  },
  computed: {
    reviewable() {
      let currentDate = new Date();
      console.log(this.chosenDate);
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
  display: flex;
  flex-direction: row;
  box-shadow: rgba(50, 50, 93, 0.25) 0px 2px 5px -1px,
    rgba(0, 0, 0, 0.3) 0px 1px 3px -1px;
  width: 90%;
  margin: 0 auto;
  position: relative;
  border: 1px solid transparent;
  border-radius: 8px;
  transition: all 0.2s ease;
  margin: 1rem auto;
  background: white;
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
  height: 3rem;
  width: 4rem;
  position: absolute;
  right: 0;
  top: 0;
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

.cancel-button:hover {
  background-image: linear-gradient(
    147deg,
    rgb(185, 108, 0) 0%,
    rgb(228, 108, 3) 84%
  );
  color: #4b4b4b;
}

.review-button {
  height: 3rem;
  width: 4rem;
  position: absolute;
  right: 0;
  top: 3rem;
  border-top: none;
  border-right: none;
  outline: none;
  border-radius: 10px 0 0 10px;
  cursor: pointer;
  background-color: lightgreen;
  border-color: green;
}

button:hover,
button:active {
  color: white;
}

.item-type {
  font-size: 1.5rem;
  margin-top: 1rem;
  margin-left: 1rem;
  text-align: left;
}

.item-adress {
  margin-left: 1.6rem;
}

.item-date {
  text-align: left;
  margin-left: 1.6rem;
  font-size: 1rem;
}
</style>
