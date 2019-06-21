package zyxhj.course.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;

import zyxhj.course.service.CourseService;
import zyxhj.utils.Singleton;
import zyxhj.utils.api.APIResponse;
import zyxhj.utils.api.Controller;
import zyxhj.utils.data.DataSource;

public class ScheduleController extends Controller {

	private static Logger log = LoggerFactory.getLogger(ScheduleController.class);
	private DruidDataSource dds;
	private CourseService importScheduleService;

	public ScheduleController(String node) {
		super(node);

		try {
			dds = DataSource.getDruidDataSource("rdsDefault.prop");
			importScheduleService = Singleton.ins(CourseService.class);
			// tempService = Singleton.ins(TempService.class);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	/**
	 * 
	 */
	@POSTAPI(//
			path = "importTempSchedule", //
			des = "导入临时课表" //
	)
	public APIResponse importTempSchedule(//
			@P(t = "excel文件url") String url//
	) throws Exception {
		try (DruidPooledConnection conn = dds.getConnection()) {
			importScheduleService.importTempSchedule(conn, url);
			return APIResponse.getNewSuccessResp();
		}
	}

	@POSTAPI(//
			path = "getSchedule", //
			des = "查询课表" //
	)
	public APIResponse getSchedule(//
			Integer count, //
			Integer offset //
	) throws Exception {
		try (DruidPooledConnection conn = dds.getConnection()) {

			return APIResponse.getNewSuccessResp(importScheduleService.getSchedule(conn, count, offset));
		}
	}

	@POSTAPI(//
			path = "deleteSchedule", //
			des = "查询课表" //
	)
	public APIResponse deleteSchedule(//
			@P(t = "课表id") Long schId//
	) throws Exception {
		try (DruidPooledConnection conn = dds.getConnection()) {
			importScheduleService.deleteSchedule(conn, schId);
			return APIResponse.getNewSuccessResp();
		}
	}

}