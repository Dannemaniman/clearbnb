<template>

  <main>
    <router-view />
  </main>

 <Footer></Footer>
</template>

<script>
import Footer from "./views/Footer.vue"

export default {
  name: "App",
  components: {
    Footer
  },
  async created() {
    let res = await fetch('/rest/hello')
    let message = await res.json()
    console.log(message);
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
  min-height:100vh;
  margin: 0;
  min-height: 100vh;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  font-size: 120%;
}

body {
  margin: 0;
}
main {
  flex-grow: 1;
}

</style>
