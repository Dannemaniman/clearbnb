<template>
  <div class="home-content">
   

    <div class="image-gallery">
      <a href="../../public/images/House1.jpg" class="img-1"> </a>
      <a href="../../public/images/House2.jpg" class="img-2"> </a>
      <a href="../../public/images/House3.jpg" class="img-3"> </a>
      <a href="../../public/images/House4.jpg" class="img-4"> </a>
      <a href="../../public/images/House5.jpg" class="img-5"> </a>
      <a href="../../public/images/House6.jpg" class="img-6"> </a>
      <a href="../../public/images/House7.jpg" class="img-7"> </a>
      <a href="../../public/images/House8.jpg" class="img-8"> </a>
    </div>
    <SearchBar />
    <GalleryComponent />
    <div class="slider"><h1>SLAAAJDEER</h1></div>
    <div class="slider"><h1>SLAAAJDEER</h1></div>
  </div>
</template>

<script>
import SearchBar from '../components/SearchBar.vue';
import GalleryComponent from '../components/GalleryComponent.vue';

export default {
  components: {
    GalleryComponent,
    SearchBar,
  },
  methods: {
    searchByLocation() {},
  },
};
</script>

<style scoped>
.home-content {
  padding: 0 0.5rem;
}
.image-gallery {
  margin-top: 5rem;
  display: grid;
  grid-template-columns: 1fr 1fr 1fr 1fr;
  grid-template-rows: auto;
  grid-gap: 0.5rem;
  grid-template-areas:
    'img-1 img-2 img-3 img-3'
    'img-1 img-4 img-5 img-6'
    'img-7 img-7 img-8 img-6';
}
.image-gallery a {
  top: 20px;
  width: 100%;
  height: 10rem;
  background-position: center;
  background-repeat: no-repeat;
  background-size: cover;
  position: relative;
}

.image-gallery a::before {
  content: '';
  position: absolute;
  width: 100%;
  height: 100%;
  top: 0;
  left: 0;
  background-color: rgba(0, 0, 0, 0.8);
  opacity: 0;
  transition: opacity 0.5s;
}

.image-gallery a:hover::before {
  opacity: 1;
}

.img-1 {
  grid-area: img-1;
  min-height: 20.5rem;
  background-image: url('../../public/images/House1.jpg');
}
.img-2 {
  grid-area: img-2;
  background-image: url('../../public/images/House2.jpg');
}
.img-3 {
  grid-area: img-3;
  background-image: url('../../public/images/House3.jpg');
}
.img-4 {
  grid-area: img-4;
  background-image: url('../../public/images/House4.jpg');
}
.img-5 {
  grid-area: img-5;
  background-image: url('../../public/images/House5.jpg');
}
.img-6 {
  grid-area: img-6;
  min-height: 20.5rem;
  background-image: url('../../public/images/House6.jpg');
}
.img-7 {
  grid-area: img-7;
  background-image: url('../../public/images/House7.jpg');
}
.img-8 {
  grid-area: img-8;
  background-image: url('../../public/images/House8.jpg');
}

.slider {
  margin: 5rem 0;
  color: #4b4b4b;
  font-size: 4rem;
  background-color: #a9a9a9;
  padding: 3rem 0;
  max-width: 100%;
}
</style>
