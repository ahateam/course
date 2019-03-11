package zyxhj.course.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.druid.pool.DruidPooledConnection;

import zyxhj.course.domain.attach.Collage;
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
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}
	
	@POSTAPI(//
			path = "addCollage" ,//
			des = "添加院校" ,//
			ret = "返回添加院校信息"
			)
	public APIResponse addCollage(//
			@P(t = "院校编号") Long collageId,//
			@P(t = "院校名称") String collageName
			) throws Exception {
		try (DruidPooledConnection conn = (DruidPooledConnection) dsRds.openConnection()) {
			// User user = ServiceUtils.userAuth(conn, userId);// user鉴权
			Collage c = collageService.addCollage(conn,collageId,collageName);
			return APIResponse.getNewSuccessResp(c);
		}
	}
	
	@POSTAPI(//
			path = "upCollage" ,//
			des = "修改院校" ,//
			ret = "返回修改信息"
			)
	public APIResponse upCollage(//
			@P(t = "院校编号") Long collageId,//
			@P(t = "院校名称") String collageName
			) throws Exception {
		try (DruidPooledConnection conn = (DruidPooledConnection) dsRds.openConnection()) {
			// User user = ServiceUtils.userAuth(conn, userId);// user鉴权
			Collage c = collageService.upCollage(conn,collageId,collageName);
			return APIResponse.getNewSuccessResp(c);
		}
	}
	
	@POSTAPI(//
			path = "deleteCollage" ,//
			des = "删除院校" ,//
			ret = ""
			)
	public APIResponse deleteCollage(//
			@P(t = "院校编号") Long collageId //
			) throws Exception {
		try (DruidPooledConnection conn = (DruidPooledConnection) dsRds.openConnection()) {
			// User user = ServiceUtils.userAuth(conn, userId);// user鉴权
			collageService.deleteCollage(conn,collageId);
			return APIResponse.getNewSuccessResp();
		}
	}
	
	@POSTAPI(//
			path = "queryCollageById" ,//
			des = "根据id查询院校" ,//
			ret = "返回查询信息"
			)
	public APIResponse queryCollageById(//
			@P(t = "院校编号") Long collageId //
			) throws Exception {
		try (DruidPooledConnection conn = (DruidPooledConnection) dsRds.openConnection()) {
			// User user = ServiceUtils.userAuth(conn, userId);// user鉴权
			Collage c = collageService.queryCollageById(conn,collageId);
			return APIResponse.getNewSuccessResp(c);
		}
	}

}
