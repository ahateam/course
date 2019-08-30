package zyxhj.course.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.druid.pool.DruidPooledConnection;

import zyxhj.course.domain.CourseOutline;
import zyxhj.course.domain.Department;
import zyxhj.course.repository.CourseOutlineRepository;
import zyxhj.utils.IDUtils;
import zyxhj.utils.Singleton;
import zyxhj.utils.data.EXP;


public class CourseOutlineService {
	
	private static Logger log = LoggerFactory.getLogger(CourseOutlineService.class);
	
	private CourseOutlineRepository courseOutlineRepository; 
	
	public CourseOutlineService() {
		try {
			courseOutlineRepository = Singleton.ins(CourseOutlineRepository.class);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}
	
	
	/**
	 * 
	 * @param conn
	 * @param name
	 * @param collegeId
	 * @param collegeName
	 * @param termId
	 * @param classHour
	 * @param credit
	 * @param exam
	 * @param elect
	 * @param classIds
	 * @return
	 * @throws Exception
	 */
	public CourseOutline createCourseOutline (DruidPooledConnection conn,String name,Long termId,Integer classHour,Float credit,Byte exam,Byte elect,String classIds ) throws Exception{
		CourseOutline courseOutline = new CourseOutline();
		courseOutline.Id = IDUtils.getSimpleId();
		courseOutline.name = name;
		courseOutline.termId = termId;
		courseOutline.classHour = classHour;//!!
		courseOutline.credit = credit;
		courseOutline.exam = exam;
		courseOutline.elect = elect;
		courseOutline.classIds = classIds;
		
		courseOutlineRepository.insert(conn, courseOutline);
		
		return courseOutline;
	}
	
	/**
	 * 
	 * @param conn
	 * @param Id
	 * @throws Exception
	 */
	public void delCourseOutline (DruidPooledConnection conn, Long Id) throws Exception{
		CourseOutline courseOutline = new CourseOutline();
		courseOutline.Id = Id;
		
//	    courseOutlineRepository.deleteByKey(conn, "id", Id);
	    
	    courseOutlineRepository.delete(conn, EXP.INS().key("id", Id));
	}
	
 
	/**
	 * 
	 * @param conn
	 * @param Id
	 * @param name
	 * @param classHour
	 * @param credit
	 * @param exam
	 * @param elect
	 * @param classIds
	 * @return
	 * @throws Exception
	 */
	public CourseOutline editCourseOutline  (DruidPooledConnection conn,Long Id,String name,Integer classHour,
			Float credit,Byte exam,Byte elect,String classIds ) throws Exception {
		CourseOutline courseOutline = new CourseOutline();
		courseOutline.Id = Id;
		courseOutline.name = name;
		courseOutline.classHour = classHour;
		courseOutline.credit = credit;
		courseOutline.exam = CourseOutline.EXAM.ROLLTEST.v();
		courseOutline.elect = CourseOutline.ELECT.OBLIGATORY.v();
		courseOutline.classIds = classIds;
		
//		courseOutlineRepository.updateByKey(conn, "id", Id, courseOutline, true);
		courseOutlineRepository.update(conn, EXP.INS().key("id", Id), courseOutline, true);
		 return courseOutline;
	}
	
	
	/**
	 * 
	 * @param conn
	 * @param exam
	 * @param count
	 * @param offset
	 * @return
	 * @throws Exception
	 */
	public List<CourseOutline> getCourseOutline  (DruidPooledConnection conn,Byte elect,Integer count,Integer offset) throws Exception {
		CourseOutline courseOutline = new CourseOutline();
		courseOutline.elect = elect;
		if (elect == 0 || elect == 1 || elect == 2) {
//			return courseOutlineRepository.getListByKey(conn,"elect",
//					elect, count, offset);
			return courseOutlineRepository.getList(conn, EXP.INS().key("elect", elect), count, offset);
		} else {
			return null;
		}
		
	}
	
	
	
	/**
	 * 
	 * @param conn
	 * @param collegeName
	 * @param termId
	 * @param count
	 * @param offset
	 * @return
	 * @throws Exception
	 */
	public List<CourseOutline> getCourseOutlineByTermId  (DruidPooledConnection conn,String collegeName,Long termId,
			Integer count,Integer offset) throws Exception {
		CourseOutline courseOutline = new CourseOutline();
		Department department = new Department();
		courseOutline.termId = termId;
		department.collegeName = collegeName;
		
//		return courseOutlineRepository.getListByANDKeys(conn,new String[] {"college_id","term_id"},
//				new Object[] {collegeName,termId}, count, offset);
		return courseOutlineRepository.getList(conn, EXP.INS().key("college_id", collegeName).andKey("term_id", termId), count, offset);
	}
	
	
	
	
}
