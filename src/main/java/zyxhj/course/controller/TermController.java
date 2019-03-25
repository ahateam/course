package zyxhj.course.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.druid.pool.DruidPooledConnection;

import zyxhj.course.domain.Term;
import zyxhj.course.service.TermService;
import zyxhj.utils.Singleton;
import zyxhj.utils.api.APIResponse;
import zyxhj.utils.api.Controller;
import zyxhj.utils.data.DataSource;
import zyxhj.utils.data.DataSourceUtils;

public class TermController extends Controller {

	private static Logger log = LoggerFactory.getLogger(TermController.class);

	private DataSource dsRds;
	// private TempService tempService;
	private TermService termService;

	public TermController(String node) {
		super(node);

		try {
			dsRds = DataSourceUtils.getDataSource("rdsDefault");

			// tempService = Singleton.ins(TempService.class);
			termService = Singleton.ins(TermService.class);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	@POSTAPI(//
			path = "addTerm" ,//
			des = "添加学期安排" ,//
			ret = "返回添加学期信息"
			)
	public APIResponse addTerm(//
			@P(t = "第几学期") String termName,//
			@P(t = "开始时间") Date startDate,//
			@P(t = "结束时间") Date endDate, //
			@P(t = "备注") String remark
			) throws Exception {
		try (DruidPooledConnection conn = (DruidPooledConnection) dsRds.openConnection()) {
			// User user = ServiceUtils.userAuth(conn, userId);// user鉴权

			Term t = termService.addTerm(conn,  termName, startDate, endDate, remark);
			return APIResponse.getNewSuccessResp(t);
		}
	}
	
	@POSTAPI(//
			path = "deleteTerm" ,//
			des = "删除学期安排" ,//
			ret = ""
			)
	public APIResponse deleteTerm(//
			@P(t = "学期编号") Long termId
			) throws Exception{
		try (DruidPooledConnection conn = (DruidPooledConnection) dsRds.openConnection()) {
			// User user = ServiceUtils.userAuth(conn, userId);// user鉴权

			termService.deleteTerm(conn, termId);
			return APIResponse.getNewSuccessResp();
		}
	}
	

	@POSTAPI(//
			path = "upTerm" ,//
			des = "修改学期安排" ,//
			ret = ""
			)
	public APIResponse upTerm(//
			@P(t = "学期编号") Long termId,//
			@P(t = "学期名") String termName,//
			@P(t = "开始时间") Date startDate,//
			@P(t = "结束时间") Date endDate, //
			@P(t = "备注") String remark
			) throws Exception{
		try (DruidPooledConnection conn = (DruidPooledConnection) dsRds.openConnection()) {
			// User user = ServiceUtils.userAuth(conn, userId);// user鉴权

			termService.upTerm(conn, termId, termName, startDate, endDate, remark);
			return APIResponse.getNewSuccessResp();
		}
	}
	
	@POSTAPI(//
			path = "queryTermById" ,//
			des = "根据id查询学期安排" ,//
			ret = ""
			)
	public APIResponse queryTermById(//
			@P(t = "学期编号") Long termId
			) throws Exception{
		try (DruidPooledConnection conn = (DruidPooledConnection) dsRds.openConnection()) {
			// User user = ServiceUtils.userAuth(conn, userId);// user鉴权

			Term t = termService.queryTermById(conn, termId);
			return APIResponse.getNewSuccessResp(t);
		}
	}
	
	
}
