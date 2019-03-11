package zyxhj.course.controller;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.druid.pool.DruidPooledConnection;

import zyxhj.course.domain.attach.Course;
import zyxhj.course.service.CourseService;
import zyxhj.utils.Singleton;
import zyxhj.utils.api.APIResponse;
import zyxhj.utils.api.Controller;
import zyxhj.utils.data.DataSource;
import zyxhj.utils.data.DataSourceUtils;

public class CourseController extends Controller {

	private static Logger log = LoggerFactory.getLogger(CourseController.class);

	private DataSource dsRds;
	private CourseService courseService;
	public CourseController(String node) {
		super(node);

		try {
			dsRds = DataSourceUtils.getDataSource("rdsDefault");

			// tempService = Singleton.ins(TempService.class);
			courseService = Singleton.ins(CourseService.class);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}


	
	@POSTAPI(//
			path = "addCourse" ,//
			des = "添加课程" ,//
			ret = "返回添加课程信息"
			)
	public APIResponse addCourse(//
			@P(t = "课程编号") Long courseId,//
			@P(t = "课程名称") String courseName,//
			@P(t = "学识") Long knowledge,//
			@P(t = "所属专业id" , r = false) Long majorId, //
			@P(t = "教师id" ,r = false) Long teacherId ,//
			@P(t = "部门id" ,r = false) Long depatId ,//
			@P(t = "学院id" ,r = false) Long institutionId ,//
			@P(t = "院校id" ,r = false) Long collageId //
			
			) throws Exception {
		try (DruidPooledConnection conn = (DruidPooledConnection) dsRds.openConnection()) {
			// User user = ServiceUtils.userAuth(conn, userId);// user鉴权

			Course c = courseService.addCourse(conn, courseId, courseName,knowledge,majorId,teacherId,depatId,institutionId,collageId);
			return APIResponse.getNewSuccessResp(c);
		}
	}
	
	
	@POSTAPI(//
			path = "deleteCourse" ,//
			des = "删除课程" ,//
			ret = ""
			)
	public APIResponse deleteCourse(//
			@P(t = "课程编号") Long courseId
			) throws Exception {
		try (DruidPooledConnection conn = (DruidPooledConnection) dsRds.openConnection()) {
			// User user = ServiceUtils.userAuth(conn, userId);// user鉴权

			courseService.deleteCourse(conn, courseId);
			return APIResponse.getNewSuccessResp();
		}
	}
	
	@POSTAPI(//
			path = "upCourse" ,//
			des = "修改课程" ,//
			ret = "返回修改信息"
			)
	public APIResponse upCourse(//
			@P(t = "课程编号") Long courseId,//
			@P(t = "课程名称") String courseName,//
			@P(t = "学识") Long knowledge,//
			@P(t = "所属专业id" , r = false) Long majorId, //
			@P(t = "教师id" ,r = false) Long teacherId ,//
			@P(t = "部门id" ,r = false) Long depatId ,//
			@P(t = "学院id" ,r = false) Long institutionId ,//
			@P(t = "院校id" ,r = false) Long collageId //
			) throws Exception {
		try (DruidPooledConnection conn = (DruidPooledConnection) dsRds.openConnection()) {
			// User user = ServiceUtils.userAuth(conn, userId);// user鉴权

			Course c = courseService.upCourse(conn, courseId,courseName,knowledge,majorId,teacherId,depatId,institutionId,collageId);
			return APIResponse.getNewSuccessResp(c);
		}
	}
	
	@POSTAPI(//
			path = "queryCourseById" ,//
			des = "根据课程id查询课程" ,//
			ret = "返回查询记录"
			)
	public APIResponse queryCourseById(//
			@P(t = "课程编号") Long courseId
			) throws Exception {
		try (DruidPooledConnection conn = (DruidPooledConnection) dsRds.openConnection()) {
			// User user = ServiceUtils.userAuth(conn, userId);// user鉴权

			Course c = courseService.queryCourseById(conn, courseId);
			return APIResponse.getNewSuccessResp(c);
		}
	}
	
	
	@POSTAPI(//
			path = "getCourse" ,//
			des = "获取课程列表" ,//
			ret = "返回课程列表"
			)
	public APIResponse getCourse(//
			) throws Exception {
		try (DruidPooledConnection conn = (DruidPooledConnection) dsRds.openConnection()) {
			// User user = ServiceUtils.userAuth(conn, userId);// user鉴权

			List<Course> c = courseService.getCourse(conn);
			return APIResponse.getNewSuccessResp(c);
		}
	}
	
}
