package zyxhj.course.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.druid.pool.DruidDataSource;

import zyxhj.utils.api.APIResponse;
import zyxhj.utils.api.Controller;
import zyxhj.utils.data.DataSource;

public class TempController extends Controller {

	private static Logger log = LoggerFactory.getLogger(TempController.class);

	private DruidDataSource dds;
	// private TempService tempService;

	public TempController(String node) {
		super(node);

		try {
			dds = DataSource.getDruidDataSource("rdsDefault.prop");

			// tempService = Singleton.ins(TempService.class);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	@POSTAPI(//
			path = "add", //
			des = "加法测试", //
			ret = "两个参数相加的值")
	public APIResponse add(Integer p1, //
			Integer p2, //
			@P(t = "临时变脸", r = false) String temp//
	) throws Exception {
		return APIResponse.getNewSuccessResp(p1 + p2);
	}

}
