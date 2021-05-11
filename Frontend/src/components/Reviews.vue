<template>
  <section class="review-card">
    <div class="review-header">
      <img :src="review.avatar" alt="" />
      {{ review.grade }} / 5
    </div>
    <div class="author">
      <strong>{{ review.author }}</strong>
      reviewed
      <strong>This</strong>
    </div>
    <div class="review-body">
      {{ review.review }}
    </div>
  </section>
</template>

<script>
export default {
  data() {
    return {
      reviews: [],
    };
  },
  methods: {},
  props: ['review'],
};
</script>

<style scoped>
.review-card {
  min-width: 240px;
  max-width: 30%;
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  padding: 24px;
  margin: 0 0 16px 0;
  background-color: #f2f2f2;
  color: #4b4b4b;
  box-sizing: border-box;
}
.review-header {
  display: flex;
  align-items: center;
}
.review-header img {
  margin-right: 3rem;
  background-color: #c7c7c7;
  border-radius: 50px;
}
.author {
  margin-top: 8px;
  font-size: 0.85rem;
  line-height: 1rem;
}
.review-body {
  overflow: hidden;
  margin-top: 16px;
  font-size: 0.9rem;
  line-height: 1.25rem;
}
</style>
