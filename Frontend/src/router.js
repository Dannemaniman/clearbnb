import { createRouter, createWebHistory } from 'vue-router';
import store from './store.js';

// import views
import Home from './views/Home.vue';
import LoginPage from './views/LoginPage.vue';
import RegisterPage from './views/RegisterPage.vue';
import HouseDetails from './views/HouseDetails.vue';
import SearchResultsPage from './views/SearchResultPage.vue';
import UserPage from './views/UserPage.vue';
import ConfirmPage from './views/ConfirmPage.vue';

const router = createRouter({
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
      path: '/search-results',
      component: SearchResultsPage,
    },
    {
      path: '/user/:id',
      component: UserPage,
      meta: { requiresAuth: true },
    },
    {
      path: '/confirm',
      component: ConfirmPage,
    },
  ],
});

/* router.beforeEach((to, from, next) => {
  let routerAuthCheck;
  if (store.state.user == null) {
    routerAuthCheck = false;
  } else {
    routerAuthCheck = true;
  }
  // Check if route requires authentication
  if (to.matched.some((record) => record.meta.requiresAuth)) {
    // Check if user is authenticated
    if (routerAuthCheck) {
      // User is authenticated
      // TODO: commit to Store that the user is authenticated
      next();
    } else {
      // User is not authenticated
      router.replace('/login-page');
    }
  }
  // Allow page to laod if it doesn't require authentication
  else {
    next();
  }
}); */

export default router;
