<template>
  <header>
    <Navbar/>
  </header>
  <main>
    <router-view />
  </main>

  <Footer/>
</template>

<script>
import Navbar from './views/Navbar.vue'
import Footer from './views/Footer.vue';

export default {
  name: 'App',
  components: {
    Footer,
    Navbar
  },
  created() {
    this.$store.dispatch('fetchHouses');
  },
};
</script>

<style>

  *{
    margin: 0;
    padding: 0;
    box-sizing: border-box;
    
  }
 


body {
 
  font-family: Avenir, Helvetica, Arial, sans-serif;
}
main {
  flex-grow: 1;
}
</style>
