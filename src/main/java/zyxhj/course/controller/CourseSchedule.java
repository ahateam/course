package zyxhj.course.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.druid.pool.DruidPooledConnection;

import zyxhj.course.service.TermService;
import zyxhj.utils.Singleton;
import zyxhj.utils.api.APIResponse;
import zyxhj.utils.api.Controller;
import zyxhj.utils.data.DataSource;
import zyxhj.utils.data.DataSourceUtils;

public class CourseSchedule extends Controller {

	private static Logger log = LoggerFactory.getLogger(CourseSchedule.class);

	private DataSource dsRds;
	private TermService termService;

	public CourseSchedule(String node) {
		super(node);

		try {
			dsRds = DataSourceUtils.getDataSource("rdsDefault");

			termService = Singleton.ins(TermService.class);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	@POSTAPI(//
			path = "createTerm", //
			des = "创建学期", //
			ret = "返回学习信息")
	public APIResponse createTerm(//
			@P(t = "学期名") String termName, //
			@P(t = "多少周") Integer weekCount, //
			@P(t = "开始时间") Date startDate, //
			@P(t = "结束时间") Date endDate, //
			@P(t = "备注") String remark //
	) throws Exception {
		try (DruidPooledConnection conn = (DruidPooledConnection) dsRds.openConnection()) {
			return APIResponse
					.getNewSuccessResp(termService.createTerm(conn, termName, weekCount, startDate, endDate, remark));
		}
	}

	@POSTAPI(//
			path = "editTerm", //
			des = "修改学期", //
			ret = "返回修改信息")
	public APIResponse editTerm(//

			@P(t = "学期名") Long termId, //
			@P(t = "学期名") String termName, //
			@P(t = "多少周") Integer weekCount, //
			@P(t = "开始时间") Date startDate, //
			@P(t = "结束时间") Date endDate, //
			@P(t = "备注") String remark //
	) throws Exception {
		try (DruidPooledConnection conn = (DruidPooledConnection) dsRds.openConnection()) {
			return APIResponse.getNewSuccessResp(
					termService.editTerm(conn, termId, termName, weekCount, startDate, endDate, remark));
		}
	}

	@POSTAPI(//
			path = "delTerm", //
			des = "删除学期", //
			ret = "返回几表示删除了多少   如果为0则表示一条记录也没有删除")
	public APIResponse delTerm(//

			@P(t = "学期id") Long termId //
	) throws Exception {
		try (DruidPooledConnection conn = (DruidPooledConnection) dsRds.openConnection()) {
			return APIResponse.getNewSuccessResp(termService.delTerm(conn, termId));
		}
	}

	@POSTAPI(//
			path = "getTerms", //
			des = "获取学期  分页", //
			ret = "返回学期信息 分页")
	public APIResponse getTerms(//
			@P(t = "每页显示记录数") Integer count, //
			@P(t = "从第几条记录开始") Integer offset) throws Exception {
		try (DruidPooledConnection conn = (DruidPooledConnection) dsRds.openConnection()) {
			return APIResponse.getNewSuccessResp(termService.getTerms(conn, count, offset));
		}
	}

}
