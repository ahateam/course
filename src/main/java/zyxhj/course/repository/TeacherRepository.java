package zyxhj.course.repository;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.druid.pool.DruidPooledConnection;

import zyxhj.course.domain.attach.Teacher;
import zyxhj.utils.Singleton;
import zyxhj.utils.data.rds.RDSRepository;

public class TeacherRepository extends RDSRepository<Teacher> {

	public TeacherRepository() {
		super(Teacher.class);
	}

	public List<Teacher> getLikeTeacher(DruidPooledConnection conn, String idNumber, Integer count,
			Integer offset) {
		if(StringUtils.isBlank(idNumber) && idNumber.length() >= 1) {
			
			StringBuffer sb = new StringBuffer(
					 "SELECT * FROM 'tb_teacher' WHERE 'teacher_name' LIKE '%"  );
			sb.append(idNumber).append("%' LIMIT ? OFFSET ? ");
			try {
				return this.nativeGetList(conn, Singleton.ins(TeacherRepository.class), sb.toString(), new Object[] {count,offset});
			}  catch (Exception e) {
				e.printStackTrace();
				return new ArrayList<Teacher>();
			}
		}else {
			return new ArrayList<Teacher>();
		}
	}


}
