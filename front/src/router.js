import Vue from 'vue'
import Router from 'vue-router'
import home from './views/home'
import login from './views/login'
import page from './views/page'
import { Message } from 'element-ui';

import teacher from "./views/teacher/router.js"
import college from "./views/college/router.js"
import admin from "./views/admin/router.js"
import labor from "./views/labor/router.js"
Vue.use(Router)
let routes=[
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


const router = new Router({

  mode: 'history',
  base: process.env.BASE_URL,
   routes,
})

const errFunction=function(){

    //菜单自动变为指向首页
    sessionStorage.setItem('navDefaultActive',''+"0")
    Message({type:"warning",message:"权限不符号"});
}

router.beforeEach((to, from, next) => {
    //console.error(store.state.userJurisdiction +"    5")
    let user =localStorage.getItem('user_id');

    if (!user||user==="" ) {
        if (to.meta.require_teacher || to.name == null ) {
            console.error("/没有权限")
            next({path: '/login'})
        } else {
            next()
        }
    }else if(user) {

        //如果是学校权限访问非权限界面
        if (user === "0") {
            if (
                to.meta.require_college ||
                to.meta.require_labor ||
                to.meta.require_teacher){

                    errFunction()
                    next({path:'/adminHome'})}
        }
        //如果是学院室权限访问非权限界面
        if (user === "1") {
            if (
                to.meta.require_admin ||
                to.meta.require_labor ||
                to.meta.require_teacher){
                    errFunction()
                    next('/collegeHome')}
        }
        //如果是实验室权限访问非权限界面
        if (user === "2") {
            if (
                to.meta.require_admin ||
                to.meta.require_college ||
                to.meta.require_teacher){
                    errFunction()
                    next({path:'/laborHome'})
            }
        }
        //如果是教师权限访问非权限界面
        if (user === "3") {
            if (
                to.meta.require_admin ||
                to.meta.require_college ||
                to.meta.require_labor){
                    errFunction()
                    next({path: '/teacherHome'})
            }
        }
        next();
    }
})



export default router
