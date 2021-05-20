<template>
  <router-link class="house" :to="/house/ + house.id">
    <img :src="house.images[0]" />
    <div class="overlay">
      <div class="text">
        {{ house.city }} <br />
        {{ house.price }} / night
      </div>
    </div>
  </router-link>
</template>

<script>
export default {
  props: ['house'],
  /*   methods: {
    goToDetails() {
      this.$router.push('/house/' + this.house.id);
    },
  }, */
};
</script>

<style scoped>
.house {
  display: inline-block;
  position: relative;
  cursor: pointer;
  transition: 200ms;
  height: 10rem;
}

.house:hover {
  transform: scale(1.04);
}

img {
  display: block;
  width: 100%;
  height: 100%;
  object-fit: cover;
  object-position: center;
  border-radius: 10px;
}
.overlay {
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
  border-radius: 10px;
  line-height: 2rem;
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
}
</style>
