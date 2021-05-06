import { createRouter, createWebHistory } from 'vue-router';

// import views
import Home from './views/Home.vue';
import LoginPage from './views/LoginPage.vue';
import RegisterPage from './views/RegisterPage.vue';
import HouseDetails from './views/HouseDetails.vue';
import AllUsers from './views/AllUsers.vue';
import UserDetails from './views/UserDetails.vue';

export default createRouter({
  history: createWebHistory(),
  routes: [
    {
      path: '/',
      component: Home,
    },
    {
      path: '/login-page',
      component: LoginPage,
    },
    {
      path: '/register-page',
      component: RegisterPage,
    },
    {
      path: '/house/:id',
      component: HouseDetails,
    },
    {
      path: '/all-users',
      component: AllUsers,
    },
    {
      path: '/user/:id',
      component: UserDetails,
    },
  ],
});
