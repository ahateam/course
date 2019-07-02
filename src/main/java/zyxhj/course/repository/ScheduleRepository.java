package zyxhj.course.repository;

import com.alibaba.druid.pool.DruidPooledConnection;
import com.alibaba.fastjson.JSONObject;

import zyxhj.course.domain.TempSchedule;
import zyxhj.utils.data.rds.RDSRepository;;

public class ScheduleRepository extends RDSRepository<TempSchedule> {

	public ScheduleRepository() {
		super(TempSchedule.class);
	}

	public Integer countAll(DruidPooledConnection conn) throws Exception {
		StringBuffer sb = new StringBuffer("select count(*) c from tb_temp_schedule");
		sb.append("").append("");
		JSONObject jo = sqlGetJSONObject(conn, sb.toString(), new Object[] {});
		Integer count = jo.getInteger("c");
		return count;
	}
}
