<template>
  <div class="image-gallery">
    <GalleryComponentItem
      v-for="(house, index) of houses.slice(0, 8)"
      :key="index"
      :class="addClass(index)"
      :house="house"
    />
  </div>
</template>

<script>
import GalleryComponentItem from './GalleryComponentItem.vue';
export default {
  components: { GalleryComponentItem },
  methods: {
    addClass(index) {
      return 'house-' + (index + 1);
    },
  },
    created() {
    console.log("galleryItem")
  },
  computed: {
    houses() {
      return this.$store.state.houses;
    },
  },
};
</script>

<style scoped>
.image-gallery {
  margin-top: 5rem;
  margin-bottom: 5rem;
  display: grid;
  grid-template-columns: 1fr 1fr 1fr 1fr;
  grid-template-rows: auto;
  grid-gap: 0.5rem;
  grid-template-areas:
    'house-1 house-2 house-3 house-3'
    'house-1 house-4 house-5 house-6'
    'house-7 house-7 house-8 house-6';
}
.house-1 {
  grid-area: house-1;
  min-height: 20.5rem;
}
.house-2 {
  grid-area: house-2;
}
.house-3 {
  grid-area: house-3;
}
.house-4 {
  grid-area: house-4;
}
.house-5 {
  grid-area: house-5;
}
.house-6 {
  grid-area: house-6;
  min-height: 20.5rem;
}
.house-7 {
  grid-area: house-7;
}
.house-8 {
  grid-area: house-8;
}
</style>
