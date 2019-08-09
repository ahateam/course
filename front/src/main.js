import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import './assets/icon/iconfont.css'
import constData from './data/constData'

import uitl from 'ahaapi'
import admin from './assets/util/admin'
import login from './assets/util/login'
import college from './assets/util/college'
import labor from './assets/util/labor'

import "./assets/css/table_width_margin.css";

import getCollegeName from "./getCollegeName"
Vue.prototype.$getCollegeName= getCollegeName

 import axios from "axios"
 Vue.prototype.$ajax= axios


//公共组件
import publicTitle from './views/components/'
Vue.use(publicTitle)


Vue.use(ElementUI)
Vue.config.productionTip = false

Vue.prototype.$login = login
Vue.prototype.$labor = labor
Vue.prototype.$college = college
Vue.prototype.$admin = admin
Vue.prototype.$constData = constData
Vue.prototype.$util = uitl

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
