package zyxhj.course.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.druid.pool.DruidPooledConnection;

import zyxhj.course.domain.Term;

public class TermService {
	private static Logger log = LoggerFactory.getLogger(TermService.class);

	public TermService() {
		try {
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	// 创建学期
	public Term createTerm(DruidPooledConnection conn, String termName, Integer weekCount, Date startDate, Date endDate,
			String remark) {
		// 1，将传过来的学期信息放到类中

		// new Date()获取当前系统时间
		// 2，执行添加操作
		return null;
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
	public List<Term> getTerms(DruidPooledConnection conn, Integer count, Integer offset) {
		// 1:执行查询操作，查询时加入分页
		return null;
	}

}
