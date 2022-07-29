import { createRouter, createWebHistory } from 'vue-router'
import LoginView from '../views/LoginView.vue'
import SignupView from '../views/SignupView.vue'
import LobbyView from '../views/LobbyView.vue'
import WaitView from '../views/WaitView.vue'
import IngameView from '../views/IngameView.vue'
import FinalView from '../views/FinalView.vue'
import MyPageView from '../views/MyPageView.vue'
import EditUserView from '../views/EditUserView.vue'

const routes = [
  {
    path: '/',
    name: 'login',
    component: LoginView
  },
  {
    path: '/signup',
    name: 'signup',
    component: SignupView
  },
  {
    path: '/lobby',
    name: 'lobby',
    component: LobbyView
  },
  {
    path: '/:roomnumber/wait',
    name: 'wait',
    component: WaitView
  },
  {
    path: '/:roomnumber/ingame',
    name: 'ingame',
    component: IngameView
  },
  {
    path: '/:roomnumber/final',
    name: 'final',
    component: FinalView
  },
  {
    path: '/:id_pk/profile',
    name: 'profile',
    component: MyPageView
  },
  {
    path: '/:id_pk/edit',
    name: 'edituser',
    component: EditUserView
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
