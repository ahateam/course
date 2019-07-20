package zyxhj.course.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;

import zyxhj.course.service.CourseOutlineService;
import zyxhj.utils.Singleton;
import zyxhj.utils.api.APIResponse;
import zyxhj.utils.api.Controller;
import zyxhj.utils.data.DataSource;

public class CourseOutlineController extends Controller {
	private static Logger log = LoggerFactory.getLogger(CourseOutlineController.class);
	private DruidDataSource dds;
	private CourseOutlineService courseOutlineService;
	
	public CourseOutlineController(String node) {
		super(node);

		try {
			dds = DataSource.getDruidDataSource("rdsDefault.prop");
			courseOutlineService = Singleton.ins(CourseOutlineService .class);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}
	
	@POSTAPI(//
			path = "createCourseOutline", 
			des = "根据课程大纲的名称来创建课程大纲",
			ret = "所创建的课程大纲的信息")
	public APIResponse createCourseOutline(
			String name,//课程大纲的名字
			Long termId,//学期Id
			Integer classHour,//学时
			Float credit,//学分
			Byte exam,//考核方式
			Byte elect,//课程性质
			String classIds//参加课程的班级编号列表(JSONArray)
			) throws Exception{
		try(DruidPooledConnection conn = dds.getConnection()){
			courseOutlineService.createCourseOutline(conn,name,termId,classHour,credit,exam,elect,classIds);
			return APIResponse.getNewSuccessResp();
		}
	}
	
	@POSTAPI(//
			path = "delcourseOutline", 
			des = "根据课程大纲的Id来删除课程大纲",
			ret = "删除后的的课程大纲的信息")
	public APIResponse delcourseOutline(
			@P(t = "课程Id")Long Id//课程Id
			) throws Exception{
		try(DruidPooledConnection conn = dds.getConnection()){
			courseOutlineService.delCourseOutline(conn, Id);
			return APIResponse.getNewSuccessResp();
		}
	}
	
	
	@POSTAPI(//
			path = "editCourseOutline", 
			des = "根据课程大纲的ID来修改课程大纲的信息",
			ret = "所修改后的课程大纲的信息")
	public APIResponse editCourseOutline(
			Long Id,//课程大纲的Id
			String name,//课程大纲的名字
			Integer classHour,//学时
			Float credit,//学分
			Byte exam,//考核方式
			Byte elect,//课程性质
			String classIds//参加课程的班级编号列表(JSONArray)
			) throws Exception{
		try(DruidPooledConnection conn = dds.getConnection()){
			courseOutlineService.editCourseOutline(conn, Id, name, classHour, credit, exam, elect, classIds);
			return APIResponse.getNewSuccessResp();
		}
	}
	
	@POSTAPI(//
			path = "getCourseOutline", 
			des = "根据课程大纲的课程性质来获取课程大纲",
			ret = "所获取的课程大纲的信息")
	public APIResponse getCourseOutline(
			Byte elect,
			Integer count,
			Integer offset
			) throws Exception{
		try(DruidPooledConnection conn = dds.getConnection()){
			return APIResponse.getNewSuccessResp(courseOutlineService.getCourseOutline(conn, elect,count,offset));
		}
	}
	
	
	@POSTAPI(
			path = "getCourseOutlineByTermId", 
			des = "根据课程大纲的所属学院名称和学期来获取课程大纲",
			ret = "所获取的课程大纲的信息")
	public APIResponse getCourseOutlineByTermId(
			String collegeName,
			Long termId,
			Integer count,
			Integer offset
			) throws Exception{
		try(DruidPooledConnection conn = dds.getConnection()){
			
			return APIResponse.getNewSuccessResp(courseOutlineService.getCourseOutlineByTermId(conn, collegeName,termId,count,offset));
		}
	}
	
}
