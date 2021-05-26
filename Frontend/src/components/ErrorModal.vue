<template>
  <div class="backdrop" @click="$emit('closeModal')"></div>
  <article>
       {{errorMessage}}
      
  <button @click="$emit('closeModal')">Close</button>
  </article>
</template>

<script>
export default {
  emit:['closeModal'],
  props: ['errorMessage']
}
</script>

<style scoped>

.backdrop {
  position: fixed;
  height: 100%;
  width: 100%;
  backdrop-filter: blur(5px);
  filter: brightness(40%);
  top: 0;
  left: 0;
  z-index: 4;
}

article {
  /* height: 40rem; */
  background: white;
  position: fixed;
  z-index: 5;
  font-size: 3rem;
  padding: 3rem;
  left: 0;
  right: 0;
  top: 0;
  bottom: 0;
  height: 20%;
  margin: auto auto;
  width: 40%;
  display: flex;
  flex-direction: column;
}

button {
  font-size: 2rem;
  margin: 0 auto;
  position: absolute;
  left: 0;
  right: 0;
  bottom: 2rem;
}

</style>