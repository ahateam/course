package zyxhj.course.repository;

import java.util.List;

import com.alibaba.druid.pool.DruidPooledConnection;

import zyxhj.course.domain.CourseSchedule;
import zyxhj.utils.data.rds.RDSRepository;

public class CourseScheduleRepository extends RDSRepository<CourseSchedule> {

	public CourseScheduleRepository() {
		super(CourseSchedule.class);
	}

	
	/**
	 * 统计
	 */
	public int count(DruidPooledConnection conn) {
		return this.count(conn);
	}

	

	/**
	 * 根据教室信息查询课程安排记录
	 * @param conn
	 * @param termId
	 * @param classroom
	 * @return
	 * @throws Exception
	 */
	public List<CourseSchedule> queryCourseByClassroom(DruidPooledConnection conn, Long classroomId)
			throws Exception {
		Object[] c= {classroomId};
		return getList(conn, "WHERE classroom_id = ?", c,1024, 0); 
	}

	public int queryCourseSchedule(DruidPooledConnection conn, Long classroomId, Integer weekStart, Integer weekEnd,
			Integer numDay, Integer timeStart, Integer timeEnd) throws Exception{
		
		StringBuffer sb = new StringBuffer();
		sb.append("WHERE classroom_id = ? ");
		sb.append("AND num_day = ? ");
		sb.append("AND week_start < ? ");
		sb.append("AND week_end > ? ");
		sb.append("OR week_start < ? ");
		sb.append("AND week_end > ? ");
		sb.append("OR week_start > ? ");
		sb.append("AND week_end < ? ");
		sb.append("AND time_start < ? ");
		sb.append("AND time_end > ? ");
		sb.append("OR time_start < ? ");
		sb.append("AND time_end > ? ");
		sb.append("OR time_start > ? ");
		sb.append("AND time_end < ? ");
		
		Object[] whereParams = {classroomId,numDay,weekStart,weekStart,weekEnd,weekEnd,weekStart,weekEnd,timeStart,timeStart,timeEnd,timeEnd,timeStart,timeEnd};
		System.out.println(sb.toString());
		CourseSchedule courseSchedule = get(conn,sb.toString(), whereParams);
		if(courseSchedule != null) {
			return 1;  //返回1为表示在这个时间点有人上课
		}else {
			return 0;//返回0表示在这个时间点没有人上课
		}
	}


	public List<CourseSchedule> getCourseSchedule(DruidPooledConnection conn, Long classroomId, String bulidNum) {
		
		
		
		return null;
	}


}
