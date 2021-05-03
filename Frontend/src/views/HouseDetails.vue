<template>
  <div class="content">
    <h1>{{ home.title }}</h1>
    <!-- <img class="img-1" :src="home.images[0]" />
    <img class="img-2" :src="home.images[0]" />
    <img class="img-3" :src="home.images[0]" />
    <img class="img-4" :src="home.images[0]" /> -->
    {{ home.description }}
  </div>
</template>

<script>
export default {
  computed: {
    home() {
      return this.$store.state.houses.find(
        (h) => h.id == this.$route.params.id
      );
    },
  },
};
</script>

<style></style>
