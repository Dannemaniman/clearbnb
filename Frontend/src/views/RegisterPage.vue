<template>
 <main class="form-signin">
    <form>
  <h1 class="h3 mb-3 font-weight-normal">Please sign in</h1>
   <label for="inputEmail" class="sr-only">Name</label>
  <input v-model="firstName" type="text"  class="form-control" placeholder="Name" required autofocus>
  <label  for="inputEmail" class="sr-only">Email address</label>
  <input  v-model="email" type="text"  class="form-control" placeholder="Email address" required autofocus>
  <label for="inputPassword" class="sr-only">Password</label>
  <input v-model="password" type="password"  class="form-control" placeholder="Password" required>
  <input  v-model="confirmPassword " type="password"  class="form-control" placeholder="Password" required>
  <div class="checkbox mb-3">
    <label>
      <input type="checkbox" value="remember-me"> Remember me
    </label>
     </div>
      <button  @click="register" class="w-100 btn btn-lg btn-primary">Register</button>
      <hr />
      <router-link to="/">
        <button class="w-100 btn btn-lg btn-primary" type="button">Cancel</button>
      </router-link>
    </form>
 </main>

</template>

<script>
export default {
  data(){
    return{
      firstName: '',
      email: '',
      password: '',
      confirmPassword: ''
    }
  },
  methods:{
    login(){
      let credentails ={
        email : this.email,
        password: this.password,
     
      }

      this.$store.dispatch('login', credentails)

    },
    register(){
         let credentails ={
        firstName: this.firstName,
        email : this.email,
        password: this.password,
        confirmPassword: this.confirmPassword
      }
        this.$store.dispatch('register', credentails)
    }
  }
};
</script>

<style >

</style>
