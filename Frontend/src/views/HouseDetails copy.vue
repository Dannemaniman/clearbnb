<template>
  <div class="content">
    <h1>{{ home.title }}</h1>
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
  /* data() {
    return {
      home: null,
    };
  }, */
  computed: {
    home() {
      return this.$store.state.selectedHouse;
    },
  },

  created() {
    this.$store.commit(
      'setSelectedHouse',
      this.$store.state.houses.find((h) => h.id == this.$route.params.id)
    );
    /* this.home = this.$store.state.houses.find(
      (h) => h.id == this.$route.params.id
    ); */
  },
};
</script>

<style></style>
