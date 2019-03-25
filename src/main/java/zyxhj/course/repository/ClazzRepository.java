package zyxhj.course.repository;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.druid.pool.DruidPooledConnection;

import zyxhj.course.domain.attach.Clazz;
import zyxhj.utils.data.rds.RDSRepository;

public class ClazzRepository extends RDSRepository<Clazz> {

	public ClazzRepository() {
		super(Clazz.class);
	}

	public List<Clazz> queryCLazzBygrade(DruidPooledConnection conn, Long institutionId, Long deptId, Long majorId,
			String grade, Integer count, Integer offset) throws Exception{
		
		ArrayList<Object> obj = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer(" WHERE ");
		
		if(null != institutionId) {
			sb.append(" institution_id = ? ");
			obj.add(institutionId);
		}
		if(null != deptId) {
			sb.append(" dept_id = ? ");
			obj.add(deptId);
		}
		if(null != majorId) {
			sb.append(" major_id = ? ");
			obj.add(majorId);
		}
		sb.append(" grade = ? ");
		obj.add(grade);
		
		return this.getListByKey(conn, sb.toString(), obj.toArray(), count, offset);
	}


}
