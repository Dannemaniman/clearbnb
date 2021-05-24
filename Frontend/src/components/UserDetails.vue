<template>
  <button class="create-button" @click="showCreate = !showCreate">
    Edit User Info
  </button>
  <div v-if="showCreate">
    <UserCreation :user="user" />
  </div>
  <div class="user-details" v-if="user">
    <div class="image">
      <img src="../../public/stockman.jpg" alt="" />
    </div>
    <div class="info">
      <h1>{{ user.fullName }}</h1>
      <p>Username: {{ user.userName }}</p>
      <p>Email: {{ user.email }}</p>
    </div>
  </div>
</template>

<script>
import UserCreation from './UserCreation.vue';

export default {
  components: {
    UserCreation,
  },
  data() {
    return {
      user: null,
      showCreate: false,
    };
  },

  async created() {
    let id = this.$route.params.id;
    let res = await fetch('/rest/users/' + id);
    let user = await res.json();
    this.user = user;
  },
};
</script>

<style scoped>
.create-button {
  margin-top: 5rem;
  margin-bottom: 3rem;
  height: 2rem;
  color: black;
  border-radius: 10px;
  outline: none;
  cursor: pointer;
}

.user-details {
  display: flex;
  flex-direction: row;
}

.image {
  width: 50%;
}

img {
  margin-top: 4rem;
  width: 450px;
  height: 300px;
  border-radius: 10px;
}

.info {
  width: 50%;
}

.info h1 {
  margin-top: 4rem;
  padding-bottom: 0.5rem;
}
</style>
