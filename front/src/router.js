import Vue from 'vue'
import Router from 'vue-router'
import home from './views/home'
import login from './views/login'

import teacher from "./views/teacher/router.js"
import college from "./views/college/router.js"
import admin from "./views/admin/router.js"

Vue.use(Router)

export default new Router({
  mode: 'hash',
  base: process.env.BASE_URL,
  routes: [
      {
          path: '/',
          name: 'login',
          component: login,
      },
      {
          path:'/home',
          name:'home',
          component:home,
          children:[
              //教师
              ...teacher,
              //校级管理
              ...admin,
              //院级管理
              ...college,
          ]
      },
      {
          path:'/login',
          name:'login',
          component:login
      }


  ]
})
