package zyxhj.course.controller;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;

import zyxhj.course.service.DepartmentService;
import zyxhj.utils.Singleton;
import zyxhj.utils.api.APIResponse;
import zyxhj.utils.api.Controller;
import zyxhj.utils.data.DataSource;


public class DepartmentController extends Controller{
	
	private static Logger log = LoggerFactory.getLogger(DepartmentController.class);
	private DruidDataSource dds;
	private DepartmentService departmentService;
	
	public DepartmentController(String node) {
		super(node);

		try {
			dds = DataSource.getDruidDataSource("rdsDefault.prop");
			departmentService = Singleton.ins( DepartmentService.class);
			//  DepartmentService = Singleton.ins( DepartmentService.class);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}
	
	
	@POSTAPI(//
			path = "createDepartment", 
			des = "根据学院的名称来创建学院",
			ret = "所创建的学院的信息")
	public APIResponse createDepartment(
			String collegeName//学院名称
			) throws Exception{
		try(DruidPooledConnection conn = dds.getConnection()){
			departmentService.createDepartment(conn, collegeName);
			return APIResponse.getNewSuccessResp();
		}
	}
	
	@POSTAPI(//
			path = "delDepartment", 
			des = "根据学院的ID来删除学院")
	public APIResponse delDepartment(
			Long collegeId//学院ID
			) throws Exception{
		try(DruidPooledConnection conn = dds.getConnection()){
			departmentService.delDepartment(conn, collegeId);
			return APIResponse.getNewSuccessResp();
		}
	}
	
	
	@POSTAPI(//
			path = "editDepartment", 
			des = "根据学院的ID来修改学院的信息",
			ret = "所修改后的学院的信息")
	public APIResponse editDepartment(
			Long collegeId,//学院ID
			String collegeName//学院名称
			) throws Exception{
		try(DruidPooledConnection conn = dds.getConnection()){
			departmentService.editDepartment(conn, collegeId, collegeName);
			return APIResponse.getNewSuccessResp();
		}
	}
	
	@POSTAPI(//
			path = "getDepartment",
			des = "分页查询学院",
			ret = "返回学院信息"
	)
	public APIResponse getDerpartment(
			@P(t = "每页显示记录数") Integer count, //
			@P(t = "从第几条记录开始") Integer offset //
			) throws Exception{
		try(DruidPooledConnection conn = dds.getConnection()){
			return APIResponse.getNewSuccessResp(departmentService.getDepartment(conn, count, offset));
		}
	}
	

}
