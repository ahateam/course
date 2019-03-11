package zyxhj.course.controller;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.druid.pool.DruidPooledConnection;

import zyxhj.course.domain.attach.Clazz;
import zyxhj.course.service.ClazzService;
import zyxhj.utils.Singleton;
import zyxhj.utils.api.APIResponse;
import zyxhj.utils.api.Controller;
import zyxhj.utils.data.DataSource;
import zyxhj.utils.data.DataSourceUtils;

public class ClazzController extends Controller {

	private static Logger log = LoggerFactory.getLogger(ClazzController.class);

	private DataSource dsRds;
	private ClazzService clazzService;
	
	public ClazzController(String node) {
		super(node);

		try {
			dsRds = DataSourceUtils.getDataSource("rdsDefault");

			// tempService = Singleton.ins(TempService.class);
			clazzService = Singleton.ins(ClazzService.class);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	
	
	@POSTAPI(//
			path = "addClazz" ,//
			des = "添加班级" ,//
			ret = "返回添加信息"
			)
	public APIResponse addClazz(//
			@P(t = "班级编号") Long clazzId,//
			@P(t = "班级名称") String clazzName,//
			@P(t = "年级") String grade,//
			@P(t = "系部id" ,r = false) Long majorId,//
			@P(t = "教师id" ,r = false) Long teacherId,//
			@P(t = "部门id" ,r = false) Long departId,//
			@P(t = "学院id" ,r = false) Long instiutionId,//
			@P(t = "院校id" ,r = false) Long collageId
			) throws Exception {
		try (DruidPooledConnection conn = (DruidPooledConnection) dsRds.openConnection()) {
			// User user = ServiceUtils.userAuth(conn, userId);// user鉴权
			Clazz c = clazzService.addClazz(conn, clazzId, clazzName,grade, majorId, teacherId,departId,instiutionId,collageId);
			return APIResponse.getNewSuccessResp(c);
		}
	}

	@POSTAPI(//
			path = "deleteClazz" ,//
			des = "删除班级" ,//
			ret = ""
			)
	public APIResponse deleteClazz(//
			@P(t = "班级编号") Long clazzId
			) throws Exception {
		try (DruidPooledConnection conn = (DruidPooledConnection) dsRds.openConnection()) {
			// User user = ServiceUtils.userAuth(conn, userId);// user鉴权
			clazzService.deleteClazz(conn, clazzId);
			return APIResponse.getNewSuccessResp();
		}
	}
	
	
	@POSTAPI(//
			path = "upClazz" ,//
			des = "修改班级信息" ,//
			ret = "返回修改信息"
			)
	public APIResponse upClazz(//
			@P(t = "班级编号") Long clazzId,//
			@P(t = "班级名称") String clazzName,//
			@P(t = "年级") String grade,//
			@P(t = "系部id" ,r = false) Long majorId,//
			@P(t = "教师id" ,r = false) Long teacherId ,//
			@P(t = "部门id" ,r = false) Long departId,//
			@P(t = "学院id" ,r = false) Long instiutionId,//
			@P(t = "院校id" ,r = false) Long collageId
			) throws Exception {
		try (DruidPooledConnection conn = (DruidPooledConnection) dsRds.openConnection()) {
			// User user = ServiceUtils.userAuth(conn, userId);// user鉴权
			Clazz c = clazzService.upCLazz(conn, clazzId, clazzName,grade, majorId, teacherId,departId,instiutionId,collageId);
			return APIResponse.getNewSuccessResp(c);
		}
	}
	
	@POSTAPI(//
			path = "queryClazzById" ,//
			des = "根据教室id查询班级信息" ,//
			ret = "返回查询的班级信息"
			)
	public APIResponse queryClazzById(//
			@P(t = "班级编号") Long clazzId
			) throws Exception {
		try (DruidPooledConnection conn = (DruidPooledConnection) dsRds.openConnection()) {
			// User user = ServiceUtils.userAuth(conn, userId);// user鉴权
			Clazz c = clazzService.queryClazzById(conn, clazzId);
			return APIResponse.getNewSuccessResp(c);
		}
	}
	
	
	@POSTAPI(//
			path = "queryCLazzBygrade" ,//
			des = "根据年级班级信息" ,//
			ret = "返回查询的班级信息"
			)
	public APIResponse queryCLazzBygrade(//
			@P(t = "年级") String grade,//
			@P(t = "学院id" , r = false) Long institutionId,//
			@P(t = "部门id" , r = false) Long deptId,//
			@P(t = "专业id" , r = false) Long majorId ,//
			Integer count,//
			Integer offset
			) throws Exception {
		try (DruidPooledConnection conn = (DruidPooledConnection) dsRds.openConnection()) {
			// User user = ServiceUtils.userAuth(conn, userId);// user鉴权
			List<Clazz> c = clazzService.queryCLazzBygrade(conn, institutionId, deptId, majorId, grade,count,offset);
			return APIResponse.getNewSuccessResp(c);
		}
	}
	
}
