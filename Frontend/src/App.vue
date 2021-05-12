<template>
<div>
  <header>
    <Navbar/>
  </header>
  <main>
    <router-view :user="user"/>
  </main>

  <Footer/>
  </div>
</template>

<script>
import Navbar from './views/Navbar.vue'
import Footer from './views/Footer.vue';

export default {
  name: 'App',


  components: {
    Footer,
    Navbar
  },
    data(){
    return{
    
      
    }
  },

    async created() {
  
  
    this.$store.dispatch('fetchHouses');
    this.$store.dispatch('user');
  },
 
};
</script>

<style>

  *{
    margin: 0;
    padding: 0;
    box-sizing: border-box;
    
  }


body {
 min-height: 100vh;
  font-family: Avenir, Helvetica, Arial, sans-serif;
  font-weight: 400;
   width: 100%;
  height: 100%;

}
main {
  flex-grow: 1;
}
.auth-wrapper{
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  flex-direction: column;
  text-align: left;
}
.auth-wrapper h3 {
  text-align: center;
  margin: 0;
  line-height: 1;
  padding-bottom: 20px;
}
.auth-inner{
  width: 450px;
  margin: auto;
  background: #ffffff;
  box-shadow: 0px 14px 80px rgba(34, 35, 58, 0.2);
  padding: 40px 55px 45px 55px;
  border-radius: 15px;
  transition: all .3s;
}

.auth-wrapper .form-control:focus{
  border-color: #167bff;
  box-shadow: none;
}


.custom-control-labl{
  font-weight: 400;

}
h3{
  color: black;
}
.form-group label{
  color: black;
}

.auth-inner button{
border-radius: 9px;
margin: 35px;
padding: 7px 100px;
}
.forget-password,
.forget-password a{
  text-align: right;
  font-size: 13px;
  padding-top: 10px;
  color: #7f7d7d;
  margin: 0;
}
.forget-password a{
  color: #167bff;
}


</style>
