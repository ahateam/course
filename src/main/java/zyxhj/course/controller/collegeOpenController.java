package zyxhj.course.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;

import zyxhj.course.service.collegeOpenService;
import zyxhj.utils.Singleton;
import zyxhj.utils.api.APIResponse;
import zyxhj.utils.api.Controller;
import zyxhj.utils.data.DataSource;

public class collegeOpenController extends Controller{
	private static Logger log = LoggerFactory.getLogger(collegeOpenController.class);
	private DruidDataSource dds;
	private collegeOpenService collegeOpenService;
	
	public collegeOpenController(String node) {
		super(node);

		try {
			dds = DataSource.getDruidDataSource("rdsDefault.prop");
			collegeOpenService = Singleton.ins(collegeOpenService .class);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}
	
	@POSTAPI(//
			path = "createCollegeOpen", 
			des = "根据学院的名称来创建学院",
			ret = "所创建的学院的信息")
	public APIResponse createCollegeOpen(
			String courseName,//学院名称
			Long courseCode,//
			String collegeName,
			String collegeOpenExamStatus
			) throws Exception{
		try(DruidPooledConnection conn = dds.getConnection()){
			collegeOpenService.createCollegeOpen(conn, courseName, courseCode, collegeName, collegeOpenExamStatus);
			return APIResponse.getNewSuccessResp();
		}
	}
	
	@POSTAPI(//
			path = "getCollegeOpen", 
			des = "获取开设课程分页信息",
			ret = "课程的列表信息")
	public APIResponse getCollegeOpen(
			Long collegeId,
			@P(t = "每页显示记录数") Integer count, //
			@P(t = "从第几条记录开始") Integer offset //
			) throws Exception{
		try(DruidPooledConnection conn = dds.getConnection()){
			
			return APIResponse.getNewSuccessResp(collegeOpenService.getcollegeOpen(conn,collegeId , count, offset));
		}
	}
	
	@POSTAPI(//
			path = "delCollegeOpen", 
			des = "根据课程的Id来删除学院",
			ret = "删除后的的课程的信息")
	public APIResponse delCollegeOpen(
			@P(t = "课程代码")Long courseCode//
			) throws Exception{
		try(DruidPooledConnection conn = dds.getConnection()){
			collegeOpenService.delCourseOpen(conn, courseCode);
			return APIResponse.getNewSuccessResp();
		}
	}
	
	@POSTAPI(//
			path = "qureyCollegeOpen", 
			des = "根据课程的Id来查询课程代码",
			ret = "查询后的课程的信息")
	public APIResponse qureyCollegeOpen(
			@P(t = "课程代码")Long courseCode//
			) throws Exception{
		try(DruidPooledConnection conn = dds.getConnection()){
			collegeOpenService.gCollegeOpen(conn, courseCode);
			return APIResponse.getNewSuccessResp();
		}
	}
}
