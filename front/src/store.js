import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    navDefaultActive : '0',
    ownWorkTime:0,
    detailsRow:{},
    count:10
  },
  mutations: {},
  actions: {}
})
