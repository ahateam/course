package zyxhj.course.controller;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.druid.pool.DruidPooledConnection;

import zyxhj.course.domain.attach.Clazz;
import zyxhj.course.domain.attach.Course;
import zyxhj.course.domain.attach.Teacher;
import zyxhj.course.service.TeacherService;
import zyxhj.utils.Singleton;
import zyxhj.utils.api.APIResponse;
import zyxhj.utils.api.Controller;
import zyxhj.utils.data.DataSource;
import zyxhj.utils.data.DataSourceUtils;

public class TeacherController extends Controller {

	private static Logger log = LoggerFactory.getLogger(TeacherController.class);

	private DataSource dsRds;
	private TeacherService teacherService;
	
	public TeacherController(String node) {
		super(node);

		try {
			dsRds = DataSourceUtils.getDataSource("rdsDefault");

			teacherService = Singleton.ins(TeacherService.class);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	
	@POSTAPI(//
			path = "addTeacher" ,//
			des = "添加教师" ,//
			ret = "返回添加教师信息"
			)
	public APIResponse addTeacher(//
			@P(t = "教师编号") Long teacherId,//
			@P(t = "教师名称") String teacherName,//
			@P(t = "教师电话" ,r = false) String teacherPhone,//
			@P(t = "教师邮箱" ,r = false) String teacherEmail,//
			@P(t = "系部id" ,r = false)  Long departId,//
			@P(t = "学院id" ,r = false)  Long institutionId,//
			@P(t = "院校id" ,r = false)  Long collageId ,//
			@P(t = "信息" ,r = false)  String information //
			) throws Exception {
		try (DruidPooledConnection conn = (DruidPooledConnection) dsRds.openConnection()) {
			// User user = ServiceUtils.userAuth(conn, userId);// user鉴权
			Teacher t = teacherService.addTeacher(conn, teacherId, teacherName, teacherPhone, departId,institutionId,collageId,information);
			return APIResponse.getNewSuccessResp(t);
		}
	}
	
	@POSTAPI(//
			path = "upTeacher" ,//
			des = "修改教师" ,//
			ret = "返回修改教师信息"
			)
	public APIResponse upTeacher(//
			@P(t = "教师编号") Long teacherId,//
			@P(t = "教师名称") String teacherName,//
			@P(t = "教师电话" ,r = false) String teacherPhone,//
			@P(t = "教师邮箱" ,r = false) String teacherEmail,//
			@P(t = "系部id" ,r = false)  Long departId,//
			@P(t = "学院id" ,r = false)  Long institutionId,//
			@P(t = "院校id" ,r = false)  Long collageId ,//
			@P(t = "信息" ,r = false)  String information //
			) throws Exception {
		try (DruidPooledConnection conn = (DruidPooledConnection) dsRds.openConnection()) {
			// User user = ServiceUtils.userAuth(conn, userId);// user鉴权
			Teacher t = teacherService.upTeacher(conn, teacherId,teacherName,teacherPhone,teacherEmail,departId,institutionId,collageId,information);
			return APIResponse.getNewSuccessResp(t);
		}
	}
	
	
	@POSTAPI(//
			path = "deleteTeacher" ,//
			des = "删除教师" ,//
			ret = ""
			)
	public APIResponse deleteTeacher(//
			@P(t = "教师编号") Long teacherId
			) throws Exception {
		try (DruidPooledConnection conn = (DruidPooledConnection) dsRds.openConnection()) {
			// User user = ServiceUtils.userAuth(conn, userId);// user鉴权
			teacherService.deleteTeacher(conn, teacherId);
			return APIResponse.getNewSuccessResp();
		}
	}

	
	@POSTAPI(//
			path = "queryTeacherById" ,//
			des = "查询教师" ,//
			ret = "返回修改教师信息"
			)
	public APIResponse queryTeacherById(//
			@P(t = "教师编号") Long teacherId
			) throws Exception {
		try (DruidPooledConnection conn = (DruidPooledConnection) dsRds.openConnection()) {
			// User user = ServiceUtils.userAuth(conn, userId);// user鉴权
			Teacher t = teacherService.queryTeacherById(conn, teacherId);
			return APIResponse.getNewSuccessResp(t);
		}
	}
	
	
	
	@POSTAPI(//
			path = "queryClazzByTeacherId" ,//
			des = "查询教师所教班级" ,//
			ret = "返回教师所教班级信息"
			)
	public APIResponse queryClazzByTeacherId(//
			@P(t = "教师编号") Long teacherId
			) throws Exception {
		try (DruidPooledConnection conn = (DruidPooledConnection) dsRds.openConnection()) {
			// User user = ServiceUtils.userAuth(conn, userId);// user鉴权
			List<Clazz> c = teacherService.queryClazzByTeacherId(conn, teacherId);
			return APIResponse.getNewSuccessResp(c);
		}
	}
	
	@POSTAPI(//
			path = "queryCourseByTeacherId" ,//
			des = "查询教师所教课程" ,//
			ret = "返回教师所教课程信息"
			)
	public APIResponse queryCourseByTeacherId(//
			@P(t = "教师编号") Long teacherId
			) throws Exception {
		try (DruidPooledConnection conn = (DruidPooledConnection) dsRds.openConnection()) {
			// User user = ServiceUtils.userAuth(conn, userId);// user鉴权
			List<Course> c = teacherService.queryCourseByTeacherId(conn, teacherId);
			return APIResponse.getNewSuccessResp(c);
		}
	}
	
	
	
	@POSTAPI(//
			path = "countKnowledge" ,//
			des = "统计课时" ,//
			ret = "返回课时数"
			)
	public APIResponse countKnowledge(//
			@P(t = "教师编号") Long teacherId
			) throws Exception {
		try (DruidPooledConnection conn = (DruidPooledConnection) dsRds.openConnection()) {
			// User user = ServiceUtils.userAuth(conn, userId);// user鉴权
			Long c = teacherService.countKnowledge(conn, teacherId);
			return APIResponse.getNewSuccessResp(c);
		}
	}
	
	@POSTAPI(//
			path = "getCourseByins" ,//
			des = "教师查询学院课程" ,//
			ret = "返回查询课程"
			)
	public APIResponse getCourseByins(//
			@P(t = "学院编号") Long institutionId
			) throws Exception {
		try (DruidPooledConnection conn = (DruidPooledConnection) dsRds.openConnection()) {
			// User user = ServiceUtils.userAuth(conn, userId);// user鉴权
			List<Course> c = teacherService.getCourseByins(conn, institutionId);
			return APIResponse.getNewSuccessResp(c);
		}
	}
	
	@POSTAPI(//
			path = "getLikeCourse" ,//
			des = "模糊查询课程" ,//
			ret = "返回查询课程"
			)
	public APIResponse getLikeCourse(//
			@P(t = "学院编号") Long institutionId ,//
			@P(t = "模糊查询内容") String idNumber ,//
			 Integer count ,//
			 Integer offset //
			) throws Exception {
		try (DruidPooledConnection conn = (DruidPooledConnection) dsRds.openConnection()) {
			// User user = ServiceUtils.userAuth(conn, userId);// user鉴权
			List<Course> c = teacherService.getLikeCourse(conn, institutionId,idNumber,count,offset);
			return APIResponse.getNewSuccessResp(c);
		}
	}
	
	
	@POSTAPI(//
			path = "getLikeTeacher" ,//
			des = "模糊查询教师" ,//
			ret = "返回查询教师"
			)
	public APIResponse getLikeTeacher(//
			@P(t = "模糊查询内容") String idNumber ,//
			 Integer count ,//
			 Integer offset //
			) throws Exception {
		try (DruidPooledConnection conn = (DruidPooledConnection) dsRds.openConnection()) {
			// User user = ServiceUtils.userAuth(conn, userId);// user鉴权
			List<Teacher> c = teacherService.getLikeTeacher(conn,idNumber,count,offset);
			return APIResponse.getNewSuccessResp(c);
		}
	}

}
