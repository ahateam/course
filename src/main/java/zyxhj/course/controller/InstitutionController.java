package zyxhj.course.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.druid.pool.DruidPooledConnection;

import zyxhj.course.domain.attach.Institution;
import zyxhj.course.service.InstitutionService;
import zyxhj.utils.Singleton;
import zyxhj.utils.api.APIResponse;
import zyxhj.utils.api.Controller;
import zyxhj.utils.data.DataSource;
import zyxhj.utils.data.DataSourceUtils;

public class InstitutionController extends Controller {

	private static Logger log = LoggerFactory.getLogger(InstitutionController.class);

	private DataSource dsRds;
	private InstitutionService institutionService;
	public InstitutionController(String node) {
		super(node);

		try {
			dsRds = DataSourceUtils.getDataSource("rdsDefault");

			institutionService = Singleton.ins(InstitutionService.class);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}
	
	@POSTAPI(//
			path = "addInstitution" ,//
			des = "添加学院" ,//
			ret = "返回添加学院信息"
			)
	public APIResponse addInstitution(//
			@P(t = "学院id") Long institutionId,//
			@P(t = "学院名称") String institutionName,//
			@P(t = "院校id" ,r = false) Long colleageId
			) throws Exception {
		try (DruidPooledConnection conn = (DruidPooledConnection) dsRds.openConnection()) {
			// User user = ServiceUtils.userAuth(conn, userId);// user鉴权

			Institution i = institutionService.addInstitution(conn,institutionId, institutionName,colleageId);
			return APIResponse.getNewSuccessResp(i);
		}
	}
	
	@POSTAPI(//
			path = "upInstitution" ,//
			des = "修改学院" ,//
			ret = "返回修改学院程信息"
			)
	public APIResponse upInstitution(//
			@P(t = "学院id") Long institutionId,//
			@P(t = "学院名称") String institutionName,//
			@P(t = "院校id" ,r = false) Long colleageId
			) throws Exception {
		try (DruidPooledConnection conn = (DruidPooledConnection) dsRds.openConnection()) {
			// User user = ServiceUtils.userAuth(conn, userId);// user鉴权
			Institution i = institutionService.upInstitution(conn,institutionId, institutionName,colleageId);
			return APIResponse.getNewSuccessResp(i);
		}
	}
	
	@POSTAPI(//
			path = "deleteInstitution" ,//
			des = "删除学院" ,//
			ret = ""
			)
	public APIResponse deleteInstitution(//
			@P(t = "学院id") Long institutionId
			) throws Exception {
		try (DruidPooledConnection conn = (DruidPooledConnection) dsRds.openConnection()) {
			// User user = ServiceUtils.userAuth(conn, userId);// user鉴权
			institutionService.deleteInstitution(conn,institutionId);
			return APIResponse.getNewSuccessResp();
		}
	}
	
	@POSTAPI(//
			path = "queryById" ,//
			des = "查询学院" ,//
			ret = "返回查询信息"
			)
	public APIResponse queryById(//
			@P(t = "学院id") Long institutionId

			) throws Exception {
		try (DruidPooledConnection conn = (DruidPooledConnection) dsRds.openConnection()) {
			// User user = ServiceUtils.userAuth(conn, userId);// user鉴权

			Institution i = institutionService.queryById(conn,institutionId);
			return APIResponse.getNewSuccessResp(i);
		}
	}
	
}
