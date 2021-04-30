<template>
  <div @click="goToDetails" :class="'image'" :key="'index'">
    <!-- v-bind: (short ':') binds an attribute
          to a reactive varaible -->
    <img v-bind:src="property.imageUrl" alt="" />
  </div>
</template>

<script>
export default {
  data() {
    return {
      image: 'img-' + (index + 1),
    };
  },
  props: ['property'],

  methods: {
    goToDetails() {
      this.$router.push('/property/' + this.property.id);
    },
  },
};
</script>

<style></style>
