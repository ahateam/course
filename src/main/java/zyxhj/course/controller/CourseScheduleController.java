package zyxhj.course.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.druid.pool.DruidPooledConnection;

import zyxhj.course.domain.CourseSchedule;
import zyxhj.course.domain.attach.Classroom;
import zyxhj.course.service.CourseScheduleService;
import zyxhj.utils.Singleton;
import zyxhj.utils.api.APIResponse;
import zyxhj.utils.api.Controller;
import zyxhj.utils.data.DataSource;
import zyxhj.utils.data.DataSourceUtils;

public class CourseScheduleController extends Controller {

	private static Logger log = LoggerFactory.getLogger(CourseScheduleController.class);

	private DataSource dsRds;
	// private TempService tempService;
	private  CourseScheduleService courseScheduleService;

	public CourseScheduleController(String node) {
		super(node);

		try {
			dsRds = DataSourceUtils.getDataSource("rdsDefault");

			// tempService = Singleton.ins(TempService.class);
			courseScheduleService = Singleton.ins(CourseScheduleService.class);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	@POSTAPI(//
			path = "addCourseSchedule", //
			des = "创建课程安排", //
			ret = "返回创建对象" //
	)
	public APIResponse addCourseSchedule(//
			@P(t = "课程安排编号") Long CSId, //
			@P(t = "学期编号") Long termId, //
			@P(t = "课程编号") Long courseId, //
			@P(t = "教室id") Long classroomId, //
			@P(t = "班级信息（可能多个，合班课）") String classesIds, //
			@P(t = "教师信息（可能多个）") String teacherIds, //
			@P(t = "开始周") Integer weekStart, //
			@P(t = "结束周") Integer weekEnd, //
			@P(t = "周几") Integer numDay, //
			@P(t = "起始时间（半小时为最小单位，一天分为48个单位时间）") Integer timeStart, //
			@P(t = "结束时间（半小时为最小单位，一天分为48个单位时间）") Integer timeEnd) throws Exception {
		try (DruidPooledConnection conn = (DruidPooledConnection) dsRds.openConnection()) {
			// User user = ServiceUtils.userAuth(conn, userId);// user鉴权

			CourseSchedule courseSchedule = courseScheduleService.addCourseSchedule(conn, CSId, termId, courseId,
					classroomId, classesIds, teacherIds, weekStart, weekEnd, numDay, timeStart, timeEnd);
			return APIResponse.getNewSuccessResp(courseSchedule);
		}
	}

	@POSTAPI(//
			path = "deleteCourseSchedule", //
			des = "删除课程安排", //
			ret = "" //
	)
	public APIResponse deleteCourseSchedule(//
			@P(t = "课程安排编号") Long CSId) throws Exception {
		try (DruidPooledConnection conn = (DruidPooledConnection) dsRds.openConnection()) {
			// User user = ServiceUtils.userAuth(conn, userId);// user鉴权

			courseScheduleService.deleteCourseSchedule(conn, CSId);
			return APIResponse.getNewSuccessResp(CSId);
		}
	}

	@POSTAPI(//
			path = "upCourseSchedule",//
			des = "修改课程安排",//
			ret = "返回查询结果集")
	public APIResponse upCourseSchedule(@P(t = "课程安排编号") Long CSId,//
			@P(t = "学期编号") Long termId,
			@P(t = "课程编号", r = false) Long courseId,//
			@P(t = "教室信息", r = false) Long classroomId,
			@P(t = "班级信息（可能多个，合班课）", r = false) String classesIds,//
			@P(t = "教师信息（可能多个）", r = false) String teacherIds,
			@P(t = "开始周", r = false) Integer weekStart, //
			@P(t = "结束周", r = false) Integer weekEnd,
			@P(t = "周几", r = false) Integer numDay, //
			@P(t = "起始时间（半小时为最小单位，一天分为48个单位时间）", r = false) Integer timeStart,//
			@P(t = "结束时间（半小时为最小单位，一天分为48个单位时间）", r = false) Integer timeEnd) throws Exception {
		try (DruidPooledConnection conn = (DruidPooledConnection) dsRds.openConnection()) {
			courseScheduleService.upCourseSchedule(conn, CSId, termId, courseId, classroomId, classesIds, teacherIds,
					weekStart, weekEnd, numDay, timeStart, timeEnd);
			return APIResponse.getNewSuccessResp();
		}
	}




	@POSTAPI(path = "queryCourse",//
			des = "查询课程冲突",//
			ret = "返回是否冲突")
	public APIResponse queryCourse(@P(t = "教室信息") Long classroomId,//
			@P(t = "开始周") Integer weekStart,
			@P(t = "结束周") Integer weekEnd,//
			@P(t = "周次") Integer numDay,//
			@P(t = "起始时间") Integer timeStart,//
			@P(t = "结束时间") Integer timeEnd) throws Exception {
		try (DruidPooledConnection conn = (DruidPooledConnection) dsRds.openConnection()) {
			int i = courseScheduleService.queryCourseSchedule(conn, classroomId, weekStart, weekEnd, numDay, timeStart, timeEnd);
			return APIResponse.getNewSuccessResp(i);
		}
	}
	

	@POSTAPI(//
			path = "queryCourseScheduleById", //
			des = "根据课程安排id查询课程安排", //
			ret = "返回查询结果集")
	public APIResponse queryCourseScheduleById(//
			@P(t = "课程安排id") Long CSId//
			) throws Exception {
		try (DruidPooledConnection conn = (DruidPooledConnection) dsRds.openConnection()) {
			CourseSchedule courseSchedule = courseScheduleService.queryCourseScheduleById(conn, CSId);
			return APIResponse.getNewSuccessResp(courseSchedule);
		}
	}
	
	
	@POSTAPI(//
			path = "getBulidNum", //
			des = "获取楼号列表", //
			ret = "返回楼号列表")
	public APIResponse getBulidNum(//
			) throws Exception {
		try (DruidPooledConnection conn = (DruidPooledConnection) dsRds.openConnection()) {
			return APIResponse.getNewSuccessResp(courseScheduleService.getBulidNum(conn));
		}
	}

	@POSTAPI(//
			path = "getClassroomByBulid", //
			des = "根据楼号获取全部教室列表", //
			ret = "返回教室列表")
	public APIResponse getClassroom(//
			@P(t = "教室楼号") String bulidNum //
			) throws Exception {
		try (DruidPooledConnection conn = (DruidPooledConnection) dsRds.openConnection()) {
			return APIResponse.getNewSuccessResp(courseScheduleService.getClassroom(conn,bulidNum));
		}
	}


	@POSTAPI(//
			path = "getCourseSchedule", //
			des = "根据教室信息查询课程安排", //
			ret = "返回查询结果集")
	public APIResponse getCourseSchedule(//
			@P(t = "教室信息") String classroomName ,//
			@P(t = "教室楼号") String bulidNum //
			) throws Exception {
		try (DruidPooledConnection conn = (DruidPooledConnection) dsRds.openConnection()) {
			List<CourseSchedule> courseSchedule = courseScheduleService.getCourseSchedule(conn, classroomName,bulidNum);
			return APIResponse.getNewSuccessResp(courseSchedule);
		}
	}

}
