<template>
<div class="auth-wrapper">
<div class="auth-inner">
<form @submit.prevent="register">
  <h3>Sign Up</h3>

  <div class="form-group">
    <label>First Name</label>
    <input type="text" class="form-control" v-model="firstName" placeholder="First Name"/>
  </div>

 
  <div class="form-group">
    <label>Last Name</label>
    <input type="text" class="form-control" v-model="lastName"  placeholder="Last Name"/>
  </div>

  <div class="form-group">
    <label>Email</label>
    <input type="text" class="form-control" v-model="email"  placeholder="Email"/>
  </div>

  <div class="form-group">
    <label>Password</label>
    <input type="password" class="form-control" v-model="password"  placeholder="Password"/>
  </div>

  <div class="form-group">
    <label>Confirm Password</label>
    <input type="password" class="form-control" placeholder="password_confirm"/>
  </div>
   
  <button class="btn btn-primary btn-block">Sign Up</button>

</form>
 </div>
  </div>
</template>

<script>


export default {
  
  name: "Register",

  data(){
    return{
      firstName: '',
      lastName: '',
      email: '',
      password: '',
      Password_confirm: ''
    }
    
   
  
  },
  methods:{

   
    register(){


      console.log('submitted')
         let credentails ={
        firstName: this.firstName,
        last: this.lastName,
        email : this.email,
        password: this.password,
         Password_confirm: this. Password_confirm
      }

    
        this.$store.dispatch('register', credentails);
        this.$router.push('/login')
       
    }
  }
};
</script>

<style >

</style>
