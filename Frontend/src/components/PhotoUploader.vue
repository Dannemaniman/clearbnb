<template>
  <form @submit.prevent="sendFile" enctype="multipart/form-data">
    <div
      v-if="message"
      :class="`message ${error ? 'is-danger' : 'is-success'} `"
    >
      <div class="message-body">{{ message }}</div>
    </div>
    <div class="dropzone">
      <input type="file" class="input-field" />

      <p v-if="!uploading" class="call-to-action">Drag your files here</p>

      <p v-if="uploading" class="progress-bar"></p>
    </div>
  </form>
</template>

<script>
export default {
  name: 'Dropzone',

  data() {
    return {
      file: '',
      messege: '',
      error: false,
      uploading: false,
      // gammalt under
      selectedFiles: [],
      thumbnail: [],
    };
  },
  emit: ['photo'],
  methods: {
    selectFile() {
      const file = this.$refs.file.files[0];
      this.selectedFiles.push(file);
      this.thumbnail.push(URL.createObjectURL(file));
      this.$emit('photo', this.selectedFiles);
    },

    chosenFile(event) {
      this.selectedFiles.push(event.target.files[0]);
      this.thumbnail.push(URL.createObjectURL(event.target.files[0]));
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
  outline-offset: -10px;
  background: lightgray;
  color: black;
}

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
  box-shadow: rgba(50, 50, 93, 0.25) 0px 30px 60px -12px inset,
    rgba(0, 0, 0, 0.3) 0px 18px 36px -18px inset;
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
