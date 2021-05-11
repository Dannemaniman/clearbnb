<template>
  <div @click="goToDetails" class="house">
    <img :src="house.images[0]" />
  </div>
</template>

<script>
export default {
  props: ['house'],
  methods: {
    goToDetails() {
      this.$router.push('/house/' + this.house.id);
    },
  },
};
</script>

<style scoped>
.house {
  cursor: pointer;
  transition: 200ms;
  height: 10rem;
}

.house:hover {
  transform: scale(1.04);
}

img {
  width: 100%;
  height: 100%;
  object-fit: cover;
  object-position: center;
  border-radius: 10px;
}
</style>
