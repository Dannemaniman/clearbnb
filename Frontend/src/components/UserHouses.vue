<template>
  <button>New House</button>
  <UserHouseItem
    v-for="(userHouse, index) of userHouses"
    v-bind:key="index"
    v-bind:house="userHouse"
  />
</template>

<script>
import UserHouseItem from './UserHouseItem.vue';
export default {
  components: {
    UserHouseItem,
  },
  computed: {
    houses() {
      return this.$store.state.houses;
    },
  },

  data() {
    return {
      user: null,
      userHouses: [],
    };
  },

  async created() {
    let userId = this.$route.params.id;
    let userRes = await fetch('/rest/users/' + userId);
    let user = await userRes.json();
    this.user = user;

    let userHouses = [];
    for (let house of this.houses) {
      if (this.user.id == house.ownerId) {
        userHouses.push(house);
      }
    }
    this.userHouses = userHouses;
  },
};
</script>

<style scoped>
button {
  margin-top: 1rem;
  width: 20%;
  height: 4rem;
  border-radius: 10px;
  outline: none;
  font-weight: 700;
  cursor: pointer;
  background-color: rgb(235, 235, 235);
  transition: all 0.2s ease;
}

button:hover {
  box-shadow: rgba(0, 0, 0, 0.35) 0px 5px 15px;
  background-color: #a9a9a9;
}

button:active {
  background-color: #a9a9a9;
}
</style>
