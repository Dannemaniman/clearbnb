<template>
  <div class="home-content">
    <h1>Clear-BnB</h1>
    <form submit.prevent="">
      <input type="text" placeholder="Enter location..." />
      <button class="btn" @click="searchByLocation">Search</button>
    </form>
    <div class="image-container">
      <img src="../../public/images/House1.jpg" alt="" />
      <img src="../../public/images/House2.jpg" alt="" />
      <img src="../../public/images/House3.jpg" alt="" />
      <img src="../../public/images/House4.jpg" alt="" />
      <img src="../../public/images/House5.jpg" alt="" />
      <img src="../../public/images/House6.jpg" alt="" />
      <img src="../../public/images/House7.jpg" alt="" />
    </div>
  </div>
</template>

<script>
export default {
  methods: {
    searchByLocation() {},
  },
};
</script>

<style scoped>
.home-content {
  max-width: 60rem;
  margin: auto;
}
.image-container {
  margin-top: 5rem;
}
.image-container img {
  height: 400px;
  width: 300px;
  object-fit: cover;
}
</style>
