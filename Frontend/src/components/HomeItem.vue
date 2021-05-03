<template>
  <div class="card">
    <img v-bind:src="home.images" alt="">
    <h3>{{ home.title }}</h3>
    <p>{{ home.id }}</p>
    <p>{{ home.address }}</p>
    <p>{{ home.city }}</p>
    <p>{{ home.propertyType }}</p>
    <p>{{ home.price }}</p>
    <p>{{ home.ownerId }}</p>
    <p>{{ home.description }}</p>
  </div>
</template>

<script>
export default {
  props: ['home']
}
</script>

<style>

</style>