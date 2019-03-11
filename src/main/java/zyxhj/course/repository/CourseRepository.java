package zyxhj.course.repository;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.druid.pool.DruidPooledConnection;

import zyxhj.course.domain.attach.Course;
import zyxhj.utils.Singleton;
import zyxhj.utils.data.rds.RDSRepository;

public class CourseRepository extends RDSRepository<Course> {

	public CourseRepository() {
		super(Course.class);
	}
	public List<Course> getLikeCourse(DruidPooledConnection conn, Long institutionId, String idNumber, Integer count,
			Integer offset) {
		if (StringUtils.isBlank(idNumber) && idNumber.length() >= 1) {

			StringBuffer sb = new StringBuffer(
					"SELECT * FROM 'tb_course' WHERE 'institution_id' = ? AND 'courseName' LIKE '%");
			sb.append(idNumber).append("%' LIMIT ? OFFSET ? ");

			try {
				return this.nativeGetList(conn, Singleton.ins(CourseRepository.class), sb.toString(),
						new Object[] { institutionId, count, offset });
			} catch (Exception e) {
				e.printStackTrace();
				return new ArrayList<Course>();
			}

		} else {
			return new ArrayList<Course>();
		}
	}

}
