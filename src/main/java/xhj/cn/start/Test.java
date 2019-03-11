package xhj.cn.start;

import com.alibaba.druid.pool.DruidPooledConnection;

import zyxhj.course.domain.CourseSchedule;
import zyxhj.course.domain.Term;
import zyxhj.course.domain.attach.Classroom;
import zyxhj.course.domain.attach.Clazz;
import zyxhj.course.domain.attach.Collage;
import zyxhj.course.domain.attach.Course;
import zyxhj.course.domain.attach.Dept;
import zyxhj.course.domain.attach.Institution;
import zyxhj.course.domain.attach.Major;
import zyxhj.course.domain.attach.Teacher;
import zyxhj.utils.data.DataSource;
import zyxhj.utils.data.DataSourceUtils;
import zyxhj.utils.data.rds.RDSUtils;

public class Test {

	private static DruidPooledConnection conn;

	static {
		DataSourceUtils.initDataSourceConfig();
		// contentService = ContentService.getInstance();

		try {
			conn = (DruidPooledConnection) DataSourceUtils.getDataSource("rdsDefault").openConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		testDB();

	}

	private static void testDB() {
		System.out.println("testDB");
		try {
			DataSource dsRds = DataSourceUtils.getDataSource("rdsDefault");

			// RDSUtils.dropTableByEntity(dsRds, Clazz.class);

			 RDSUtils.createTableByEntity(dsRds, Teacher.class);
			 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
