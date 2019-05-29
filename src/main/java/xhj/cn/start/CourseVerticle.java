package xhj.cn.start;

import io.vertx.core.Vertx;
import zyxhj.core.controller.TestController;
import zyxhj.course.controller.ScheduleController;
import zyxhj.utils.Singleton;
import zyxhj.utils.ZeroVerticle;
import zyxhj.utils.data.DataSourceUtils;

public class CourseVerticle extends ZeroVerticle {

	public static void main(String[] args) {
		Vertx vertx = Vertx.vertx();
		vertx.deployVerticle(new CourseVerticle());
	}

	public String name() {
		return "course";
	}

	public int port() {
		return 8080;
	}

	protected void init() throws Exception {

		DataSourceUtils.initDataSourceConfig();

		initCtrl(ctrlMap, Singleton.ins(TestController.class, "test"));

		initCtrl(ctrlMap, Singleton.ins(ScheduleController.class, "sch"));

	}

}
