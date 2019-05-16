import util from 'ahaapi'
import baseUrl from './url'

let api = {};
console.log(baseUrl)
console.log('开始调用ctrl');


//账号登录
api.getGroup = function (cnt,callback) {
    util.call(baseUrl+'/demon/getGroup', cnt, callback)
}



export default api
