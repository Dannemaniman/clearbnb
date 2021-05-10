<template>
  <h1>Details</h1>
  <div class="user-details" v-if="user">
    <div class="image">
      <img src="../../public/pngegg.png" alt="" />
    </div>
    <div class="info">
      <h1>{{ user.fullName }}</h1>
      <p>Username: {{ user.userName }}</p>
      <p>Email: {{ user.email }}</p>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      user: null,
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
.user-details {
  display: flex;
  flex-direction: row;
}

.image {
  width: 50%;
}

.info {
  width: 50%;
}

.info h1 {
  margin-top: 4rem;
  padding-bottom: 0.5rem;
}
</style>
