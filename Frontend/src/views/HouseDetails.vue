<template>
  <div class="detail-content">
    <h1>{{ home.title }}</h1>
    <img :src="home.images" alt="" />
    <h2>{{ home.address }}</h2>
    <h2>{{ home.description }}</h2>
    <!-- <img class="img-1" :src="home.images[0]" />
    <img class="img-2" :src="home.images[0]" />
    <img class="img-3" :src="home.images[0]" />
    <img class="img-4" :src="home.images[0]" /> -->
  </div>
</template>

<script>
export default {
  data() {
    return {
      home: null,
    };
  },
  /*  computed: {
    home() {
      return this.$store.state.selectedHouse;
    },
  }, */
  beforeMount() {
    for (let home of this.$store.state.houses) {
      if (home.id == this.$route.params.id) {
        this.home = home;
      }
    }
  },
  /*  async created() {
    let id = this.$route.params.id;
    const response = await fetch(`/rest/houses/${id}`);
    const data = await response.json();
    this.home = data;
  }, */
};
</script>

<style></style>
