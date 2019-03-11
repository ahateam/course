package zyxhj.course.repository;

import java.util.List;

import com.alibaba.druid.pool.DruidPooledConnection;

import zyxhj.course.domain.attach.Classroom;
import zyxhj.utils.data.rds.RDSRepository;

public class ClassroomRepository extends RDSRepository<Classroom> {

	public ClassroomRepository() {
		super(Classroom.class);
	}

	public List<Classroom> getBulidNum(DruidPooledConnection conn) throws Exception{
		this.getList(conn, "GROUP BY bulid_num ", new Object[]{}, 64, 0);
		return null;
	}

	public Long getClassroomId(DruidPooledConnection conn, String classroomName, String bulidNum) throws Exception{
		Classroom c = this.get(conn, "WHERE classroom_name = ? AND build_num = ? ", new Object[] {classroomName,bulidNum});
		return c.Id;
	}


}
