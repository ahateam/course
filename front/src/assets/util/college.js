import util from 'ahaapi'
import baseUrl from './url'

let api = {};
//console.log(baseUrl)
//console.log('开始调用ctrl');


//导入档期表
api.importSchedules = function (cnt,callback) {
    util.call(baseUrl+'/sch/importSchedules', cnt, callback)
}
//请求列表
api.getSchedule = function (cnt,callback) {
    util.call(baseUrl+'/sch/getSchedule', cnt, callback)
}
//删除单个档期信息
api.deleteSchedule = function (cnt,callback) {
    util.call(baseUrl+'/sch/deleteSchedule', cnt, callback)
}

/**
 *开设课程
 */

//获取课程大纲
api.getCourseOutlineByTermId = function (cnt,callback) {
    util.call(baseUrl+'/sch/getCourseOutlineByTermId', cnt, callback)
}

//新增大纲
api.createCourseOutline = function (cnt,callback) {
    util.call(baseUrl+'/sch/createCourseOutline', cnt, callback)
}

//删除大纲
api.delCourseOutline = function (cnt,callback) {
    util.call(baseUrl+'/sch/delCourseOutline', cnt, callback)
}

//修改大纲

api.editCourseOutline = function (cnt,callback) {
    util.call(baseUrl+'/sch/editCourseOutline', cnt, callback)
}


//获取院内教师
api.getCollegeTeacher = function (cnt,callback) {
    util.call(baseUrl+'/sch/getCollegeTeacher', cnt, callback)
}
//工号查询教师
api.lookupCollegeTeacher = function (cnt,callback) {
    util.call(baseUrl+'/sch/lookupCollegeTeacher', cnt, callback)
}

/**
* collegeOpen 开设课程
 */

//获取开设课程
api.getCollegeOpen = function (cnt,callback) {
    util.call(baseUrl+'/sch/getCollegeOpen', cnt, callback)
}

//修改开设课程
api.editCollegeOpen = function (cnt,callback) {
    util.call(baseUrl+'/sch/editCollegeOpen', cnt, callback)
}

//新增开设课程
api.createCollegeOpen  = function (cnt,callback) {
    util.call(baseUrl+'/sch/createCollegeOpen', cnt, callback)
}

//删除开设课程
api.delCollegeOpen = function (cnt,callback) {
    util.call(baseUrl+'/sch/delCollegeOpen', cnt, callback)
}

export default api
