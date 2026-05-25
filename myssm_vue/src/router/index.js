import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
//1.用于指定/配置路由
//2.path: '/' 当访问http://localhost:8080/，就路由到HomeView组件
const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/about',
    name: 'about',
    component: () => import('../views/AboutView.vue')
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
