<template>
  <div v-if="home" class="detail-content">
    <h1>{{ home.title }}</h1>
    <div class="images">
      <div class="big-image">
        <img :src="home.images[0]" alt="" class="img-1" />
      </div>
      <div class="small-images">
        <img :src="home.images[0]" alt="" class="img-2" />
        <img :src="home.images[0]" alt="" class="img-3" />
        <img :src="home.images[0]" alt="" class="img-4" />
        <img :src="home.images[0]" alt="" class="img-5" />
        <img :src="home.images[0]" alt="" class="img-6" />
        <img :src="home.images[0]" alt="" class="img-7" />
        <img :src="home.images[0]" alt="" class="img-8" />
        <img :src="home.images[0]" alt="" class="img-9" />
      </div>
    </div>
    <div class="information">
      <h1>{{ home.city }}</h1>
      <h2>{{ home.address }}</h2>
      <h2>{{ home.description }}</h2>
      <Amenities :amenities="home.amenities" />
    </div>
    <BookingModal />
    <div class="map"></div>
    <div class="reviews">
      <Reviews
        v-for="review of reviews.slice(1, 4)"
        :review="review"
        :key="review.id"
      />
    </div>

    <Hosts />
  </div>
</template>

<script>
import Hosts from '../components/Hosts.vue';
import BookingModal from '../components/BookingModal.vue';
import Amenities from '../components/Amenities.vue';
import Reviews from '../components/Reviews.vue';
export default {
  components: { Amenities, Hosts, BookingModal, Reviews },
  data() {
    return {
      home: null,
      reviews: {},
    };
  },

  async created() {
    let id = this.$route.params.id;
    const response = await fetch(`/rest/houses/${id}`);
    const data = await response.json();
    this.home = data;
    this.reviews = this.$store.state.reviews;
  },
};
</script>

<style scoped>
.detail-content {
  display: flex;
  flex-direction: column;
}
.images {
  display: flex;
  padding: 10px;
}
.detail-content .img-1 {
  max-width: 20rem;
  max-height: 25rem;
  object-fit: contain;
  background-repeat: no-repeat;
  align-self: flex-start;
  flex-grow: 1;
}
.small-images img {
  max-height: 12.5rem;
  width: 25%;
  object-fit: contain;
  background-repeat: no-repeat;
  flex-wrap: wrap;
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
</style>
