<template>
  <section>
    <div class="image-container">
      <img v-for="(image, index) in thumbnail" :src="image" :key="index" class="chosen-image"/>
    </div>
      <input type="file" @change="chosenFile" style="display: none" ref="inputFile"/>
      <button @click="$refs.inputFile.click()">Choose File</button>
  </section>
</template>

<script>
export default {
  emit:['photo'],
  data(){
    return {
      selectedFiles: [],
      thumbnail: []
    }
  },
  methods: {
    chosenFile(event) {
      this.selectedFiles.push(event.target.files[0])
      this.thumbnail.push(URL.createObjectURL(event.target.files[0]))
      this.$emit('photo', this.selectedFiles)
    }
  }
}
</script>

<style scoped>

  section {
    margin-top: 1rem;
  }

  .image-container {
    margin: 0 auto;
    height: 11rem;
    min-width: 11rem;
    width: fit-content;
    max-height: 20rem;
    display: flex;
    flex-direction: row;
    text-align: left;
    background-color: white;
    border: 1px solid black;
    box-shadow: rgba(50, 50, 93, 0.25) 0px 30px 60px -12px inset, rgba(0, 0, 0, 0.3) 0px 18px 36px -18px inset;
    padding: 0.5rem;
  }

  .chosen-image {
    height: 10rem;
    width: 10rem;
  }

  button {
    padding: 0.6rem;
    margin: 1rem;
    cursor: pointer;
    border-radius: 10px;
  }
</style>