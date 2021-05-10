<template>
<div>
  <header>
    <Navbar/>
  </header>
  <main>
    <router-view/>
  </main>

  <Footer/>
  </div>
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
    this.$store.dispatch('whoami');
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
