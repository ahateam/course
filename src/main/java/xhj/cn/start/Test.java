package xhj.cn.start;

import com.alibaba.druid.pool.DruidDataSource;

import zyxhj.utils.data.DataSource;

public class Test {

	public static void main(String[] args) {

		testDB();

	}

	private static void testDB() {
		System.out.println("testDB");
		try {
			DruidDataSource dsRds = DataSource.getDruidDataSource("rdsDefault.prop");

			// RDSUtils.dropTableByEntity(dsRds, Term.class); // 删表

			// RDSUtils.createTableByEntity(dsRds, TempSchedule.class); // 建表

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
