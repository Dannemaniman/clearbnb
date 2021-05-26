<template>
  <Navbar />

  <main>
    <div class="background-orange"></div>
    <router-view />
  </main>

  <Footer />
</template>

<script>
import Navbar from './components/Navbar.vue';
import Footer from './components/Footer.vue';

export default {
  name: 'App',
  components: {
    Navbar,
    Footer,
  },

  created() {
    this.$store.dispatch('whoAmI');
    this.$store.dispatch('fetchHouses');
    // this.$store.dispatch('fetchReviews');
    this.$store.dispatch('fetchUsers');
    this.$store.dispatch('fetchBestHouses');
    this.$store.dispatch('fetchCheapestHouses');
    this.$store.dispatch('fetchBookings');
  },
};
</script>

<style>
#app {
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
  min-height: 100vh;
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  font-size: 120%;
}

body {
  color: rgba(34, 34, 34);
  font-size: 14px;
  letter-spacing: normal;
  line-height: 18px;
  -webkit-font-smoothing: antialiased;
}

main {
  flex-grow: 1;
}

.background-orange {
  background-image: url('https://www.getaccept.com/hubfs/Product%20pages%202.0/Product%20tour/bottom%20wave.svg');
  background-repeat: no-repeat;
  background-size: contain;
  background-size: cover;
  /* transform: rotate(180deg); */
  transform: rotate(180deg);
  width: 100%;
  height: 40rem;
  position: fixed;
  /* position: absolute; */
  margin: 0 auto;
  left: 0;
  top: -20rem;
  /* right: 20rem; */
  z-index: -1;
  filter: brightness(130%);
}
</style>
