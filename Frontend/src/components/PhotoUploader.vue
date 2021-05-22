<template>
  <form @submit.prevent="sendFile" enctype="multipart/form-data">
    <div
      v-if="message"
      :class="`message ${error ? 'is-danger' : 'is-success'} `"
    >
      <div class="message-body">{{ message }}</div>
    </div>
    <div class="dropzone">
      <input type="file" class="input-file" ref="file" @change="selectFile" />

      <p v-if="!uploading" class="call-to-action">Drag your files here</p>

      <p v-if="uploading" class="progress-bar"></p>
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
    async selectFile() {
      const file = this.$refs.file.files[0];
      this.selectedFiles.push(file);
      this.thumbnail.push(URL.createObjectURL(file));
      this.$emit('photo', this.selectedFiles);

      // upload files with FormData
      let files = this.$refs.file.files;

      if (files.length) {
        let formData = new FormData();

        // add files to formData
        for (let file of files) {
          formData.append('files', file, file.name);
        }

        // upload selected files to server
        let uploadResult = await fetch('/api/uploads/', {
          method: 'POST',
          body: formData,
        });

        // get the uploaded file urls from response
        let uploadNames = await uploadResult.json();
        console.log(uploadNames);
      }
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
.dropzone:hover {
  background: rgb(184, 184, 184);
}

.input-file {
  opacity: 0;
  width: 100%;
  height: 200px;
  position: absolute;
  cursor: pointer;
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
