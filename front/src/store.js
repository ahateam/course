import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    navDefaultActive : '0',
    ownWorkTime:0,
    detailsRow:{},
    count:10,
    tableCollege:[
      {
        collegeId:12315135,
        collegeName:"大数据"
      },
      {
        collegeId:123135,
        collegeName:"土木"
      },
      {
        collegeId:123155,
        collegeName:"理学院"
      },
    ]

        //localStorage.getItem('tableCollege')
  },
  mutations: {},
  actions: {}
})
