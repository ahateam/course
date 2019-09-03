package zyxhj.course.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.druid.pool.DruidPooledConnection;

import zyxhj.course.domain.Term;
import zyxhj.course.repository.TermRepository;
import zyxhj.utils.IDUtils;
import zyxhj.utils.Singleton;
import zyxhj.utils.data.EXP;

/**
 * @author 16436
 *
 */
public class TermService {
	
	private static Logger log = LoggerFactory.getLogger(TermService.class);
	private TermRepository termRepository;
	
	public TermService(){
		try {
			termRepository = Singleton.ins(TermRepository.class);	
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}
	
	/**
	 * 创建学期
	 * @param conn
	 * @param termName
	 * @param weekCount
	 * @param startDate
	 * @param endDate
	 * @param createTime
	 * @param remark
	 * @return
	 * @throws Exception
	 */
	public Term createTerm(DruidPooledConnection conn,String termName,Integer weekCount,Date startDate,Date endDate, Date createTime, String remark) throws Exception{
		Term term = new Term();
		
		term.id = IDUtils.getSimpleId();
		term.name = termName;
		term.weekCount = weekCount;
		term.startDate = startDate;
		term.endDate = endDate;
		term.remark = remark;
		createTime = new Date();
		
		 termRepository.insert(conn, term);
		return term;
				
	}
	
	/**
	 * 通过学期ID编辑学期
	 * @param conn
	 * @param Id
	 * @param termName
	 * @param weekCount
	 * @param startDate
	 * @param endDate
	 * @param createTime
	 * @param remark
	 * @return
	 * @throws Exception
	 */
	public Term editTerm(DruidPooledConnection conn,Long termId,String termName,Integer weekCount,Date startDate,Date endDate, Date createTime, String remark) throws Exception{
		Term term = new Term();
		
		term.name = termName;
		term.weekCount = weekCount;
		term.startDate = startDate;
		term.endDate = endDate;
		term.remark = remark;
		createTime = new Date();
		
//		termRepository.updateByKey(conn, "id", termId, term, true);
		termRepository.update(conn, EXP.INS().key("id", termId), term, true);
		return term;
	}
	
	/**
	 * 根据Id查询学期
	 * @param conn
	 * @param termId
	 * @return
	 * @throws Exception
	 */
	public Term getTerm(DruidPooledConnection conn,Long termId) throws Exception{
		
//		return termRepository.getByKey(conn, "id", termId);
		return termRepository.get(conn, EXP.INS().key("id", termId));
	}
	
	
	/**
	 * 分页查询
	 * @param conn
	 * @param count
	 * @param offset
	 * @return
	 * @throws Exception
	 */
	public List<Term> queryTerm(DruidPooledConnection conn, Integer count, Integer offset) throws Exception{
		
//		return termRepository.getList(conn, count, offset);
		return termRepository.getList(conn, null, count, offset);
	}
	
	/**
	 * 
	 * @param conn
	 * @param termId
	 * @throws Exception
	 */
	public void delTerm(DruidPooledConnection conn,Long termId) throws Exception{
		
//		termRepository.deleteByKey(conn, "id", termId);
		termRepository.delete(conn, EXP.INS().key("id", termId));
	}
}
