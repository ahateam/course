package zyxhj.course.service;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.druid.pool.DruidPooledConnection;

import zyxhj.course.domain.CourseSchedule;
import zyxhj.course.domain.attach.Classroom;
import zyxhj.course.repository.ClassroomRepository;
import zyxhj.course.repository.CourseScheduleRepository;
import zyxhj.utils.IDUtils;
import zyxhj.utils.Singleton;

public class ClassRoomService {
	private static Logger log = LoggerFactory.getLogger(ClassRoomService.class);

	private  ClassroomRepository classroomRepository;
	private  CourseScheduleRepository courseScheduleRepository;
	public ClassRoomService() {
		try {
			classroomRepository = Singleton.ins(ClassroomRepository.class);
			courseScheduleRepository = Singleton.ins(CourseScheduleRepository.class);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}


	/**
	 * 添加教室
	 * @param conn
	 * @param courseId
	 * @param courseName
	 * @return
	 */
	public Classroom addClassroom(DruidPooledConnection conn,Long classroomId,String classroomName,String buildNum) throws Exception{
		Classroom c = new Classroom();
		c.Id = classroomId;
		c.classroomName = classroomName;
		c.buildNum = buildNum;
		classroomRepository.insert(conn, c);
		return c;
	}

	/**
	 * 刪除教室
	 * @param conn
	 * @param courseId
	 */
	public void deleteClassroom(DruidPooledConnection conn,Long classroomId) throws Exception{
		classroomRepository.deleteByKey(conn, "id", classroomId);
	}
	
	/**
	 * 修改教室
	 * @param conn
	 * @param classroomName 
	 * @param courseId
	 * @return
	 */
	public Classroom upClassroom(DruidPooledConnection conn,Long classroomId, String classroomName,String buildNum) throws Exception{
		Classroom c = new Classroom();
		c.Id = classroomId;
		c.classroomName = classroomName;
		c.buildNum = buildNum;
		classroomRepository.updateByKey(conn, "id", classroomId, c, true);
		return c;
	}
	 
	/**
	 * 根据教室id查询当前教室的课程安排.
	 * @param conn
	 * @param classroomId
	 * @return
	 */
	public List<CourseSchedule> queryCourseScheduleById(DruidPooledConnection conn,Long classroomId) throws Exception{
		return courseScheduleRepository.getListByKey(conn, "classroom_id", classroomId, 64, 0);
	}

	
	/**
	 * 根据id查询教室
	 * @param conn
	 * @param classroomId
	 * @return
	 * @throws Exception
	 */
	public Classroom queryClassroomById(DruidPooledConnection conn, Long classroomId) throws Exception{
		return classroomRepository.getByKey(conn, "classroom_id", classroomId);
	}


	/**
	 * 添加教学楼
	 * @param conn
	 * @param classroomId
	 * @param buildNum
	 * @return
	 * @throws Exception
	 */
	public Classroom addbuild(DruidPooledConnection conn , String buildNum) throws Exception {
		Classroom c = new Classroom();
		c.Id = IDUtils.getSimpleId();
		c.buildNum = buildNum;
		classroomRepository.insert(conn, c);
		return c;
	}

}
