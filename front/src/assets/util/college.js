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


api.getSchedule = function (cnt,callback) {
    util.call(baseUrl+'/sch/getSchedule', cnt, callback)
}



api.add = function (cnt,callback) {
    util.call(baseUrl+'/add', cnt, callback)
}
export default api
