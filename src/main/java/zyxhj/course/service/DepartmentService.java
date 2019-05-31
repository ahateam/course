package zyxhj.course.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.druid.pool.DruidPooledConnection;

import zyxhj.course.domain.Department;
import zyxhj.course.domain.Term;

public class DepartmentService {
	private static Logger log = LoggerFactory.getLogger(DepartmentService.class);

	public DepartmentService() {
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

	public Department createDepartment(DruidPooledConnection conn,String name) {
		//insert into tb_department(xx,xx,xx) VALUES(?,?,?)
		// 1，将传过来的学期信息放到类中
		
		// 2，执行添加操作
		return null;
	}
	
	public Department editDepartment(DruidPooledConnection conn,Long id,String name) {
		// 1，将传过来的学期信息放到类中
		
		// 2，执行修改操作
		return null;
	}
	
	public Department delDepartment(DruidPooledConnection conn,Long id) {
		// 1，执行删除操作
		
		return null;
	}
	
	public List<Department> getDepartment(DruidPooledConnection conn,Integer count,Integer offset){
		//分页查询院系
		//SELECT * FROM tb_department LIMIT 10 OFFSET 0
		return null;
	}
	
}
