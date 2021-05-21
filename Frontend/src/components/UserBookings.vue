<template>
  <h1>Bookings</h1>
  <ListItem :item="booking"/>
</template>

<script>
import ListItem from "./ListItem.vue"

export default {
props: ['booking']
};
</script>

<style></style>
