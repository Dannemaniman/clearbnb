<template>
  <p>{{ title }}</p>
  <header>
    <section class="section-slider">
      <div class="slider-container" v-if="houses !== null">
        <div class="arrow-left" @click="moveLeft" />
        <article
          :class="`slide${String(Math.random()).replace('.', '')}`"
          v-for="house of houses"
          :key="house.id"
          :ref="String(Math.random()).replace('.', '')"
        >
          <img :src="house.images[0]" @click="goToDetails(house.id)" />
        </article>
        <div class="arrow-right" @click="moveRight" />
      </div>
    </section>
  </header>
</template>

<script>
export default {
  props: ['houses', 'title'],
  data() {
    return {
      moveCounter: 0,
    };
  },
  methods: {
    moveRight() {
      this.moveCounter >= this.houses.length - 1
        ? (this.moveCounter = 0)
        : (this.moveCounter += 1);

      for (let ref in this.$refs) {
        this.$refs[ref].style.transform = `translateX(-${
          300 * this.moveCounter
        }px)`;
      }
    },
    moveLeft() {
      this.moveCounter <= 0 ? (this.moveCounter = 0) : (this.moveCounter -= 1);

      for (let ref in this.$refs) {
        this.$refs[ref].style.transform = `translateX(-${
          300 * this.moveCounter
        }px)`;
      }
    },
    goToDetails(id) {
      this.$router.push('/house/' + id);
    },
  },
};
</script>

<style scoped>
header {
  box-shadow: rgba(0, 0, 0, 0.041) 0px 10px 20px,
    rgba(0, 0, 0, 0.15) 0px 6px 6px;
  border-radius: 10px;
  padding: 0.1rem;
  /* height: 15rem; */
  margin-bottom: 7rem;
}

.section-slider {
  width: 100%;
  background: rgb(238, 238, 238);
  /* background: rgb(204, 204, 204); */
  /* box-shadow: rgba(50, 50, 93, 0.25) 0px 30px 60px -12px inset, rgba(0, 0, 0, 0.3) 0px 18px 36px -18px inset; */
  padding: 0.5rem;
  border-radius: 10px;
  background-image: url('../../public/bottomwave.svg');
  background-repeat: no-repeat;
  background-size: cover;
  background-position-y: 10rem;
}

p {
  font-size: 2.5rem;
  color: #4b4b4b;
  font-weight: thin;
  text-align: left;
  margin-left: 2rem;
  text-shadow: 2px 2px 0px #ffffff, 2px 2px 0px rgba(0, 0, 0, 0.164);
}

.slider-container {
  width: 100%;
  display: flex;
  flex-direction: row;
  /* overflow: hidden; */
  position: relative;
  overflow-x: hidden;
}

article {
  transition: all 0.3s ease;
  cursor: pointer;
  box-shadow: rgb(0 0 0 / 50%) 0px 1px 12px;
  margin: auto 0.4rem;
  background: rgb(173, 173, 173);
  border-radius: 10px;
}

img {
  height: 300px;
  width: 300px;
  border-radius: 10px;
}

.arrow-right {
  position: absolute;
  z-index: 3;
  top: 30%;
  right: 0;
  width: 0;
  height: 0;
  border-top: 60px solid transparent;
  border-bottom: 60px solid transparent;
  border-left: 60px solid #808080;
  opacity: 0.75;
  cursor: pointer;
}

.arrow-right:hover,
.arrow-right:active {
  border-left: 60px solid #4b4b4b;
}

.arrow-left {
  z-index: 3;
  position: absolute;
  top: 30%;
  width: 0;
  height: 0;
  border-top: 60px solid transparent;
  border-bottom: 60px solid transparent;
  border-right: 60px solid #808080;
  opacity: 0.75;
  cursor: pointer;
}

.arrow-left:hover,
.arrow-left:active {
  border-right: 60px solid #4b4b4b;
}
</style>
