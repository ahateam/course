package zyxhj.course.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.druid.pool.DruidPooledConnection;

import zyxhj.course.domain.attach.Course;
import zyxhj.course.domain.attach.Major;
import zyxhj.course.repository.ClazzRepository;
import zyxhj.course.repository.CourseRepository;
import zyxhj.course.repository.MajorRepository;
import zyxhj.utils.IDUtils;
import zyxhj.utils.Singleton;

public class MajorService {
	private static Logger log = LoggerFactory.getLogger(MajorService.class);

	private MajorRepository majorRepository;
	private CourseRepository courseRepository;
	private ClazzRepository clazzRepository;

	public MajorService() {
		try {
			majorRepository = Singleton.ins(MajorRepository.class);
			courseRepository = Singleton.ins(CourseRepository.class);
			clazzRepository = Singleton.ins(ClazzRepository.class);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	/**
	 * 添加专业
	 * 
	 * @param conn
	 * @param majorId
	 * @param majorName
	 * @param deptId
	 * @return
	 */
	public Major addMajor(DruidPooledConnection conn, String majorName, Long deptId, Long institutionId, Long collageId)
			throws Exception {
		Major m = new Major();
		m.id = IDUtils.getSimpleId();
		m.majorName = majorName;
		m.deptId = deptId;
		m.institutionId = institutionId;
		m.collageId = collageId;
		majorRepository.insert(conn, m);
		return m;
	}

	/**
	 * 删除专业
	 * 
	 * @param conn
	 * @param majorId
	 */
	public void deleteMajor(DruidPooledConnection conn, Long majorId) throws Exception {
		majorRepository.deleteByKey(conn, "id", majorId);
	}

	/**
	 * 修改专业信息
	 * 
	 * @param conn
	 * @param majorId
	 * @param majorName
	 * @param deptId
	 * @param collageId
	 * @param institutionId
	 * @return
	 */
	public Major upMajor(DruidPooledConnection conn, Long majorId, String majorName, Long deptId, Long institutionId,
			Long collageId) throws Exception {
		Major m = new Major();
		m.id = majorId;
		m.majorName = majorName;
		m.deptId = deptId;
		m.institutionId = institutionId;
		m.collageId = collageId;
		majorRepository.updateByKey(conn, "id", majorId, m, true);
		return m;
	}

	/**
	 * 查询专业信息
	 * 
	 * @param conn
	 * @param majorId
	 * @return
	 */
	public Major queryById(DruidPooledConnection conn, Long majorId) throws Exception {
		return majorRepository.getByKey(conn, "id", majorId);
	}

	/**
	 * 查询当前专业下所有课程信息
	 * 
	 * @param conn
	 * @param majorId
	 * @return
	 */
	public List<Course> queryCourseByMajorId(DruidPooledConnection conn, Long majorId) throws Exception {
		return courseRepository.getListByKey(conn, "id", majorId, 512, 0);
	}

	/**
	 * 统计当前专业下有多少班级
	 * 
	 * @param conn
	 * @param majorId
	 * @return
	 */
	public Integer countClazz(DruidPooledConnection conn, Long majorId) throws Exception {
		return clazzRepository.countByKey(conn, "major_id", majorId);
	}

}
