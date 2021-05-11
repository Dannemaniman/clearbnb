<template>
  <section class='section-slider'>
    <div class='slider-container' v-if="houses !== null">
      <div class='arrow-left' @click='moveLeft'/>
      <article :class="`slide${String(Math.random()).replace(
        '.', '')}`" v-for='house of houses' :key='house.id'  :ref="String(Math.random()).replace(
        '.', '')">
        <img :src='house.images[0]' @click="goToDetails(house.id)"/>
      </article>
      <div class='arrow-right' @click='moveRight'/>
    </div>
  </section>
</template>

<script> 
export default {
  props: ['houses'],
  data() {
    return {
       moveCounter: 0,
    }
  }, 
  methods: {
    moveRight() {
      this.moveCounter >= this.houses.length-1 ? this.moveCounter = 0 : this.moveCounter += 1

       for(let ref in this.$refs) {
         this.$refs[ref].style.transform = `translateX(-${300 * this.moveCounter}px)`
       }
    }, 
    moveLeft() {
      this.moveCounter <= 0 ? this.moveCounter = 0 : this.moveCounter -= 1

      for(let ref in this.$refs) {
        this.$refs[ref].style.transform = `translateX(-${300 * this.moveCounter}px)`
      } 
    },
    goToDetails(id) {
       this.$router.push('/house/' + id);
    }
  },
}
</script>

<style scoped>

  .section-slider {
    width: 100%;
    background-color: grey;
    margin-bottom: 10rem;
    border-top: 3px solid black;
  }

  .slider-container {
    width: 100%;
    display: flex;
    flex-direction: row;
    overflow: hidden;
    position: relative;
  }

  article {
    transition: all 0.3s ease;
    cursor: pointer;
  }

  img {
    height: 300px;
    width: 300px;
  }

  .arrow-right {
    position: absolute;
    z-index:3;
    top: 30%;
    right: 0;
    width: 0; 
    height: 0; 
    border-top: 60px solid transparent;
    border-bottom: 60px solid transparent;
    border-left: 60px solid black;
    cursor: pointer;
  }

  .arrow-right:hover,   .arrow-right:active {
    border-left: 60px solid white;
  }

  .arrow-left {
    z-index: 3;
    position: absolute;
    top: 30%;
    width: 0; 
    height: 0; 
    border-top: 60px solid transparent;
    border-bottom: 60px solid transparent; 
    border-right:60px solid black; 
    cursor: pointer;    
  }

  .arrow-left:hover,   .arrow-left:active {
    border-right: 60px solid white;
  }

</style>