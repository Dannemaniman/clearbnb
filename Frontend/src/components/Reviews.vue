<template>
  <section>
    <ReviewSlider :reviews="reviews" />
  </section>
</template>

<script>
import ReviewSlider from './ReviewSlider.vue';

export default {
  props: ['reviews'],
  components: {
    ReviewSlider,
  },
};
</script>

<style scoped></style>
