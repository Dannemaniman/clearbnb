<template>
  <article @click="goToDetails">
    <img :src="house.images[0]" />
    <h2>{{ house.propertyType }}</h2>
    <h3>Review 3/5 (39 reviews)</h3>
    <h2>{{ house.city }}</h2>
    <h3>{{ house.address }}</h3>
  </article>
</template>

<script>
export default {
  props: ['house'],
  methods: {
    goToDetails() {
      /* this.$store.commit('setSelectedHouse', this.house); */
      this.$router.push('/house/' + this.house.id);
    },
  },
};
</script>

<style scoped>
.house {
  width: 45%;
}
img {
  width: 100%;
  height: 25rem;
  border-radius: 2rem;
  cursor: pointer;
}
</style>
