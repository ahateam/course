package zyxhj.course.controller;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.druid.pool.DruidPooledConnection;

import zyxhj.course.domain.attach.Course;
import zyxhj.course.domain.attach.Major;
import zyxhj.course.service.MajorService;
import zyxhj.utils.Singleton;
import zyxhj.utils.api.APIResponse;
import zyxhj.utils.api.Controller;
import zyxhj.utils.data.DataSource;
import zyxhj.utils.data.DataSourceUtils;

public class MajorController extends Controller {

	private static Logger log = LoggerFactory.getLogger(MajorController.class);

	private DataSource dsRds;
	private MajorService majorService;
	
	public MajorController(String node) {
		super(node);

		try {
			dsRds = DataSourceUtils.getDataSource("rdsDefault");
			majorService = Singleton.ins(MajorService.class);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}
	
	@POSTAPI(//
			path = "addMajor" ,//
			des = "添加专业" ,//
			ret = "返回添加专业信息"
			)
	public APIResponse addClazz(//
			@P(t = "专业编号") Long majorId,//
			@P(t = "专业名称") String majorName,//
			@P(t = "系部id" ,r = false) Long departId,//
			@P(t = "院系id" ,r = false) Long institutionId
			) throws Exception {
		try (DruidPooledConnection conn = (DruidPooledConnection) dsRds.openConnection()) {
			// User user = ServiceUtils.userAuth(conn, userId);// user鉴权
			Major m = majorService.addMajor(conn, majorId, majorName, departId);
			return APIResponse.getNewSuccessResp(m);
		}
	}
	
	@POSTAPI(//
			path = "upMajor" ,//
			des = "修改专业" ,//
			ret = "返回修改信息"
			)
	public APIResponse upMajor(//
			@P(t = "专业编号") Long majorId,//
			@P(t = "专业名称") String majorName,//
			@P(t = "系部id" ,r = false) Long departId,//
			@P(t = "院系id" ,r = false) Long institutionId
			) throws Exception {
		try (DruidPooledConnection conn = (DruidPooledConnection) dsRds.openConnection()) {
			// User user = ServiceUtils.userAuth(conn, userId);// user鉴权
			Major m = majorService.upMajor(conn, majorId, majorName, departId);
			return APIResponse.getNewSuccessResp(m);
		}
	}
	
	@POSTAPI(//
			path = "deleteMajor" ,//
			des = "删除专业" ,//
			ret = ""
			)
	public APIResponse deleteMajor(//
			@P(t = "专业编号") Long majorId
			) throws Exception {
		try (DruidPooledConnection conn = (DruidPooledConnection) dsRds.openConnection()) {
			// User user = ServiceUtils.userAuth(conn, userId);// user鉴权
			majorService.deleteMajor(conn, majorId);
			return APIResponse.getNewSuccessResp();
		}
	}

	
	@POSTAPI(//
			path = "queryById" ,//
			des = "查询专业" ,//
			ret = "返回查询专业信息"
			)
	public APIResponse queryById(//
			@P(t = "专业编号") Long majorId
			) throws Exception {
		try (DruidPooledConnection conn = (DruidPooledConnection) dsRds.openConnection()) {
			// User user = ServiceUtils.userAuth(conn, userId);// user鉴权
			Major m = majorService.queryById(conn, majorId);
			return APIResponse.getNewSuccessResp(m);
		}
	}
	
	
	@POSTAPI(//
			path = "queryCourseByMajorId" ,//
			des = "查询当前专业下所有课程" ,//
			ret = "返回查询信息"
			)
	public APIResponse queryCourseByMajorId(//
			@P(t = "专业编号") Long majorId
			) throws Exception {
		try (DruidPooledConnection conn = (DruidPooledConnection) dsRds.openConnection()) {
			// User user = ServiceUtils.userAuth(conn, userId);// user鉴权
			List<Course> c = majorService.queryCourseByMajorId(conn, majorId);
			return APIResponse.getNewSuccessResp(c);
		}
	}
	
	@POSTAPI(//
			path = "countClazz" ,//
			des = "统计当前专业下的班级" ,//
			ret = "返回班级数量"
			)
	public APIResponse countClazz(//
			@P(t = "专业编号") Long majorId
			) throws Exception {
		try (DruidPooledConnection conn = (DruidPooledConnection) dsRds.openConnection()) {
			// User user = ServiceUtils.userAuth(conn, userId);// user鉴权
			Integer c = majorService.countClazz(conn, majorId);
			return APIResponse.getNewSuccessResp(c);
		}
	}
}
