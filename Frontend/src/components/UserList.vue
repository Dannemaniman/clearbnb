<template>
  <UserItem v-for="user of users" v-bind:key="user.id" v-bind:user="user" />
</template>

<script>
import UserItem from './UserItem.vue';

export default {
  components: {
    UserItem,
  },

  computed: {
    users() {
      return this.$store.state.users;

      
    },
  },
};
</script>

<style></style>
