<template>
  <header>
    <Navbar/>
  </header>
    <main>
      <router-view />
    </main>

  <Footer></Footer>
</template>

<script>
import Footer from "./views/Footer.vue"
import Navbar from './views/Navbar.vue';

export default {
  name: "App",
  components: {
    Footer,
    Navbar
  },
  async created() {
    let res = await fetch('/rest/hello');
    let message = await res.json();
    console.log(message);
  },
};
</script>

<style>
*{
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
