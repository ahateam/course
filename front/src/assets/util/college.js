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
export default api
