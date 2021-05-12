<template>
<div class="auth-wrapper">
<div class="auth-inner">
   <form @submit.prevent="login">
  <img class="mb-4" src="../../public/images/clearbnb_logo1.png" alt="" width="260" height="200">
  <h1 class="h3 mb-3 font-weight-normal">Please sign in</h1>
  <input type="text"  class="form-control" v-model="email" placeholder="Email address" required autofocus>
  <input type="password"  class="form-control" v-model="password" placeholder="Password" required>
  <div class="checkbox mb-3">
    <label>
      <input type="checkbox" value="remember-me"> Remember me
    </label>
  </div>
  <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
  <p class="forgot-password text-right"><router-link to="forgot">Forgot password</router-link></p>
  <div>New User?<a href="/register">Create new account</a></div>
  
</form>
</div>
</div>
</template>
  
        
   

<script>
export default {
  name: 'Login',
 
  data(){
    return{
    
      email: '',
      password: '',
    }
  },
  methods:{
     login(){
       let credentails ={
        email : this.email,
        password: this.password,
     
      }

      this.$store.dispatch('login', credentails)
      this.$store.dispatch('user', credentails)
      this.$router.push('/');

    },
    logout(){
      this.$store.dispatch('logout')
    }
  }

}
</script>

<style>
 


</style>