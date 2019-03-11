package zyxhj.course.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.druid.pool.DruidPooledConnection;

import zyxhj.course.domain.CourseSchedule;
import zyxhj.course.domain.attach.Classroom;
import zyxhj.course.service.ClassRoomService;
import zyxhj.utils.Singleton;
import zyxhj.utils.api.APIResponse;
import zyxhj.utils.api.Controller;
import zyxhj.utils.data.DataSource;
import zyxhj.utils.data.DataSourceUtils;

public class ClassroomController extends Controller {

	private static Logger log = LoggerFactory.getLogger(ClassroomController.class);

	private DataSource dsRds;
	private ClassRoomService classRoomService;

	public ClassroomController(String node) {
		super(node);

		try {
			dsRds = DataSourceUtils.getDataSource("rdsDefault");

			classRoomService = Singleton.ins(ClassRoomService.class);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}
	
	@POSTAPI(//
			path = "addbuild", //
			des = "添加楼号", //
			ret = "返回添加教室信息")
	public APIResponse addbuild(//
			@P(t = "教室编号") Long classroomId, //
			@P(t = "楼号") String buildNum //
	) throws Exception {
		try (DruidPooledConnection conn = (DruidPooledConnection) dsRds.openConnection()) {
			// User user = ServiceUtils.userAuth(conn, userId);// user鉴权
			Classroom c = classRoomService.addbuild(conn, classroomId,buildNum);
			return APIResponse.getNewSuccessResp(c);
		}
	}
	

	@POSTAPI(//
			path = "addClassroom", //
			des = "添加教室", //
			ret = "返回添加教室信息")
	public APIResponse addClassroom(//
			@P(t = "教室编号") Long classroomId, //
			@P(t = "教室名称") String classroomName,//
			@P(t = "楼号") String buildNum //
	) throws Exception {
		try (DruidPooledConnection conn = (DruidPooledConnection) dsRds.openConnection()) {
			// User user = ServiceUtils.userAuth(conn, userId);// user鉴权
			Classroom c = classRoomService.addClassroom(conn, classroomId, classroomName,buildNum);
			return APIResponse.getNewSuccessResp(c);
		}
	}

	@POSTAPI(//
			path = "deleteClassroom", //
			des = "删除教室", //
			ret = "")
	public APIResponse deleteClassroom(//
			@P(t = "班级编号") Long classroomId) throws Exception {
		try (DruidPooledConnection conn = (DruidPooledConnection) dsRds.openConnection()) {
			// User user = ServiceUtils.userAuth(conn, userId);// user鉴权
			classRoomService.deleteClassroom(conn, classroomId);
			return APIResponse.getNewSuccessResp();
		}
	}

	@POSTAPI(//
			path = "upClassroom", //
			des = "修改教室", //
			ret = "返回修改教室信息")
	public APIResponse upClassroom(//
			@P(t = "班级编号") Long classroomId, //
			@P(t = "班级名称") String classroomName,//
			@P(t = "教室名称") String buildNum //
			) throws Exception {
		try (DruidPooledConnection conn = (DruidPooledConnection) dsRds.openConnection()) {
			// User user = ServiceUtils.userAuth(conn, userId);// user鉴权
			Classroom c = classRoomService.upClassroom(conn, classroomId, classroomName,buildNum);
			return APIResponse.getNewSuccessResp(c);
		}
	}

	@POSTAPI(//
			path = "queryCourseScheduleById", //
			des = "查询当前教室教室课程安排", //
			ret = "返回当前教室教室课程安排")
	public APIResponse queryCourseScheduleById(//
			@P(t = "教室id") Long classroomId) throws Exception {
		try (DruidPooledConnection conn = (DruidPooledConnection) dsRds.openConnection()) {
			// User user = ServiceUtils.userAuth(conn, userId);// user鉴权
			List<CourseSchedule> c = classRoomService.queryCourseScheduleById(conn, classroomId);
			return APIResponse.getNewSuccessResp(c);
		}
	}

	@POSTAPI(//
			path = "queryClassroomById", //
			des = "根据id查询教室信息", //
			ret = "返回查询信息")
	public APIResponse queryClassroomById(//
			@P(t = "教室id")  Long classroomId) throws Exception {
		try (DruidPooledConnection conn = (DruidPooledConnection) dsRds.openConnection()) {
			// User user = ServiceUtils.userAuth(conn, userId);// user鉴权
			Classroom c = classRoomService.queryClassroomById(conn, classroomId);
			return APIResponse.getNewSuccessResp(c);
		}
	}

}
