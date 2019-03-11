package zyxhj.course.controller;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.druid.pool.DruidPooledConnection;

import zyxhj.course.domain.attach.Dept;
import zyxhj.course.domain.attach.Major;
import zyxhj.course.service.DeptService;
import zyxhj.utils.Singleton;
import zyxhj.utils.api.APIResponse;
import zyxhj.utils.api.Controller;
import zyxhj.utils.data.DataSource;
import zyxhj.utils.data.DataSourceUtils;

public class DeptController extends Controller {

	private static Logger log = LoggerFactory.getLogger(DeptController.class);

	private DataSource dsRds;
	private DeptService deptService;
	public DeptController(String node) {
		super(node);

		try {
			dsRds = DataSourceUtils.getDataSource("rdsDefault");
			deptService = Singleton.ins(DeptService.class);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}
	
	@POSTAPI(//
			path = "addDept" ,//
			des = "添加系部" ,//
			ret = "返回添加信息"
			)
	public APIResponse addDept(//
			@P(t = "系部编号") Long deptId,//
			@P(t = "系部名称") String deptName,//
			@P(t = "学院id") Long institutionId,//
			@P(t = "院校id") Long collageId
			) throws Exception {
		try (DruidPooledConnection conn = (DruidPooledConnection) dsRds.openConnection()) {
			// User user = ServiceUtils.userAuth(conn, userId);// user鉴权
			 Dept d = deptService.addDept(conn,deptId,deptName,institutionId,collageId);
			return APIResponse.getNewSuccessResp(d);
		}
	}
	
	@POSTAPI(//
			path = "upDept" ,//
			des = "修改系部" ,//
			ret = "返回修改信息"
			)
	public APIResponse upDept(//
			@P(t = "系部编号") Long deptId,//
			@P(t = "系部名称") String deptName,//
			@P(t = "学院id") Long institutionId,//
			@P(t = "院校id") Long collageId
			) throws Exception {
		try (DruidPooledConnection conn = (DruidPooledConnection) dsRds.openConnection()) {
			// User user = ServiceUtils.userAuth(conn, userId);// user鉴权
			 Dept d = deptService.upDept(conn,deptId,deptName,institutionId,collageId);
			return APIResponse.getNewSuccessResp(d);
		}
	}
	
	
	@POSTAPI(//
			path = "deleteDept" ,//
			des = "删除系部" ,//
			ret = ""
			)
	public APIResponse deleteDept(//
			@P(t = "系部编号") Long deptId //
			) throws Exception {
		try (DruidPooledConnection conn = (DruidPooledConnection) dsRds.openConnection()) {
			// User user = ServiceUtils.userAuth(conn, userId);// user鉴权
			  deptService.deleteDept(conn,deptId);
			return APIResponse.getNewSuccessResp();
		}
	}
	
	@POSTAPI(//
			path = "queryMajorById" ,//
			des = "根据系部id查询当前系部下所有专业" ,//
			ret = "返回查询信息"
			)
	public APIResponse queryMajorById(//
			@P(t = "系部编号") Long deptId //
			) throws Exception {
		try (DruidPooledConnection conn = (DruidPooledConnection) dsRds.openConnection()) {
			// User user = ServiceUtils.userAuth(conn, userId);// user鉴权
			  List<Major> d = deptService.queryMajorById(conn,deptId);
			return APIResponse.getNewSuccessResp(d);
		}
	}
	
	@POSTAPI(//
			path = "queryDeptById" ,//
			des = "查询系部" ,//
			ret = "返回查询结果"
			)
	public APIResponse queryDeptById(//
			@P(t = "系部编号") Long deptId //
			) throws Exception {
		try (DruidPooledConnection conn = (DruidPooledConnection) dsRds.openConnection()) {
			// User user = ServiceUtils.userAuth(conn, userId);// user鉴权
			Dept d = deptService.queryDeptById(conn,deptId);
			return APIResponse.getNewSuccessResp(d);
		}
	}
	
}
