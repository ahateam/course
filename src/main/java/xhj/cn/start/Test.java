package xhj.cn.start;

import com.alibaba.druid.pool.DruidDataSource;

import zyxhj.course.domain.CourseOutline;
import zyxhj.course.domain.collegeOpen;
import zyxhj.utils.data.DataSource;
import zyxhj.utils.data.rds.RDSUtils;

public class Test {

	public static void main(String[] args) {

		testDB();

	}

	private static void testDB() {
		System.out.println("testDB");
		try {
			DruidDataSource dsRds = DataSource.getDruidDataSource("rdsDefault.prop");

//		   RDSUtils.dropTableByEntity(dsRds, Department.class); // 删表

		     RDSUtils.createTableByEntity(dsRds, CourseOutline.class); // 建表

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
