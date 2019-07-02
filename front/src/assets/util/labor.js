import util from 'ahaapi'
import baseUrl from './url'

let api = {};


/**
 * collegeLabor 教室管理
 */

//getCollegeLabor 获取实验室
api.getCollegeLabor = function (cnt,callback) {
    util.call(baseUrl+'/collegeLabor/getCollegeLabor', cnt, callback)
}

export default api
