package zyxhj.course.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.druid.pool.DruidPooledConnection;

import zyxhj.course.domain.Department;
import zyxhj.course.repository.DepartmentRepository ;
import zyxhj.utils.IDUtils;
import zyxhj.utils.Singleton;
import zyxhj.utils.api.ServerException;

public class DepartmentService {
	private static Logger log = LoggerFactory.getLogger(DepartmentService.class);

	private DepartmentRepository departmentRepository;

	public DepartmentService() {
		try {
			departmentRepository = Singleton.ins(DepartmentRepository.class);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}
	

	/**
	 * 
	 * @param conn
	 * @param collegeName
	 * @return
	 * @throws Exception
	 */
	public Department createDepartment (DruidPooledConnection conn, String collegeName) throws Exception {
		Department department = new Department();
		department.collegeId = IDUtils.getSimpleId();
		department.collegeName = collegeName;
		
		departmentRepository.insert(conn, department);

		return department;
		
	}
	
	
	/**
	 * 
	 * @param conn
	 * @param collegeID
	 * @throws Exception
	 */
	public void delDepartment (DruidPooledConnection conn, Long collegeId)throws Exception{
		Department department = new Department();
		department.collegeId =collegeId;
		
		departmentRepository.deleteByKey(conn, "college_id", collegeId);
	} 
	
	
	/**
	 * 
	 * @param conn
	 * @param collegeID
	 * @param collegeName
	 * @return
	 * @throws Exception
	 */
	public Department editDepartment (DruidPooledConnection conn, Long collegeId, String collegeName)throws Exception {
		Department department = new Department();
		department.collegeName =collegeName;
		
		 departmentRepository.updateByKey(conn, "college_id", collegeId, department, true);
		 return department;
	}
	
	
	
	/**
	 * 
	 * @param conn
	 * @param collegeName
	 * @return
	 * @throws Exception
	 */
	public Department getDepartment(DruidPooledConnection conn, Long collegeId )throws Exception {
		
		return departmentRepository.getByKey(conn,"college_id",collegeId,"college_name");
	}
	
	

	public int gDepartment(int count , int offset )throws Exception {
		int a = count + offset;
		return a;
	}
	
	
	/**
	 * 
	 * @param conn
	 * @param count
	 * @param offset
	 * @return
	 * @throws Exception
	 */
	public List<Department> getDepartment(DruidPooledConnection conn,Integer count,Integer offset)throws ServerException{
		return departmentRepository.getList(conn, count, offset);
	}

}
