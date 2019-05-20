package zyxhj.course.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.alibaba.druid.pool.DruidPooledConnection;

import zyxhj.course.service.CollageService;
import zyxhj.utils.Singleton;
import zyxhj.utils.api.APIResponse;
import zyxhj.utils.api.Controller;
import zyxhj.utils.data.DataSource;
import zyxhj.utils.data.DataSourceUtils;

public class CollageController extends Controller {

	private static Logger log = LoggerFactory.getLogger(CollageController.class);
	private DataSource dsRds;

	private CollageService collageService;

	public CollageController(String node) {
		super(node);

		try {
			dsRds = DataSourceUtils.getDataSource("rdsDefault");
			collageService = Singleton.ins(CollageService.class);
			// tempService = Singleton.ins(TempService.class);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	@POSTAPI(//
			path = "addCollage", //
			des = "添加课程大纲", //
			ret = "返回添加课程大纲信息")
	public APIResponse addCollage(//
			@P(t = "课程大纲名称") String collageName, //
			@P(t = "学识") Float knowledge, //
			@P(t = "课程性质", r = false) Byte electId, //
			@P(t = "考核方式") Byte examId

	) throws Exception {
		try (DruidPooledConnection conn = (DruidPooledConnection) dsRds.openConnection()) {
			return APIResponse
					.getNewSuccessResp(collageService.addCollage(conn, collageName, knowledge, electId, examId));
		}
	}

	@POSTAPI(//
			path = "deleteCollage", //
			des = "删除课程大纲", //
			ret = "删除")
	public APIResponse delCollage(//
			@P(t = "课程大纲编号") Long Id) throws Exception {
		try (DruidPooledConnection conn = (DruidPooledConnection) dsRds.openConnection()) {

			collageService.delCollage(conn, Id);
			return APIResponse.getNewSuccessResp();
		}
	}

	@POSTAPI(//
			path = "getCollage", //
			des = "更据编号查找课程大纲", //
			ret = "课程大纲")
	public APIResponse getCollage(@P(t = "课程大纲ID") Long Id) throws Exception {
		try (DruidPooledConnection conn = (DruidPooledConnection) dsRds.openConnection()) {

			collageService.queryCollageById(conn, Id);
			return APIResponse.getNewSuccessResp();
		}
	}

	@POSTAPI(//
			path = "upCollage", //
			des = "修改课程大纲", //
			ret = "课程大纲修改信息")
	public APIResponse upCollage(@P(t = "课程大纲ID") Long Id, @P(t = "课程大纲名称") String CollageName,
			@P(t = "总学时") Float knowledge, @P(t = "课程性质") Byte electId, @P(t = "考核方式") Byte examId

	) throws Exception {
		try (DruidPooledConnection conn = (DruidPooledConnection) dsRds.openConnection()) {

			collageService.upCollageClass(conn, Id, CollageName, knowledge, electId, examId);
			return APIResponse.getNewSuccessResp();
		}
	}

	@POSTAPI(//
			path = "getCollageClass", //
			des = "查询所有课程大纲", //
			ret = "课程大纲信息")
	public APIResponse getCollageClass(Integer count, //
			Integer offset //
	) throws Exception {
		try (DruidPooledConnection conn = (DruidPooledConnection) dsRds.openConnection()) {
			return APIResponse.getNewSuccessResp(collageService.getCollageClass(conn, count, offset));
		}
	}
	
	
	

}