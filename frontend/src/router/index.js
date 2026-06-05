import { createRouter, createWebHistory } from 'vue-router';
import DashboardView from '../views/DashboardView.vue';
import ServicesView from '../views/ServicesView.vue';
import HistoryView from '../views/HistoryView.vue';

const router = createRouter({
  history: createWebHistory(),
  routes: [
    { path: '/', name: 'dashboard', component: DashboardView },
    { path: '/services', name: 'services', component: ServicesView },
    { path: '/history', name: 'history', component: HistoryView }
  ]
});

export default router;
