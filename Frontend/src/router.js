import { createRouter, createWebHistory } from 'vue-router';

// import views
import Home from './views/Home.vue';
import RegisterPage from './views/RegisterPage.vue';
import HouseDetails from './views/HouseDetails.vue';
import SearchResult from './views/SearchResult.vue';
import Login from './views/Login.vue';

export default createRouter({
  history: createWebHistory(),
  routes: [
    {
      path: '/',
      component: Home,
    },
    {
      path: '/login',
      component: Login,
    },
    {
      path: '/register',
      component: RegisterPage,
    },
    {
      path: '/house/:id',
      component: HouseDetails,
    },
    {
      path: '/result',
      component: SearchResult,

    },
  ],
});
