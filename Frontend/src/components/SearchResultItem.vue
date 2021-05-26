<template>
  <article class="house" @click="goToDetails">
    <div class="image-div">
      <img :src="house.images[0]" />
      <div class="overlay">
        <div class="text">
          {{ house.propertyType }} with {{ house.accommodation.beds }} beds
          <br />
          {{ house.price }} SEK / Night
        </div>
      </div>
    </div>
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
  min-width: 20rem;
  background-image: url('https://www.getaccept.com/hubfs/Product%20pages%202.0/Product%20tour/bottom%20wave.svg');
  margin-top: 3rem;
  box-shadow: 1px 3px 5px rgb(0 0 0 / 80%);
  transition: 0.2s ease-out;
}

.image-div {
  position: relative;
}
.house .overlay {
  position: absolute;
  top: 0;
  bottom: 0;
  left: 0;
  right: 0;
  height: 100%;
  width: 100%;
  opacity: 0;
  transition: 0.5s ease;
  background-color: #000;
  line-height: 2rem;
  border-radius: 0 0 8rem 0rem;
}
.overlay:hover {
  opacity: 0.7;
}
.overlay .text {
  color: white;
  font-size: 20px;
  position: absolute;
  top: 50%;
  left: 50%;
  -webkit-transform: translate(-50%, -50%);
  -ms-transform: translate(-50%, -50%);
  transform: translate(-50%, -50%);
  text-align: center;
  width: 100%;
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
