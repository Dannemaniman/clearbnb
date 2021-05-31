<template>
  <section class="section-amenities">
    <div class="amenities-container">
      <article v-for="(amenity, index) of amenities" :key="index">
        <img :src="getPath(index)" :alt="amenity" />
        <p>{{ amenity }}</p>
      </article>
    </div>
  </section>
</template>

<script>
export default {
  props: ['amenities'],
  methods: {
    getPath(index) {
      return (
        '../../public/icons/' +
        this.amenities[index].replace(/\s+/g, '') +
        '.png'
      );
    },
  },
};
</script>

<style scoped>
.section-amenities {
  display: flex;
  flex-direction: column;
  border-radius: 8px 8px 8px 2px;
}

h1 {
  font-size: 3rem;
  color: black;
}

.amenities-container {
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  justify-content: center;
}
.amenities img {
  height: 25px;
  width: 25px;
}
.amenities p {
  display: none;
}

article {
  margin: 1rem;
}

p {
  color: black;
}

img {
  height: 50px;
  width: 50px;
}
</style>
