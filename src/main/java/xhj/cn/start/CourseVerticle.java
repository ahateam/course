package xhj.cn.start;

import io.vertx.core.Vertx;
import zyxhj.cms.controller.ContentController;
import zyxhj.core.controller.TagController;
import zyxhj.core.controller.TestController;
import zyxhj.core.controller.UserController;
import zyxhj.course.controller.ClassroomController;
import zyxhj.course.controller.ClazzController;
import zyxhj.course.controller.CollageController;
import zyxhj.course.controller.CourseController;
import zyxhj.course.controller.CourseScheduleController;
import zyxhj.course.controller.DeptController;
import zyxhj.course.controller.InstitutionController;
import zyxhj.course.controller.MajorController;
import zyxhj.course.controller.TeacherController;
import zyxhj.course.controller.TempController;
import zyxhj.course.controller.TermController;
import zyxhj.course.domain.CourseSchedule;
import zyxhj.course.domain.attach.Collage;
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

	protected void init() throws Exception {

		DataSourceUtils.initDataSourceConfig();

		initCtrl(ctrlMap, Singleton.ins(TestController.class, "test"));

		initCtrl(ctrlMap, Singleton.ins(UserController.class, "user"));

		initCtrl(ctrlMap, Singleton.ins(TagController.class, "tag"));

		initCtrl(ctrlMap, Singleton.ins(ContentController.class, "content"));
		
		initCtrl(ctrlMap,Singleton.ins(TempController.class, "temp"));
		
		initCtrl(ctrlMap,Singleton.ins(CourseScheduleController.class,"courseSchedule"));
		
		initCtrl(ctrlMap,Singleton.ins(CourseController.class,"course"));
		
		initCtrl(ctrlMap,Singleton.ins(TeacherController.class,"teacher"));
		
		initCtrl(ctrlMap,Singleton.ins(ClazzController.class,"clazz"));
		
		initCtrl(ctrlMap,Singleton.ins(TermController.class,"term"));
		
		initCtrl(ctrlMap,Singleton.ins(ClassroomController.class,"classroom"));
		
		initCtrl(ctrlMap,Singleton.ins(CollageController.class,"collage"));
		
		initCtrl(ctrlMap,Singleton.ins(InstitutionController.class,"ins"));
		
		initCtrl(ctrlMap,Singleton.ins(DeptController.class,"dept"));
		
		initCtrl(ctrlMap,Singleton.ins(MajorController.class,"major"));
		
		
	}

}
