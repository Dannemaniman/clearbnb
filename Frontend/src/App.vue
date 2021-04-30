<template>
  <header></header>

  <main>
    <router-view />
  </main>

  <footer></footer>
</template>

<script>
export default {
  async created() {
    let res = await fetch('/rest/hello');
    let message = await res.json();
    console.log(message);
  },
};
</script>
