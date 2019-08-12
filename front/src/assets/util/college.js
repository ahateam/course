import util from 'ahaapi'
import baseUrl from './url'

let api = {};
//console.log(baseUrl)
//console.log('开始调用ctrl');

/**
 *档期管理
 */

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
 * collegeOpen 开设课程
 */

        //获取开设课程
        api.getCollegeOpen = function (cnt,callback) {
            util.call(baseUrl+'/collegeOpen/getCollegeOpen', cnt, callback)
        }

        //修改开设课程
        api.editCollegeOpen = function (cnt,callback) {
            util.call(baseUrl+'/collegeOpen/editCollegeOpen', cnt, callback)
        }

        //新增开设课程
        api.createCollegeOpen  = function (cnt,callback) {
            util.call(baseUrl+'/collegeOpen/createCollegeOpen', cnt, callback)
        }

        //删除开设课程
        api.delCollegeOpen = function (cnt,callback) {
            util.call(baseUrl+'/collegeOpen/delCollegeOpen', cnt, callback)
        }

/**
 *课程大纲
 */
        //获取课程大纲
        api.getCourseOutlineByTermId = function (cnt,callback) {
            util.call(baseUrl+'/courseOutline/getCourseOutline', cnt, callback)
        }

        //新增大纲
        api.createCourseOutline = function (cnt,callback) {
            util.call(baseUrl+'/courseOutline/createCourseOutline', cnt, callback)
        }

        //删除大纲
        api.delCourseOutline = function (cnt,callback) {
            util.call(baseUrl+'/courseOutline/delCourseOutline', cnt, callback)
        }

        //修改大纲

        api.editCourseOutline = function (cnt,callback) {
            util.call(baseUrl+'/courseOutline/editCourseOutline', cnt, callback)
        }

/**
 * collegeTeacher 院内教师
 */
        //获取院内教师
        api.getCollegeTeacher = function (cnt,callback) {
            util.call(baseUrl+'/collegeTeacher/getCollegeTeacher', cnt, callback)
        };

        api.createCollegeTeacher = function (cnt,callback) {
            util.call(baseUrl+'/collegeTeacher/createCollegeTeacher', cnt, callback)
        };


        //删除教师
        api.delCollegeTeacher = function (cnt,callback) {
            util.call(baseUrl+'/userController/delCollegeTeacher', cnt, callback)
        };

        //编辑教师
        api.editCollegeTeacher = function (cnt,callback) {
            util.call(baseUrl+'/collegeTeacher/editCollegeTeacher', cnt, callback)
        };

        //工号查询教师
        api.lookupCollegeTeacher = function (cnt,callback) {
            util.call(baseUrl+'/userController/lookupCollegeTeacher', cnt, callback)
        };

/**
* collegeOpen 开设课程
 */

        //获取开设课程
        api.getCollegeOpen = function (cnt,callback) {
            util.call(baseUrl+'/collegeOpen/getCollegeOpen', cnt, callback)
        }

        //修改开设课程
        api.editCollegeOpen = function (cnt,callback) {
            util.call(baseUrl+'/collegeOpen/editCollegeOpen', cnt, callback)
        }

        //新增开设课程
        api.createCollegeOpen  = function (cnt,callback) {
            util.call(baseUrl+'/collegeOpen/createCollegeOpen', cnt, callback)
        }

        //删除开设课程
        api.delCollegeOpen = function (cnt,callback) {
            util.call(baseUrl+'/collegeOpen/delCollegeOpen', cnt, callback)
        }


/**
*  collegeLabor 实验室管理
*/
        //获取实验室
        api.getCollegeLabor = function (cnt,callback) {
            util.call(baseUrl+'/collegeLabor/getCollegeLabor', cnt, callback)
        }

        //修改实验室
        api.editCollegeLabor = function (cnt,callback) {
            util.call(baseUrl+'/classRoom/editCollegeLabor', cnt, callback)
        }


/**getCollegeClass
* collegeClass  班级管理
* */
        api.getCollegeClass = function (cnt,callback) {
            util.call(baseUrl+'/collegeClass/getCollegeClass', cnt, callback)
        }

        api.editCollegeClass = function (cnt,callback) {
            util.call(baseUrl+'/collegeClass/editCollegeClass', cnt, callback)
        }

        api.createCollegeClass = function (cnt,callback) {
            util.call(baseUrl+'/collegeClass/createCollegeClass', cnt, callback)
        }

        api.delCollegeClass = function (cnt,callback) {
            util.call(baseUrl+'/collegeClass/delCollegeClass', cnt, callback)
        }

export default api
