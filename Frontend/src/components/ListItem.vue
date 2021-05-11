<template>
  <article>
    <img :src="item.image" />
    <div class="item-info">
      <p class="item-type">{{ item.type }}</p>
      <p class="item-adress">{{ item.adress }}, {{ item.city }}</p>
      <p class="item-date">{{ item.date.toLocaleString() }}</p>
    </div>
    <button>Cancel</button>
  </article>
</template>

<script>
export default {
  props: ['item'],
  date() {
    return {
      id: item.id,
    };
  },
};
</script>

<style scoped>
article {
  display: flex;
  flex-direction: row;
  box-shadow: rgba(50, 50, 93, 0.25) 0px 2px 5px -1px,
    rgba(0, 0, 0, 0.3) 0px 1px 3px -1px;
  width: 90%;
  margin: 0 auto;
  position: relative;
  border: 1px solid transparent;
  background-color: whitesmoke;
  border-bottom: 2px solid black;
  transition: all 0.2s ease;
}

article:hover,
article:active {
  box-shadow: rgb(38, 57, 77) 0px 20px 30px -10px;
  border-bottom: 2px solid black;
}

img {
  height: 150px;
  width: 150px;
}

button {
  height: 3rem;
  position: absolute;
  right: 0;
  top: 0;
  border-top: none;
  border-right: none;
  outline: none;
  border-radius: 0 0 0 10px;
  cursor: pointer;
}

button:hover,
button:active {
  color: white;
}

.item-type {
  font-size: 2rem;
  margin-top: 4rem;
  margin-left: 1rem;
  text-align: left;
}

.item-adress {
  margin-left: 1rem;
}

.item-date {
  position: absolute;
  bottom: 0;
  right: 0;
  margin-right: 1rem;
  font-size: 0.7rem;
}
</style>
