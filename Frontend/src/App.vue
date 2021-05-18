<template>
  <Navbar />

  <main>
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
    this.$store.dispatch('fetchHouses');
    this.$store.dispatch('fetchReviews');
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
</style>
