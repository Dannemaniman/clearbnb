<template>
  <div v-if="home" class="detail-content">
    <h1>{{ home.title }}</h1>
    <div class="images">
      <img
        v-for="(image, index) of home.images.slice(0, 8)"
        :src="home.images[index]"
        :key="index"
        :class="addClass(index)"
        @click="swapImage(index)"
      />
    </div>
    <div class="content-holder">
      <div class="information">
        <h2>
          {{ home.city }}, <span class="home-address">{{ home.address }}</span>
        </h2>
        <h3>{{ home.description }}</h3>
        <Amenities :amenities="home.amenities" />
      </div>
      <BookingModal :home="home" />
    </div>
    <br />
    <br />
    <br />
    <br />
    <MapComponent :home="home" :inDetail="true" />
    <Reviews :reviews="reviews" v-if="reviews.length"/>
    <Hosts :home="home" />
  </div>
</template>

<script>
import Hosts from '../components/Hosts.vue';
import BookingModal from '../components/BookingModal.vue';
import Amenities from '../components/Amenities.vue';
import MapComponent from '../components/MapComponent.vue';
import Reviews from "../components/ReviewSlider.vue"

export default {
  components: { Amenities, Hosts, BookingModal, Reviews, MapComponent },
  data() {
    return {
      home: null,
      reviews: null
    };
  },
  methods: {
    addClass(index) {
      return 'img-' + (index + 1);
    },
    swapImage(index) {
      [this.home.images[0], this.home.images[index]] = [
        this.home.images[index],
        this.home.images[0],
      ];
    },
  },

  async created() {
    let id = this.$route.params.id;
    const response = await fetch(`/rest/houses/${id}`);
    const data = await response.json();
    this.home = data;

    await this.$store.dispatch('fetchReviews', this.home.id);
    this.reviews = this.$store.state.reviews
  },
};
</script>

<style scoped>
.detail-content {
  display: flex;
  flex-direction: column;
  border-radius: 10px;
  text-align: center;
  width: 100%;
}

h1 {
  margin-top: 5rem;
  font-size: 3rem;
  text-align: left;
  margin-left: 1rem;
}

h3 {
  line-height: 150%;
}

.content-holder {
  position: relative;
  display: flex;
  flex-direction: row;
}

.information {
  padding: 2rem;
  flex-grow: 3;
}

.information h2:last-of-type {
  font-size: 3rem;
}

.home-address {
  font-size: 1.6rem;
}

.images {
  display: grid;
  grid-template-rows: 1fr 1fr 1fr 1fr;
  grid-template-columns: 1fr 1fr 1fr 1fr;
  grid-template-areas:
    'img-1 img-1 img-1 img-2'
    'img-1 img-1 img-1 img-3'
    'img-1 img-1 img-1 img-4'
    'img-5 img-6 img-7 img-8';
  padding: 0.3rem;
  gap: 0.3rem;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.26);
}
.images img {
  width: 100%;
  max-height: 100%;
  object-fit: cover;
  cursor: pointer;
}

.images img:hover,
.images img:active {
  filter: brightness(60%);
}

.images .img-1 {
  grid-area: img-1;
  object-fit: cover;
  height: 30.6rem;
}
.img-2 {
  grid-area: img-2;
  height: 10rem;
}
.img-3 {
  grid-area: img-3;
  height: 10rem;
}
.img-4 {
  grid-area: img-4;
  height: 10rem;
}
.img-5 {
  grid-area: img-5;
  height: 10rem;
}
.img-6 {
  grid-area: img-6;
  height: 10rem;
}
.img-7 {
  grid-area: img-7;
  height: 10rem;
}
.img-8 {
  grid-area: img-8;
  height: 10rem;
}

.reviews {
  display: flex;
  align-items: center;
  justify-content: center;
  flex-wrap: wrap;
  gap: 0.5rem;
  height: 100%;
  margin: 1rem 0;
}
.map {
  background-image: url('../../public/Map.png');
  height: 25rem;
  margin-top: 3rem;
}

.information h2 {
  font-size: 6rem;
}
</style>
