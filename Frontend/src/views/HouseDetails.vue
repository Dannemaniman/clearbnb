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
    <div class="map"><h1>Map</h1></div>
    <div class="reviews">
      <Reviews />
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
    };
  },

  async created() {
    let id = this.$route.params.id;
    const response = await fetch(`/rest/houses/${id}`);
    const data = await response.json();
    this.home = data;
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
.map {
  height: 15rem;
  background-color: #6b6b6b;
  margin-top: 3rem;
}
.reviews {
  height: 15rem;
  margin-top: 3rem;
}
</style>
