<template>
  <div class="form-container">
    <form @submit.prevent="login">
      <h1>
        <span> Login </span>
      </h1>
      <input v-model="email" type="email" placeholder="E-mail..." required />
      <input
        v-model="userPassword"
        type="password"
        placeholder="Password..."
        required
      />
      <button class="login">Login</button>
      <hr />
      <!--<router-link to="/">
        <button class="cancel" type="button">Cancel</button>
      </router-link> -->
      <router-link class="register" to="/register-page"
        >Register new account</router-link
      >
    </form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      email: '',
      userPassword: '',
    };
  },

  methods: {
    login() {
      let credentials = {
        email: this.email,
        userPassword: this.userPassword,
      };
      this.$store.dispatch('login', credentials);
      this.$router.push('/');
    },
  },
};
</script>

<style scoped>
.form-container {
  padding: 3rem;
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
}

@media (max-width: 650px) {
  .form-container {
    padding-left: 0;
    padding-right: 0;
  }
}

form {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  max-width: 30rem;
  height: 35rem;
  border-radius: 8px;
  background-size: cover;
  width: 100%;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.26);
}

form span {
  padding-bottom: 0.6rem;
  border-bottom: 0.4rem solid rgb(254, 149, 0);
}

input {
  width: 45vw;
  min-width: 130px;
  max-width: 220px;
  height: 2rem;
  margin: 0.5rem auto;
  border: none;
  border-bottom: 1px solid rgb(0, 0, 0);
  outline: none;
}

input:first-of-type {
  margin-top: 2rem;
}

input:last-of-type {
  margin-bottom: 2rem;
}

button {
  width: 45vw;
  min-width: 130px;
  max-width: 220px;
  height: 1.4rem;
  margin: 0.5rem auto;
  font-size: 1rem;
  cursor: pointer;
  background-image: linear-gradient(
    147deg,
    rgb(254, 149, 0) 0%,
    rgb(254, 120, 0) 84%
  );
  border: #4b4b4b;
  border-radius: 4px;
  color: #4b4b4b;
  text-decoration: none;
  align-items: center;
  justify-content: center;
  display: flex;
  background-color: #c7c7c7;
}

button:hover {
  background-image: linear-gradient(
    147deg,
    rgb(185, 108, 0) 0%,
    rgb(228, 108, 3) 84%
  );
  color: #4b4b4b;
}

.login {
  height: 2.5rem;
}

hr {
  margin: 1rem 0;
  border: 0;
  height: 1px;
  width: 100%;
  background-image: linear-gradient(
    to right,
    rgba(255, 255, 255, 0),
    rgba(255, 255, 255, 0.75),
    rgba(255, 255, 255, 0)
  );
}

.register {
  text-decoration: none;
  margin-top: 3rem;
  font-size: 1rem;
  color: #4b4b4b;
}
</style>
