<template>
  <div v-if="houses">
    <h2>{{ houses[0].title }}</h2>
    <p>{{ houses[0].address }}</p>
    <p>{{ houses[0].city }}</p>
    <p>{{ houses[0].description }}</p>
  </div>
</template>

<script>
export default {
  data() {
    return {
      user: null,
      houses: [],
    };
  },

  async created() {
    let userId = this.$route.params.id;
    let userRes = await fetch('/rest/users/' + userId);
    let user = await userRes.json();
    this.user = user;

    this.userHouses();
  },

  methods: {
    userHouses() {
      for (let house of this.$store.state.houses) {
        if (this.user.id == house.ownerId) {
          this.houses.push(house);
        }
      }
      console.log(this.houses);
    },
  },
};
</script>

<style></style>
