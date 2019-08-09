import util from 'ahaapi'
import baseUrl from './url'

let api = {};

/**
 * 学期 term
 * */

//创建学期
api.createTerm = function (cnt,callback) {
    util.call(baseUrl+'/term/createTerm', cnt, callback)
}
//学期列表
api.getTerms = function (cnt,callback) {
    util.call(baseUrl+'/term/getTerms', cnt, callback)
}
//删除学期
api.delTerm = function (cnt,callback) {
    util.call(baseUrl+'/term/delTerm', cnt, callback)
}
api.getTeacherUsers = function (cnt,callback) {
    util.call(baseUrl+'/term/delTerm', cnt, callback)
}


/**
 * 学院管理 department
 * */

//获取学院
api.getDepartments = function (cnt,callback) {
    util.call(baseUrl+'/department/getDepartment', cnt, callback)
}

//getDepartmentClass 获取班级
api.getDepartmentClass = function (cnt,callback) {
    util.call(baseUrl+'/department/getDepartmentClass', cnt, callback)
}

//getDepartmentLaboratory 获取实验室数量
api.getDepartmentLaboratory = function (cnt,callback) {
    util.call(baseUrl+'/department/getDepartmentLaboratory', cnt, callback)
}

//getDepartmentTeacher 获取教师数量
api.getDepartmentTeacher = function (cnt,callback) {
    util.call(baseUrl+'/department/getDepartmentTeacher', cnt, callback)
}

//getDepartmentMajor 获取专业
api.getDepartmentMajor = function (cnt,callback) {
    util.call(baseUrl+'/department/getDepartmentMajor', cnt, callback)
}

//新增学院
api.createDepartment = function (cnt,callback) {
    util.call(baseUrl+'/department/createDepartment', cnt, callback)
}

//编辑学院
api.editDepartment = function (cnt,callback) {
    util.call(baseUrl+'/department/editDepartment', cnt, callback)
}

//删除学院
api.delDepartment = function (cnt,callback) {
    util.call(baseUrl+'/department/delDepartment', cnt, callback)
}


/**
 * 教师管理 schoolTeacher
 * */
//获取教师 schoolTeacher
// api.getSchoolTeacher = function (cnt,callback) {
//     util.call(baseUrl+'/schoolTeacher/getTeacher', cnt, callback)
// }
api.getSchoolTeacher = function (cnt,callback) {
    util.call(baseUrl+'/userController/getSchoolTeacher', cnt, callback)
}

//编辑教师
api.editSchoolTeacher = function (cnt,callback) {
    util.call(baseUrl+'/userController/editCollegeTeacher', cnt, callback)
}

// //添加教师
// api.addSchoolTeacher = function (cnt,callback) {
//     util.call(baseUrl+'/course/delTeacher', cnt, callback)
// }
//
//删除教师
api.delSchoolTeacher = function (cnt,callback) {
    util.call(baseUrl+'/course/delTeacher', cnt, callback)
}


/**
 * 实验室管理 schoolLabor
 * */

//获取实验室
api.getSchoolLabor = function (cnt,callback) {
    util.call(baseUrl+'/classRoom/getSchoolLabor', cnt, callback)
}

//新增实验室大楼
api.createLaborBuild = function (cnt,callback) {
    util.call(baseUrl+'/classRoom/createLaborBuild', cnt, callback)
}
//获取实验室大楼
api.getLaborBuild = function (cnt,callback) {
    util.call(baseUrl+'/classRoom/getLaborBuild', cnt, callback)
}
//获取楼层
api.getFloorNum = function (cnt,callback) {
    util.call(baseUrl+'/classRoom/getFloorNum', cnt, callback)
}

//修改实验室
api.editSchoolLabor = function (cnt,callback) {
    util.call(baseUrl+'/classRoom/editSchoolLabor', cnt, callback)
}

//条件查询实验室
api.lookupLabor = function (cnt,callback) {
    util.call(baseUrl+'/classRoom/lookupLabor', cnt, callback)
}

/**
 * 专业管理 major
 * */
api.lookupSchoolMajor = function (cnt,callback) {
    util.call(baseUrl+'/major/lookupMajor', cnt, callback)
}

api.getSchoolMajor = function (cnt,callback) {
    util.call(baseUrl+'/major/getMajor', cnt, callback)
}

api.editSchoolMajor = function (cnt,callback) {
    util.call(baseUrl+'/major/editMajor', cnt, callback)
}

api.createSchoolMajor = function (cnt,callback) {
    util.call(baseUrl+'/major/createMajor', cnt, callback)
}

api.delSchoolMajor = function (cnt,callback) {
    util.call(baseUrl+'/major/delMajor', cnt, callback)
}


export default api
