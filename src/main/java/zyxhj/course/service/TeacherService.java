package zyxhj.course.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.druid.pool.DruidPooledConnection;

import zyxhj.course.domain.attach.Clazz;
import zyxhj.course.domain.attach.Course;
import zyxhj.course.domain.attach.Teacher;
import zyxhj.course.repository.ClazzRepository;
import zyxhj.course.repository.CourseRepository;
import zyxhj.course.repository.TeacherRepository;
import zyxhj.utils.Singleton;

public class TeacherService {
	private static Logger log = LoggerFactory.getLogger(TeacherService.class);

	private  TeacherRepository teacherRepository;
	private  ClazzRepository clazzRepository;
	private  CourseRepository courseRepository;

	public TeacherService() {
		try {
			teacherRepository = Singleton.ins(TeacherRepository.class);
			clazzRepository = Singleton.ins(ClazzRepository.class);
			courseRepository = Singleton.ins(CourseRepository.class);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	/**
	 * 添加教师信息
	 * 
	 * @param conn
	 * @param teacherId
	 * @param TeacherName
	 * @param teacherPhone
	 * @param TeacherIdCard
	 * @param departId
	 * @param collageId 
	 * @param institutionId 
	 * @param information 
	 * @return
	 */
	public Teacher addTeacher(DruidPooledConnection conn, Long teacherId, String teacherName,
			String teacherPhone, Long departId, Long institutionId, Long collageId, String information) throws Exception {
		Teacher t = new Teacher();
		t.Id = teacherId;
		t.teacherName = teacherName;
		t.teacherPhone = teacherPhone;
		t.departId = departId;
		t.institutionId = institutionId;
		t.collageId = collageId;
		t.information = information;
		teacherRepository.insert(conn, t);
		return t; 	
	}

	/**
	 * 删除教师信息
	 * 
	 * @param conn
	 * @param teacherId
	 */
	public void deleteTeacher(DruidPooledConnection conn, Long teacherId) throws Exception{
		teacherRepository.deleteByKey(conn, "id", teacherId);
	}

	/**
	 * 修改教师信息
	 * 
	 * @param conn
	 * @param teacherId
	 * @param departId
	 * @param teacherEmail
	 * @param teacherPhone
	 * @param teacherName
	 * @param collageId 
	 * @param institutionId 
	 * @param information 
	 * @return
	 */
	public Teacher upTeacher(DruidPooledConnection conn, Long teacherId, String teacherName, String teacherPhone,
			String teacherEmail, Long departId, Long institutionId, Long collageId, String information) throws Exception{
		Teacher t = new Teacher();
		t.Id = teacherId;
		t.teacherName = teacherName;
		t.teacherPhone = teacherPhone;
		t.departId = departId;
		t.institutionId = institutionId;
		t.information = information;
		t.collageId = collageId;
		teacherRepository.updateByKey(conn, "id", teacherId, t, true);
		return t;
	}

	/**
	 * 根据id查询教师信息
	 * 
	 * @param conn
	 * @param teacherId
	 * @return
	 */
	public Teacher queryTeacherById(DruidPooledConnection conn, Long teacherId) throws Exception{
		return teacherRepository.getByKey(conn, "id", teacherId);
	}



	/**
	 * 查询教师所教班级
	 * 
	 * @param conn
	 * @param teacherId
	 * @return
	 */
	public List<Clazz> queryClazzByTeacherId(DruidPooledConnection conn, Long teacherId) throws Exception{
		return clazzRepository.getListByKey(conn, "teacher_id", teacherId, 64, 0);
	}

	/**
	 * 查询教师所教课程
	 * 
	 * @param conn
	 * @param teacherId
	 * @return
	 */
	public List<Course> queryCourseByTeacherId(DruidPooledConnection conn, Long teacherId) throws Exception{
		return courseRepository.getListByKey(conn, "teacher_id", teacherId, 64, 0);
	}

	/**
	 * 统计课时
	 * 
	 * @param conn
	 * @param teacherId
	 * @return
	 */
	public Long countKnowledge(DruidPooledConnection conn, Long teacherId) throws Exception{
		List<Course> li = courseRepository.getListByKey(conn, "teacher_id", teacherId, 64, 0);
		
		return null;
	}   
	
	/**
	 * 查询学院课程
	 * @param conn
	 * @param institutionId
	 * @return
	 * @throws Exception
	 */
	public List<Course> getCourseByins(DruidPooledConnection conn,Long institutionId) throws Exception{
		List<Course> listByKey = courseRepository.getListByKey(conn, "institution_id", institutionId, 512, 0);
		return listByKey;
	}
	
	/**
	 * 模糊查询课程
	 * @param conn
	 * @param institutionId
	 * @param idNumber
	 * @param count
	 * @param offset
	 * @return
	 */
	public List<Course> getLikeCourse(DruidPooledConnection conn,Long institutionId,String idNumber,Integer count,Integer offset){
		return courseRepository.getLikeCourse(conn,institutionId,idNumber,count,offset);
	}
	
	/**
	 * 模糊查询教师
	 * @param conn
	 * @param teacherId
	 * @param idNumber
	 * @param count
	 * @param offset
	 * @return
	 */
	public List<Teacher> getLikeTeacher(DruidPooledConnection conn,String idNumber,Integer count,Integer offset){
		return teacherRepository.getLikeTeacher( conn, idNumber, count, offset);
		
	}
}
