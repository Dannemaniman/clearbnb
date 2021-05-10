<template>
  <section class="review-card">
    <div class="review-header">
      {{ review.grade }}
    </div>
    <div class="author">
      <strong>{{ review.author }}</strong>
      reviewed
      <strong>Torpet</strong>
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
  max-width: 400px;
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
.author {
  margin-top: 8px;
  font-size: 0.75rem;
  line-height: 1rem;
  overflow: hidden;
  text-overflow: ellipsis;
}
.review-body {
  overflow: hidden;
  margin-top: 16px;
  font-size: 0.875rem;
  line-height: 1.25rem;
}
</style>
