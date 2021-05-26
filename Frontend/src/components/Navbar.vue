<template>
  <header class="header">
    <div class="logo">
      <router-link to="/"
        ><img
          class="logo"
          src="../../public/images/clearbnb_logo1.png"
          alt="clearbnb_logo"
      /></router-link>
    </div>

    <form @submit.prevent="searchByLocation" class="header-search">
      <input v-model="searchCity" type="text" placeholder="Enter location..." />
      <div class="header-searchIcon">
        <div class="favorite-icon">
          <button type="submit">
            <img src="../../public/images/search1.png" alt="Search Icon" />
          </button>
        </div>
      </div>
    </form>

    <div class="nav">
      <button class="nav-button account" @click="isOpen = !isOpen">
        <i class="fas fa-bars fa-lg"></i>
        <i class="fas fa-user-circle"></i>
      </button>
      <div class="login-form" v-if="isOpen">
        <form>
          <div
            class="logIn"
            @click="isOpen = false"
            v-if="this.$store.state.user == null"
          >
            <router-link to="/login-page">Log in</router-link>
          </div>
          <div
            @click="isOpen = false"
            class="signUp"
            v-if="this.$store.state.user == null"
          >
            <router-link to="/register-page">Sign up</router-link>
          </div>
          <div
            class="signUp"
            v-if="this.$store.state.user != null"
            @click="
              this.$router.push('/user/' + this.$store.state.user.id);
              isOpen = false;
            "
          >
            My Page
          </div>
          <div
            class="signUp"
            v-if="this.$store.state.user != null"
            @click="
              logout();
              isOpen = false;
            "
          >
            <router-link to="/">Logout</router-link>
          </div>
        </form>
      </div>
    </div>
  </header>
</template>

<script>
export default {
  name: 'navbar',

  data() {
    return {
      isOpen: false,
      searchCity: '',
    };
  },

  methods: {
    logout() {
      this.$store.dispatch('logout');
      if (!this.$route.params.id) {
        this.$router.go();
      }
      console.log(this.$store.state.user);
    },

    searchByLocation() {
      let searchCity = this.searchCity;
      this.$store.commit('setCitySearch', searchCity);
      this.$router.push('/search-results');
      this.searchCity = '';
    },
  },
};
</script>

<style>
:root {
  --grey--med: #ddd;
  --grey--light: #f7f7f7;
  --grey--text--light: #717171;
  --grey-text-dark: #222;
  --pink: rgb(254, 149, 0);
}

.logo {
  width: 150px;
  height: 95px;
  margin: 7px 0 0 0;
}

.logo .nav {
  flex: 1 0 140px;
}

.header {
  top: 0%;
  height: 80px;
  display: flex;
  align-items: center;
  justify-content: space-around;

  width: 100%;
  z-index: 2;
  background: white;
  box-shadow: rgba(0, 0, 0, 0.08) 0px 1px 12px;
  background-image: url('https://www.getaccept.com/hubfs/Product%20pages%202.0/Product%20tour/bottom%20wave.svg');
  background-repeat: no-repeat;
  background-size: cover;
  background-position-y: 2rem;
}

.header-option ul {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.header-search {
  display: inline-flex;
  border-radius: 24px;
  overflow: hidden;
  align-items: center;
  border: 1px solid var(--grey--med);
  transition: ease 0.3s;
  padding: 2px 8px;
  box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.1);
  background: white;
}

.header-search:hover {
  box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.2);
}

.header-search input {
  outline: none;
  background: transparent;
  height: 38px;
  padding: 0 16px;
  display: flex;
  align-items: center;
  border: none;
  font-family: Helvetica, Arial, sans-serif;
}

.header-searchIcon {
  width: 32px;
  height: 32px;
  border-radius: 50%;
  background: var(--pink);
  display: flex;
  align-items: center;
  justify-content: center;
  margin-left: 14px;
  z-index: 5;
}

.header-searchIcon img {
  width: 2rem;
  height: 1.2rem;
  padding: 0;
}

.nav {
  /* display: flex;
   justify-content: flex-end; */

  -webkit-box-pack: end !important;
  -ms-flex-pack: end !important;
  -webkit-box-align: center !important;
  -ms-flex-align: center !important;
  position: relative !important;
  display: -webkit-box !important;
  display: -moz-box !important;
  display: -ms-flexbox !important;
  display: -webkit-flex !important;
  display: flex !important;
  -webkit-align-items: center !important;
  align-items: center !important;
  -webkit-justify-content: flex-end !important;
  justify-content: flex-end !important;
  height: 80px !important;
}

.favorite-icon button {
  background: none;
  border: none;
  position: relative;
  display: flex;
  align-items: center;
  justify-content: center;
  cursor: pointer;
}

.fa-user-circle {
  font-size: 30px;
  color: rgb(254, 149, 0);
}

.fa-bars {
  color: rgb(254, 149, 0);
}

.fa-globe {
  font-size: 17px;
}

.nav {
  display: flex;
  justify-content: flex-end;
}

.nav-button {
  height: 48px;
  background: transparent;
  border: none;
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 10px 16px;
  cursor: pointer;
  border-radius: 22px;
  background: white;
  color: rgb(34, 34, 34);
}

.globe {
  transition: ease 0.3s;
  text-decoration: none;
  color: black;
  font-weight: bold;
}

.globe:hover {
  background: var(--grey--med);
}

.account {
  height: 42px;
  border-radius: 22px;
  border: 1px solid var(--grey--med);
  transition: ease 0.3s;
  padding: 5px 5px 5px 12px;
}

.account:hover {
  box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.18);
}

.account i:last-child {
  height: 30px;
  margin-left: 16px;
}

.account i:first-child {
  height: 12px;
}

.login-form {
  position: absolute;
  width: 245px;
  height: 200px;
  z-index: 4;
  background: rgb(252, 252, 252);
  right: 0;
  top: 75px;
  border-radius: 14px;
  box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.1);
  border: 1px solid var(--grey--med);
}

.login-form form {
  margin: 10px;
  font-size: 20px;
}

.logIn {
  padding: 30px;
}

.logIn a {
  color: var(--grey-text-dark);
  text-decoration: none;
}

.signUp {
  padding: 30px;
  cursor: pointer;
}

.signUp a {
  color: var(--grey-text-dark);
  text-decoration: none;
}

.signUp:hover {
  border-radius: 35px;
  background: var(--grey--light);
}

.logIn:hover {
  border-radius: 35px;
  background: var(--grey--light);
}

@media only screen and (max-width: 650px) {
  .header-search,
  .header-search input {
    -webkit-box-align: center !important;
    align-items: center !important;
    display: flex !important;
    height: 44px !important;

    white-space: nowrap !important;
  }

  .logo {
    display: none;
  }
}

@media only screen and (max-width: 450px) {
  .header-search {
    width: 65%;
  }

  .header-search input {
    width: 100%;
  }
}
</style>
