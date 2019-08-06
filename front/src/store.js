import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    diaName:"@/views/college/class/createClass",
    navDefaultActive : '0',
    ownWorkTime:0,
    detailsRow:{},
    count:10,
    offset:0,
    teacherInformation:JSON.parse(sessionStorage.getItem("teacherInformation")),
    tableCollege:[
      {
        collegeId:123,
        collegeName:"大数据"
      },
      {
        collegeId:123135,
        collegeName:"土木"
      },
      {
        collegeId:1251515,
        collegeName:"理学院"
      },

    ]

        //localStorage.getItem('tableCollege')
  },
  mutations: {},
  actions: {}
})
