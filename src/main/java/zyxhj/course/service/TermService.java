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
import zyxhj.utils.api.ServerException;

public class TermService {
	private static Logger log = LoggerFactory.getLogger(TermService.class);

	private TermRepository termRepository;

	public TermService() {
		try {
			termRepository = Singleton.ins(TermRepository.class);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	// 创建学期
	public Term createTerm(DruidPooledConnection conn, String termName, Integer weekCount, Date startDate, Date endDate,
			String remark) throws Exception {

		// INSERT INTO table_name (列1, 列2,...) VALUES (值1, 值2,....)
		// 或者 INSERT INTO 表名称 VALUES (值1, 值2,....)

		// 1，将传过来的学期信息放到类中
		Term term = new Term();
		term.id = IDUtils.getSimpleId();
		term.name = termName;
		term.weekCount = weekCount;
		term.startDate = startDate;
		term.endDate = endDate;
		term.remark = remark;
		term.createTime = new Date();
		// new Date()获取当前系统时间
		// 2，执行添加操作
		termRepository.insert(conn, term);
		return term;
	}

	// 创建学期
	public Term editTerm(DruidPooledConnection conn, Long termId, String termName, Integer weekCount, Date startDate,
			Date endDate, String remark) {
		// 1，将传过来的学期信息放到类中 注：此处需要传学期id 通过学期id修改数据

		// 2，执行修改操作
		return null;
	}

	// 创建学期
	public int delTerm(DruidPooledConnection conn, Long termId) {
		// 1，执行删除操作
		return 0;
	}

	// 创建学期
	public List<Term> getTerms(DruidPooledConnection conn, Integer count, Integer offset) throws Exception {
		// 1:执行查询操作，查询时加入分页
		return termRepository.getList(conn, count, offset);
	}

}
