<template>
  <section>
    <h1>User Credentials</h1>
    <div class="user-details" v-if="user">
      <div class="image">
        <img :src="user.image" alt="" />
      </div>
      <div class="info">
        <h2>{{ user.fullName }}</h2>
        <div class="email">
          <h4>Email:</h4>
          <p>{{ user.email }}</p>
        </div>
        <div class="name">
          <h4>Name:</h4>
          <p>{{ user.fullName }}</p>
        </div>
        <div class="gender">
          <h4>Gender:</h4>
          <p>{{ user.gender }}</p>
        </div>
        <div class="description">
          <h4>Description:</h4>
          <p>{{ user.description }}</p>
        </div>
      </div>
    </div>
    <button class="create-button" @click="showCreate = !showCreate">
      Edit User Info
    </button>
    <div v-if="showCreate">
      <UserCreation :user="user" />
    </div>
  </section>
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
section {
  padding: 2rem;
}

table {
  /* border: 1px solid black; */
  margin: 0 auto;
  font-size: 1.5rem;
  background: white;
}

th {
  text-align: center;
}

td,
th {
  /* border-bottom: 1px solid rgba(0, 0, 0, 0.164); */
  /* border: 1px solid orange; */
  padding: 1rem 3rem;
  /* width: 100%; */
  /* border: 1px solid black; */
  line-height: 120%;
  border-bottom: 1px solid rgba(0, 0, 0, 0.226);
}

/* .create-button {
  margin-top: 5rem;
  margin-bottom: 3rem;
  height: 2rem;
  color: black;
  border-radius: 10px;
  outline: none;
  cursor: pointer;
} */

button {
  margin-top: 3rem;
  width: 20%;
  height: 4rem;
  border-radius: 10px;
  outline: none;
  font-weight: 700;
  cursor: pointer;
  /* background: rgb(235, 235, 235); */
  background: transparent;
  transition: all 0.2s ease;
  border: 0;
  /* border: 1px solid green; */
  background-image: url('https://www.getaccept.com/hubfs/Product%20pages%202.0/Product%20tour/bottom%20wave.svg');
  background-repeat: no-repeat;
  background-size: contain;
  background-size: cover;
  filter: brightness(130%);
}

button:hover,
button:active {
  box-shadow: rgba(0, 0, 0, 0.25) 0px 5px 15px;
  filter: brightness(100%);
}

h1 {
  margin: 4rem auto;
  font-size: 2rem;
}

h2 {
  margin: 1rem 0;
  font-size: 1.5rem;
}

.user-details {
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  background-color: rgb(235, 235, 235);
  padding: 2rem;
  box-shadow: rgba(38, 57, 77, 0.3) 0px 20px 30px -10px;
  justify-content: center;
  align-items: center;
  border: 1px solid rgba(0, 0, 0, 0.082);
  border-bottom: 2px solid rgba(0, 0, 0, 0.335);
  line-height: 145%;
}

.image {
  width: 48%;
  margin: 0.5rem;
}

img {
  margin-top: 2rem;
  max-width: 100%;
  height: auto;
  border-radius: 50%;
  object-fit: contain;
}

.info {
  width: 48%;
  display: flex;
  flex-flow: wrap;
  text-align: left;
}

.info h2 {
  margin-top: 2rem;
  max-width: 100%;
}
.info p {
  display: inline;
}
.info h4 {
  display: inline;
  margin-bottom: 0.1rem;
  margin-right: 0.2rem;
}
.email,
.name,
.gender {
  width: 100%;
  margin: 0.1rem;
}
.info .description {
  display: flex;
  flex-direction: column;
}
.info .description h4 {
  width: 100%;
  margin: 0;
}
.description p {
  display: contents;
  margin-top: 0.2rem;
}
@media screen and (max-width: 600px) {
  .image,
  .info {
    width: 100%;
  }
}
</style>
