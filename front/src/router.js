import Vue from 'vue'
import Router from 'vue-router'
import home from './views/home'
import login from './views/login'
import page from './views/page'

import teacher from "./views/teacher/router.js"
import laboratory from "./views/laboratory/router.js"
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
              //教师s
              ...teacher,
              // //校级管理
              ...admin,
              // //院级管理
              ... college,
              // //实验室管理
              ...laboratory
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
