<template>
  <section class='section-slider'>
    <div class='slider-container'>
      <div class='arrow-left' @click='moveLeft'/>
      <article class='slide' v-for='(image, index) of images' :key='index'>
        <img :src='images[index]' ref='imagez'/>
      </article>
      <div class='arrow-right' @click='moveRight'/>
    </div>
  </section>
</template>

<script>
export default {
  props: ['images'],
  data() {
    return {
       moveCounter: 1 
    }
  },
  methods: {
    moveRight() {
      console.log(this.$refs)
      let slides =  document.querySelectorAll('.slide')

      slides.forEach((item, index) => {
         item.style.transform = `translateX(-${300 * this.moveCounter}px)`
      })
       this.moveCounter >= this.images.length-1 ? this.moveCounter = 0 : this.moveCounter += 1
    }, 
    moveLeft() {
      let slides =  document.querySelectorAll('.slide')
      this.moveCounter <= 0 ? this.moveCounter = 0 : this.moveCounter -= 1
      slides.forEach((item, index) => {
         item.style.transform = `translateX(-${300 * this.moveCounter}px)`
      })
      
  console.log(this.moveCounter)
    }
  },
  mounted() {
    this.imgWidth = this.$refs.image
  }
}
</script>

<style scoped>

  .section-slider {
    width: 100%;
    background-color: grey;
  }

  .slider-container {
    width: 100%;
    display: flex;
    flex-direction: row;
    overflow: hidden;
    position: relative;
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
  }

  .arrow-left:hover,   .arrow-left:active {
    border-right: 60px solid white;
  }

</style>