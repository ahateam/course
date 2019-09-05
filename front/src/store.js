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
    teacherInformation:JSON.parse(localStorage.getItem("teacherInformation")),
    tableCollege:JSON.parse(localStorage.getItem("tableCollege")),
    occupyTeacher:{}

    //获取学院名称
    // getCollegeName(collegeId){
    //   let tableCollege=this.state.teacherInformation
    //   console.log(collegeId)
    //   teacherInformation.forEach((val,index,arr)=>{
    //     if(val.collegeId===collegeId){
    //       console.log(555)
    //       return val.collegeName
    //     }
    //   })
    // }
        // [
        //     {
        //         collegeId:123,
        //         collegeName:"大数据"
        //     },
        //         {
        //             collegeId:123135,
        //             collegeName:"土木"
        //         },
        //         {
        //             collegeId:1251515,
        //             collegeName:"理学院"
        //         },
        //
        //     ]

        //localStorage.getItem('tableCollege')
  },
  mutations: {
    getCollegeName(state,collegeId){
      let teacherInformation=this.teacherInformation
      console.log(state)
      teacherInformation.forEach((val,index,arr)=>{
        if(val.collegeId===collegeId){
          return val.collegeName
        }
      })
    }
  },
  actions: {}
})
