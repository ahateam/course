import Vue from 'vue'
import Router from 'vue-router'
import home from './views/home'
import login from './views/login'
import page from './views/page'

import teacher from "./views/teacher/router.js"
import college from "./views/college/router.js"
import admin from "./views/admin/router.js"
import labor from "./views/labor/router.js"
Vue.use(Router)
export default new Router({
  mode: 'hash',
  base: process.env.BASE_URL,
  routes: [
      { path: "/", redirect: "/login" },

      {
          path:'/home',
          name:'home',
          component:home,
          children:[
              ...admin,
              ...college,
              ...teacher,
              ...labor,
          ]
      },
      {
          path:'/login',
          name:'login',
          component:login
      },
      {
          path:'/page',
          name:'page',
          component:page
      }


  ]
})
