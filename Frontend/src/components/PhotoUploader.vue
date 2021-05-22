<template>
  <form @submit.prevent="sendFile" enctype="multipart/form-data">
    <div class="dropzone">
      <input
        type="file"
        class="input-file"
        ref="file"
        @change="selectFile"
        multiple
      />

      <p v-if="!uploading">Click or drag your files here</p>
    </div>
    <div class="image-container">
      <img
        v-for="(image, index) in thumbnail"
        :src="image"
        :key="index"
        class="chosen-image"
      />
    </div>
  </form>
</template>

<script>
export default {
  name: 'Dropzone',

  data() {
    return {
      file: '',
      uploading: false,
      thumbnail: [],
      progress: 0,
    };
  },
  emit: ['photo'],
  methods: {
    async selectFile() {
      this.uploading = true;
      let files = this.$refs.file.files;

      if (files.length) {
        let formData = new FormData();

        for (let file of files) {
          formData.append('files', file, file.name);
          this.thumbnail.push(URL.createObjectURL(file));
        }

        try {
          this.$store.dispatch('uploadImage', formData);
          this.uploading = false;
        } catch (err) {
          console.log(err);
        }
      }
      this.uploading = false;
    },
  },
};
</script>

<style scoped>
.dropzone {
  min-height: 200px;
  padding: 10px 10px;
  position: relative;
  cursor: pointer;
  outline: 2px dashed grey;
  outline-offset: -6px;
  background: lightgray;
  color: black;
}
.dropzone:hover {
  background: rgb(184, 184, 184);
}

.input-file {
  opacity: 0;
  width: 100%;
  height: 200px;
  position: absolute;
  left: 0;
  cursor: pointer;
}

.image-container {
  margin: 0.5rem auto;
  height: 11rem;
  min-width: 11rem;
  width: fit-content;
  max-height: 20rem;
  display: flex;
  flex-direction: row;
  text-align: left;
  background-color: white;
  border: 1px solid black;
  box-shadow: rgba(50, 50, 93, 0.25) 0px 30px 60px -12px inset,
    rgba(0, 0, 0, 0.3) 0px 18px 36px -18px inset;
  padding: 0.5rem;
}

.chosen-image {
  height: 10rem;
  width: 10rem;
}
</style>
