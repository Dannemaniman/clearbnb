<template>
  <header></header>
  <main>
    <router-view />
  </main>

  <Footer></Footer>
</template>

<script>
import Footer from './views/Footer.vue';

export default {
  name: 'App',
  components: {
    Footer,
  },
  created() {
    this.$store.dispatch('fetchHouses');
  },
};
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
  min-height: 100vh;
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}


body{
  color: rgba(34, 34, 34);
  font-family:  Helvetica, Arial, sans-serif;
  font-size: 14px;
  letter-spacing: normal;
  line-height: 18px;
  -webkit-font-smoothing: antialiased;
}
main {
  flex-grow: 1;
}
</style>
