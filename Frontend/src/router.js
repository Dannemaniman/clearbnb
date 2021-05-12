import { createRouter, createWebHistory } from 'vue-router';

// import views
import Home from './views/Home.vue';
import Register from './views/Register.vue';
import HouseDetails from './views/HouseDetails.vue';
import SearchResult from './views/SearchResult.vue';
import Login from './views/Login.vue';
import Booking from './views/Booking.vue';

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
      component: Register,
    },
    {
      path: '/house/:id',
      component: HouseDetails,
    },
     {
      path: '/booking',
      component: Booking,

    },
    {
      path: '/result',
      component: SearchResult,

    },
  ],
});
