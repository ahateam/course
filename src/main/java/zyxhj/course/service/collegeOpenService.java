package zyxhj.course.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.druid.pool.DruidPooledConnection;

import zyxhj.course.domain.Department;
import zyxhj.course.domain.collegeOpen;
import zyxhj.course.repository.collegeOpenRepository;
import zyxhj.utils.IDUtils;
import zyxhj.utils.Singleton;
import zyxhj.utils.api.ServerException;

public class collegeOpenService {
	private static Logger log = LoggerFactory.getLogger(collegeOpenService.class);

	private collegeOpenRepository collegeOpenRepository;

	public collegeOpenService() {
		try {
			collegeOpenRepository = Singleton.ins(collegeOpenRepository.class);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}
	
	/**
	 * 创建课程
	 * @param conn
	 * @param courseName
	 * @param collegeId
	 * @param collegeName
	 * @param collegeOpenExamStatus
	 * @return
	 * @throws Exception
	 */
	public collegeOpen createCollegeOpen (DruidPooledConnection conn,String courseName,Long collegeId,String collegeName,String collegeOpenExamStatus ) throws Exception{
		collegeOpen cOpen = new collegeOpen();
		cOpen.courseCode = IDUtils.getSimpleId();
		cOpen.courseName = courseName;
		cOpen.collegeId = collegeId;
		cOpen.collegeName = collegeName;
		
		collegeOpenRepository.insert(conn, cOpen);
		
		return cOpen;
	}
	
	/**
	 * 删除
	 * @param conn
	 * @param courseCode
	 * @throws Exception
	 */
	public void delCourseOpen (DruidPooledConnection conn, Long courseCode)throws Exception{
		collegeOpen cOpen = new collegeOpen();
		cOpen.courseCode = courseCode;
		
		collegeOpenRepository.deleteByKey(conn, "course_id", courseCode);
	} 
	/**
	 * 分页查询
	 * @param conn
	 * @param count
	 * @param offset
	 * @return
	 * @throws ServerException
	 */
	public List<collegeOpen> getcollegeOpen(DruidPooledConnection conn,Integer count,Integer offset)throws ServerException{
		System.out.println(collegeOpenRepository.getList(conn, count, offset));
		System.out.println("调用成功");
		return collegeOpenRepository.getList(conn, count, offset); 
		  
		  }
	
	public collegeOpen gCollegeOpen(DruidPooledConnection conn, Long courseCode )throws Exception {
		
		return collegeOpenRepository.getByKey(conn,"college_id",courseCode,"course_name");
	}
}
