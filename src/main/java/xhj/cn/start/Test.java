package xhj.cn.start;

import com.alibaba.druid.pool.DruidPooledConnection;

import zyxhj.course.domain.CourseOutline;
import zyxhj.course.domain.TempSchedule;
import zyxhj.utils.data.DataSource;
import zyxhj.utils.data.DataSourceUtils;
import zyxhj.utils.data.rds.RDSUtils;

public class Test {

	private static DruidPooledConnection conn;

	static {
		DataSourceUtils.initDataSourceConfig();
//		 contentService = ContentService.getInstance();

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

	//	 RDSUtils.dropTableByEntity(dsRds, Schedule.class);  // 删表
			
			 RDSUtils.createTableByEntity(dsRds, TempSchedule.class); // 建表
			 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
