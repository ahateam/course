import util from "ahaapi";
import baseUrl from './url'

let api = {};


api.login = function (cnt,callback) {
    util.call(baseUrl+'/login/Login', cnt, callback)
}


export default api