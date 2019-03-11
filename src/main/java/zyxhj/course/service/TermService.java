package zyxhj.course.service;


import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.druid.pool.DruidPooledConnection;

import zyxhj.course.domain.Term;
import zyxhj.course.repository.TermRepository;
import zyxhj.utils.Singleton;

public class TermService {
	private static Logger log = LoggerFactory.getLogger(TermService.class);

	// private ChannelUserRepository channelUserRepository;
	//private static CourseRepository courseRepository;
	private  TermRepository termRepository;

	public TermService() {
		try {
			// channelUserRepository = Singleton.ins(ChannelUserRepository.class);
			//courseRepository = Singleton.ins(CourseRepository.class);
			termRepository = Singleton.ins(TermRepository.class);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}


	
	/**
	 * 添加学期
	 * @param conn
	 * @param classroomId
	 * @param name
	 * @param startDate
	 * @param endDate
	 * @param remark
	 * @return
	 */
	public Term addTerm(DruidPooledConnection conn,Long termId,String termName,Date startDate,Date endDate,String remark) throws Exception{
	
		Term t = new Term();
		t.id = termId;
		t.name = termName;
		t.startDate = startDate;
		t.endDate = endDate;
		t.remark = remark;
		termRepository.insert(conn,t);
		return t;
	}
	
	/**
	 * 删除学期
	 * @param conn
	 * @param classroomId
	 */
	public void deleteTerm(DruidPooledConnection conn,Long termId) throws Exception{
		termRepository.deleteByKey(conn, "id", termId);
	}
	
	/**
	 * 修改学期
	 * @param conn
	 * @param classroomId
	 * @param name
	 * @param startDate
	 * @param endDate
	 * @param remark
	 */
	public void upTerm(DruidPooledConnection conn,Long termId,String termName,Date startDate,Date endDate,String remark) throws Exception{
		Term t = new Term();
		t.id = termId;
		t.name = termName;
		t.startDate = startDate;
		t.endDate = endDate;
		t.remark = remark;
		termRepository.updateByKey(conn, "id", termId, t, true);
	}
	
	/**
	 * 根据id查询学期
	 * @param conn
	 * @param termId
	 * @return
	 */
	public Term queryTermById(DruidPooledConnection conn,Long termId) throws Exception{
		return termRepository.getByKey(conn, "id", termId);
	}
	
}













