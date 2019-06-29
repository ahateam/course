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
api.getTeacherUsers = function (cnt,callback) {
    util.call(baseUrl+'/course/delTerm', cnt, callback)
}


//获取学院
api.getDepartments = function (cnt,callback) {
    util.call(baseUrl+'/course/getDepartments', cnt, callback)
}

//getDepartmentClass 获取班级
api.getDepartmentClass = function (cnt,callback) {
    util.call(baseUrl+'/course/getDepartmentClass', cnt, callback)
}

//getDepartmentLaboratory 获取实验室
api.getDepartmentLaboratory = function (cnt,callback) {
    util.call(baseUrl+'/course/getDepartmentLaboratory', cnt, callback)
}

//getDepartmentTeacher 获取教师
api.getDepartmentTeacher = function (cnt,callback) {
    util.call(baseUrl+'/course/getDepartmentTeacher', cnt, callback)
}

//getDepartmentMajor 获取专业
api.getDepartmentMajor = function (cnt,callback) {
    util.call(baseUrl+'/course/getDepartmentMajor', cnt, callback)
}

//新增学院
api.createDepartment = function (cnt,callback) {
    util.call(baseUrl+'/course/createDepartment', cnt, callback)
}

//编辑学院
api.editDepartment = function (cnt,callback) {
    util.call(baseUrl+'/course/editDepartment', cnt, callback)
}

//删除学院
api.delDepartment = function (cnt,callback) {
    util.call(baseUrl+'/course/delDepartment', cnt, callback)
}

//获取教师
api.getSchoolTeacher = function (cnt,callback) {
    util.call(baseUrl+'/course/getTeacher', cnt, callback)
}

//编辑教师
api.editSchoolTeacher = function (cnt,callback) {
    util.call(baseUrl+'/course/editTeacher', cnt, callback)
}

//添加教师
api.addSchoolTeacher = function (cnt,callback) {
    util.call(baseUrl+'/course/delTeacher', cnt, callback)
}

//删除教师
api.delSchoolTeacher = function (cnt,callback) {
    util.call(baseUrl+'/course/delTeacher', cnt, callback)
}


export default api
