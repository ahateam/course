import util from 'ahaapi'
import baseUrl from './url'

let api = {};



//创建学期
api.createTerm = function (cnt,callback) {
    util.call(baseUrl+'/course/createTerm', cnt, callback)
}
//学期列表
api.getTerms = function (cnt,callback) {
    util.call(baseUrl+'/course/getTerms', cnt, callback)
}
//删除学期
api.delTerm = function (cnt,callback) {
    util.call(baseUrl+'/course/delTerm', cnt, callback)
}
export default api
