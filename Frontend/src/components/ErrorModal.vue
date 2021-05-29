<template>
  <div class="backdrop" @click="$emit('closeModal')"></div>
  <article>
    <h1>{{errorMessage}}</h1>
       <hr />
      <p>{{errorDescription}}</p>
  <button @click="$emit('closeModal')">Close</button>
  </article>
</template>

<script>
export default {
  emit:['closeModal'],
  props: ['errorMessage', 'errorDescription']
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
  background: white;
  position: fixed;
  z-index: 5;
  font-size: 3rem;
  padding: 3rem;
  left: 0;
  right: 0;
  top: 0;
  bottom: 0;
  max-height: 40%;
  margin: auto auto;
  width: 40%;
  display: flex;
  flex-direction: column;
  border-radius: 10px;
}

p {
  font-size: 2rem;
}

button {
  margin-top: 3rem;
  width: 20%;
  height: 4rem;
  border-radius: 10px;
  outline: none;
  font-weight: 700;
  cursor: pointer;
  background: transparent;
  transition: all 0.2s ease;
  border: 0;
  background-image: url('https://www.getaccept.com/hubfs/Product%20pages%202.0/Product%20tour/bottom%20wave.svg');
  background-repeat: no-repeat;
  background-size: contain;
  background-size: cover;
  filter: brightness(130%);
}

h1 {
  margin: 0;
}

hr {
  width: 100%;
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