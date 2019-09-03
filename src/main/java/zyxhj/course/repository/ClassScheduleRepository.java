package zyxhj.course.repository;

import java.util.ArrayList;

import com.alibaba.druid.pool.DruidPooledConnection;
import com.alibaba.fastjson.JSONObject;

import zyxhj.course.domain.ClassScheduleTerm;
import zyxhj.utils.data.rds.RDSRepository;

public class ClassScheduleRepository extends RDSRepository<ClassScheduleTerm>{
	public ClassScheduleRepository(){
		super(ClassScheduleTerm.class);
	}
	
	public Integer countAll(DruidPooledConnection conn) throws Exception {
		StringBuffer sb = new StringBuffer("select count(*) c from tb_temp_schedule");
		JSONObject jo = sqlGetJSONObject(conn, sb.toString(), new ArrayList<Object>());
		Integer count = jo.getInteger("c");
		return count;
	}
}
