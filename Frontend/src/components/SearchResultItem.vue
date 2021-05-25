<template>
  <article class="house" @click="goToDetails">
    <img :src="house.images[0]" />
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
  display: flex;
  flex-direction: column;
  align-self: center;
  width: 45%;
  background-image: url('https://www.getaccept.com/hubfs/Product%20pages%202.0/Product%20tour/bottom%20wave.svg');
  margin-top: 3rem;
  box-shadow: 1px 3px 5px rgb(0 0 0 / 80%);
  transition: 0.2s ease-out;
}
.house:hover {
  transform: scale(1.01);
  box-shadow: 2px 5px 5px rgb(0 0 0 /80%);
}
img {
  width: 100%;
  height: 15rem;
  border-radius: 0 0 8rem 0rem;
  cursor: pointer;
  object-fit: cover;
}
h2,
h3 {
  margin: 0.5rem;
}
</style>
