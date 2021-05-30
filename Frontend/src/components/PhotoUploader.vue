<template>
  <form @submit.prevent="sendFile" enctype="multipart/form-data">
    <div class="image-container">
      <p v-if="thumbnail.length === 0">Chosen pictures will be shown here!</p>

      <img
        v-for="(image, index) in thumbnail"
        :src="image"
        :key="index"
        class="chosen-image"
      />
    </div>
    <div class="dropzone" v-if="thumbnail.length < 1">
      <input
        type="file"
        class="input-file"
        ref="file"
        @change="selectFile"
        multiple
      />
      <p v-if="!uploading">Click or drag your files here</p>
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

form {
overflow: hidden;
}

.dropzone {
  min-height: 200px;
  padding: 10px 10px;
  position: relative;
  cursor: pointer;
  outline: 2px dashed grey;
  outline-offset: -2px;
  background: rgba(0, 0, 0, 0.089);
  color: black;
  width: 50%;
  margin: 0 auto;
  margin-top: 2rem;
}
.dropzone:hover {
  background: rgba(0, 0, 0, 0.246);
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
  max-height: 20rem;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: center;
  background-color: white;
  border: 1px solid rgba(0, 0, 0, 0.144);
  box-shadow: rgb(225, 231, 236) 3px 3px 6px 0px inset,
    rgba(255, 255, 255, 0.5) -3px -3px 6px 1px inset;
  padding-left: 1.5rem;
  border-radius: 10px;
  margin-left: 1rem;
  overflow: hidden;
}

.chosen-image {
  height: 10rem;
  width: 10rem;
  padding: 0.2rem;
  border-radius: 10px;
  filter: brightness(80%);
}

.chosen-image:hover,
.chosen-image:active {
  filter: brightness(100%);
}
</style>
