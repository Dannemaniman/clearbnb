<template>
  <header class="header" >
    <div class="logo">
      
     <a href="#"> <img class="logo" src="../../public/images/clearbnb_logo1.png" alt="clearbnb_logo"/></a>
    </div>
 
    <div class="header-search">
      
      <input type="text" v-model="searchLocation" placeholder="Start your search"> <div class="header-searchIcon">
     <div class="favorite-icon">
       <button type="submit" @click="searchSubmit" ><img src="../../public/images/search1.png" alt="Search Icon"></button>
     </div>
      
      </div>
    
    </div>
    <div class="nav">
     

<button class="nav-button account" @click="isOpen = !isOpen">
  <i class="fas fa-bars fa-lg"></i>
  <i class="fas fa-user-circle"></i>
</button>
<div class="login-form" v-if="isOpen">
  <form >
    <div class="logIn" @click="isOpen = !isOpen">
      <router-link to="/login" ><a>Log in</a> </router-link>
    </div>
    <div class="signUp" @click="isOpen = !isOpen">
   <router-link to="/register"><a>Sign up</a></router-link>
    </div>
  </form>
</div>
    </div>
  </header>
</template>


<script>
export default {
  name: 'navbar',

  data(){
    return{
      isOpen: false,
      searchLocation: '',
  };
  },
    methods: {
    searchSubmit() {
      let searchLocation = this.searchLocation;
     this.$store.commit('searchCity', searchLocation);
      this.$router.push('/result');
    
    },
  },


}
</script>
<style >


:root{
  --grey--med:#ddd;
  --grey--light:#f7f7f7;
  --grey--text--light:#717171;
  --grey-text-dark:#222;
  --pink: #ff385c;
}


.logo{
  width: 150px;
  height: 95px;
  margin: 7px 0 0 0;
}
.logo .nav{
  flex: 1 0 140px;
}
.header{
  top: 0%;
  height: 80px;
  display: flex;
  align-items: center;
  justify-content: space-around;
  position: fixed;
  width: 100%;
  z-index: 2;
  background: white;
  box-shadow: rgba(0, 0, 0, 0.08) 0px 1px 12px;
}
.header-option ul {
   display: flex;
   justify-content: space-between;
  
   
  
  align-items: center;
  
}
.header-search{
  display: inline-flex;
  border-radius:  24px;
  overflow: hidden;
  align-items: center;
  border: 1px solid var(--grey--med);
  transition:ease 0.3s;
  padding: 2px 8px;
   box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.10)
}
.header-search:hover{
  box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.20);
}
.header-search input{
  outline: none;
  background: transparent;
  height: 38px;
  padding: 0 16px;
  display: flex;
  align-items: center;
  border: none;
  font-family: Helvetica, Arial, sans-serif;
  cursor: pointer;
   
  
}



.header-searchIcon{
  width: 32px;
  height: 32px;
  border-radius: 50%;
  background:var(--pink);
  display: flex;
  align-items: center;
  justify-content: center;
  margin-left: 14px;
  z-index: 5;

}
 .header-searchIcon img{
   width: 2rem;
   height: 1.2rem;
   padding: 0;
   
   
 }
 .nav{
   /* display: flex;
   justify-content: flex-end; */

    -webkit-box-pack: end ;
    -ms-flex-pack: end;
    -webkit-box-align: center ;
    -ms-flex-align: center;
    position: relative ;
    display: -webkit-box ;
    display: -moz-box ;
    display: -ms-flexbox ;
    display: -webkit-flex ;
    display: flex ;
    -webkit-align-items: center ;
    align-items: center ;
    -webkit-justify-content: flex-end ;
    justify-content: flex-end ;
    height: 80px ;
  
 }
 .favorite-icon button{
  background: none;
  border: none;
  position: relative;
  display: flex;
  align-items: center;
  justify-content: center;
  cursor: pointer;
 
}

 .fa-user-circle{
   font-size: 30px;
   color: var(--grey--text--light);
  
 }
 .fa-globe{
   font-size: 17px;
 }
 .nav{
   display: flex;
   justify-content: flex-end;
 }

 .nav-button{
   height: 48px;
   background: transparent;
   border: none;
   display: flex;
   align-items: center;
   justify-content: center;
   padding: 10px 16px;
   cursor: pointer; 
   border-radius: 22px;

   color: rgb(34, 34, 34);
 }
 .globe{
   transition:ease 0.3s;
   text-decoration: none;
  color: black;
  font-weight: bold;
 }
 .globe:hover{
   background: var(--grey--med);

 }
 .account{
  height: 42px;
  border-radius: 22px;
  border: 1px solid var(--grey--med);
  transition:ease 0.3s;
  padding: 5px 5px 5px 12px;

 }
 .account:hover{
  box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.18);

 }
.account i:last-child{
 
 height: 30px;
 margin-left: 16px;
}
.account i:first-child{

 height: 12px;
 
}
.login-form{
  position: absolute;
  width: 245px;
  height: 200px;
  z-index: 4;
  background: rgb(252, 252, 252);
  right:0;
  top: 75px;
  border-radius: 14px;
  box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.10);
  border: 1px solid var(--grey--med);
  

}

.login-form form{
  
 margin: 10px;
 font-size: 20px;
 
 
}
.logIn{
  padding: 30px;
  
}

.logIn a{
  color: var(--grey-text-dark);
  text-decoration: none; 
}
.signUp{
  padding: 30px;
}


.signUp a{
  color: var(--grey-text-dark);
  text-decoration: none;
}
.signUp:hover{
  border-radius: 35px;
  background: var(--grey--light);
}
.logIn:hover{
   border-radius: 35px;
  background: var(--grey--light);
  
}





@media only screen and (max-width: 768px){
  
.header-search, .header-search input {
     -webkit-box-align: center ;
    align-items: center ;
    display: flex ;
    height: 44px ;
    margin: 0px ;
    white-space: nowrap ;
		width: 100%;
	
}

.nav-button{
  display: none;
}
.logo{
  display: none;
  
  
}
.login-form{
  display: none;
}

}
@media only screen and (max-width: 430px){
.header-search{
 width: 100%;
	
}


.header-search input{  
	width: 100%;

}


}



</style>