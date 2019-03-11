package zyxhj.course.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.druid.pool.DruidPooledConnection;

import zyxhj.course.domain.attach.Clazz;
import zyxhj.course.repository.ClazzRepository;
import zyxhj.course.repository.TeacherRepository;
import zyxhj.utils.Singleton;

public class ClazzService {
	private static Logger log = LoggerFactory.getLogger(ClazzService.class);

	private  ClazzRepository clazzRepository;
	private TeacherRepository teacherRepository;
	public ClazzService() {
		try {
			clazzRepository = Singleton.ins(ClazzRepository.class);
			teacherRepository = Singleton.ins(TeacherRepository.class);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	/**
	 * 添加班级
	 * 
	 * @param conn
	 * @param clazzName
	 * @param grade 
	 * @param majorId
	 * @param teacherId
	 * @param collageId 
	 * @param instiutionId 
	 * @return
	 */
	public Clazz addClazz(DruidPooledConnection conn, Long clazzId, String clazzName, String grade, Long majorId,
			Long teacherId,Long departId, Long instiutionId, Long collageId) throws Exception{
		Clazz c = new Clazz();
		c.Id = clazzId;
		c.clazzName = clazzName;
		c.grade = grade;
		c.majorId = majorId;
			if(null != teacherRepository.getByKey(conn, "id", teacherId)) {
			c.teacherId = teacherId;
			}
			c.deptId = departId;
			c.instiutionId = instiutionId;
			c.collageId = collageId;
		clazzRepository.insert(conn, c);
		return c;
	}

	/**
	 * 删除班级
	 * 
	 * @param conn
	 * @param clazzId
	 */
	public void deleteClazz(DruidPooledConnection conn, Long clazzId) throws Exception{
		clazzRepository.deleteByKey(conn, "id", clazzId);
	}

	/**
	 * 修改班级信息
	 * 
	 * @param conn
	 * @param clazzId
	 * @param clazzName
	 * @param grade 
	 * @param majorId
	 * @param teacherId
	 * @param collageId 
	 * @param instiutionId 
	 * @param departId 
	 */
	public Clazz upCLazz(DruidPooledConnection conn, Long clazzId, String clazzName, String grade, Long majorId,
			Long teacherId, Long departId, Long instiutionId, Long collageId) throws Exception{
		Clazz c = new Clazz();
		c.Id = clazzId;
		c.clazzName = clazzName;
		c.grade = grade;
			c.majorId = majorId;
			if(null != teacherRepository.getByKey(conn, "id", teacherId)) {
			c.teacherId = teacherId;
			}
			c.deptId = departId;
			c.instiutionId = instiutionId;
			c.collageId = collageId;
		clazzRepository.updateByKey(conn, "id", clazzId, c, true);
		return c;
	}

	public Clazz queryClazzById(DruidPooledConnection conn, Long clazzId) throws Exception{
		return clazzRepository.getByKey(conn, "id", clazzId);
	}
	
	
	
	public List<Clazz> queryCLazzBygrade(DruidPooledConnection conn,Long institutionId,Long deptId,Long majorId,String grade,Integer count,Integer offset) throws Exception {
		
		ArrayList<Object> obj = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer(" WHERE ");
		
		if(null != institutionId) {
			sb.append(" institution_id = ? ");
			obj.add(institutionId);
		}
		if(null != deptId) {
			sb.append(" dept_id = ? ");
			obj.add(deptId);
		}
		if(null != majorId) {
			sb.append(" major_id = ? ");
			obj.add(majorId);
		}
		sb.append(" grade = ? ");
		obj.add(grade);
		
		return clazzRepository.getListByKey(conn, sb.toString(), obj.toArray(), count, offset);
	}
}
