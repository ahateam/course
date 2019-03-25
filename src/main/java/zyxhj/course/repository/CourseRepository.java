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
					" WHERE 'institution_id' = ? AND 'course_name' LIKE '%");
			sb.append(idNumber).append("%'");

			try {
				return this.getList(conn, sb.toString(), new Object[] { institutionId }, count, offset);
			
			} catch (Exception e) {
				e.printStackTrace();
				return new ArrayList<Course>();
			}
		} else {
			return new ArrayList<Course>();
		}
	}

}
