<template>
  <div class="form-container">
    <form>
      <img src="/images/login.png" />
      <input type="email" placeholder="E-mail..." required />
      <input type="password" placeholder="Password..." required />
      <button class="login" type="submit" value="submit">Logga in</button>
      <hr />
      <router-link to="/">
        <button class="cancel" type="button">Cancel</button>
      </router-link>
      <router-link class="register" to="/register-page"
        >Register new account</router-link
      >
    </form>
  </div>
</template>

<script>
export default {};
</script>

<style scoped>
.form-container {
  padding: 3rem;
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
}

img {
  width: 100%;
  max-width: 300px;
  height: auto;
  align-self: center;
  margin: 2rem;
  border-radius: 8px;
  opacity: 0.8;
  min-height: 100px;
  object-fit: contain;
}
form {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  max-width: 30rem;
  height: 35rem;
  background-color: #808080;
  border-radius: 8px;
}
input {
  width: 45vw;
  min-width: 130px;
  max-width: 220px;
  height: 1.4rem;
  margin: 0.5rem auto;
  border-radius: 4px;
  border: 0;
  background-color: #e9e9e9;
}
input:hover {
  background-color: #a9a9a9;
}
input:last-of-type {
  margin-bottom: 1rem;
}
button {
  width: 45vw;
  min-width: 130px;
  max-width: 220px;
  height: 1.4rem;
  margin: 0.5rem auto;
  font-size: 1rem;
  cursor: pointer;
  background-image: linear-gradient(147deg, #6b6b6b 0%, #4b4b4b 84%);
  border: #4b4b4b;
  border-radius: 4px;
  color: #e9e9e9;
  text-decoration: none;
  align-items: center;
  justify-content: center;
  display: flex;
  background-color: #c7c7c7;
}
button:hover {
  background: #c7c7c7;
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
