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

<style></style>
