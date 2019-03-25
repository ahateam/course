package zyxhj.course.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.druid.pool.DruidPooledConnection;

import zyxhj.course.domain.attach.Course;
import zyxhj.course.repository.CourseRepository;
import zyxhj.utils.IDUtils;
import zyxhj.utils.Singleton;

public class CourseService {
	private static Logger log = LoggerFactory.getLogger(CourseService.class);

	// private ChannelUserRepository channelUserRepository;
	private CourseRepository courseRepository;

	public CourseService() {
		try {
			// channelUserRepository = Singleton.ins(ChannelUserRepository.class);
			courseRepository = Singleton.ins(CourseRepository.class);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	/**
	 * 添加課程
	 * 
	 * @param conn
	 * @param courseId
	 * @param courseName
	 * @param teacherId
	 * @param majorId
	 * @param knowledge
	 * @param collageId
	 * @param institutionId
	 * @param depatId
	 * @return
	 */
	public Course addCourse(DruidPooledConnection conn,  String courseName, Long knowledge, Long majorId,
			Long teacherId, Long depatId, Long institutionId, Long collageId) throws Exception {
		Course c = new Course();
		c.Id = IDUtils.getSimpleId();
		c.courseName = courseName;
		c.knowledge = knowledge;
		c.majorId = majorId;
		c.teacherId = teacherId;
		c.depatId = depatId;
		c.institutionId = institutionId;
		c.collageId = collageId;
		courseRepository.insert(conn, c);
		return c;
	}

	/**
	 * 刪除課程
	 * 
	 * @param conn
	 * @param courseId
	 */
	public void deleteCourse(DruidPooledConnection conn, Long courseId) throws Exception {
		courseRepository.deleteByKey(conn, "id", courseId);
	}

	/**
	 * 修改課程
	 * 
	 * @param conn
	 * @param courseId
	 * @param teacherId
	 * @param majorId
	 * @param knowledge
	 * @param courseName
	 * @param collageId
	 * @param institutionId
	 * @param depatId
	 * @return
	 */
	public Course upCourse(DruidPooledConnection conn, Long courseId, String courseName, Long knowledge, Long majorId,
			Long teacherId, Long depatId, Long institutionId, Long collageId) throws Exception {
		Course c = new Course();
		c.Id = courseId;
		c.courseName = courseName;
		c.knowledge = knowledge;
		c.majorId = majorId;
		c.teacherId = teacherId;
		c.depatId = depatId;
		c.institutionId = institutionId;
		c.collageId = collageId;
		courseRepository.updateByKey(conn, "id", courseId, c, true);
		return c;
	}

	/**
	 * 根据id查询课程信息
	 * 
	 * @param conn
	 * @param courseId
	 * @return
	 * @throws Exception
	 */
	public Course queryCourseById(DruidPooledConnection conn, Long courseId) throws Exception {
		return courseRepository.getByKey(conn, "id", courseId);
	}

	public List<Course> getCourse(DruidPooledConnection conn) throws Exception {
		return courseRepository.getList(conn, 64, 0);
	}

}
