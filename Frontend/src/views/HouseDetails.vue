<template>
  <div v-if="home" class="detail-content">
    <h1>{{ home.title }}</h1>
    <div class="images">
      <img :src="home.images[0]" alt="" class="img-1" />
      <img :src="home.images[0]" alt="" class="img-2" />
      <img :src="home.images[0]" alt="" class="img-3" />
      <img :src="home.images[0]" alt="" class="img-4" />
      <img :src="home.images[0]" alt="" class="img-5" />
      <img :src="home.images[0]" alt="" class="img-6" />
      <img :src="home.images[0]" alt="" class="img-7" />
      <img :src="home.images[0]" alt="" class="img-8" />
    </div>
    <div class="content-holder">
      <div class="information">
        <h1>{{ home.city }}</h1>
        <h2>{{ home.address }}</h2>
        <h2>{{ home.description }}</h2>
        <Amenities :amenities="home.amenities" />
      </div>
      <BookingModal :home="home" />
    </div>
    <br />
    <br />
    <br />
    <br />

    <MapComponent :home="home" />
    <Reviews :reviews="$store.state.reviews" />
    <Hosts />
  </div>
</template>

<script>
import Hosts from '../components/Hosts.vue';
import BookingModal from '../components/BookingModal.vue';
import Amenities from '../components/Amenities.vue';
import Reviews from '../components/Reviews.vue';
import MapComponent from '../components/MapComponent.vue';

export default {
  components: { Amenities, Hosts, BookingModal, Reviews, MapComponent },
  data() {
    return {
      home: null,
    };
  },

  /* mounted() {
    console.log(this.$refs);
    let mapDiv = this.$refs.mapDiv;
    mapDiv.dropMarker({ Latitude: 55.6837, Longitude: 13.60829 });
  }, */

  async created() {
    let id = this.$route.params.id;
    const response = await fetch(`/rest/houses/${id}`);
    const data = await response.json();
    this.home = data;
    console.log(data);
    await this.$store.dispatch('fetchReviews', this.home.id);
  },
};
</script>

<style scoped>
.detail-content {
  display: flex;
  flex-direction: column;
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
  font-size: 1.3rem;
}

.images {
  display: grid;
  grid-template-rows: 1fr 1fr 1fr 1fr;
  grid-template-columns: 1fr 1fr 1fr 1fr;
  padding: 1rem;
  gap: 0.3rem;
}
.images img {
  width: 100%;
}
.images img:nth-child(1) {
  grid-row-start: 1;
  grid-row-end: 4;
  grid-column-start: 1;
  grid-column-end: 4;
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
