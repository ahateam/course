package zyxhj.course.service;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.druid.pool.DruidPooledConnection;

import zyxhj.course.domain.attach.Dept;
import zyxhj.course.domain.attach.Major;
import zyxhj.course.repository.DepartRepository;
import zyxhj.course.repository.MajorRepository;
import zyxhj.utils.Singleton;

public class DeptService {
	private static Logger log = LoggerFactory.getLogger(DeptService.class);

	private DepartRepository departRepository;
	private MajorRepository majorRepository;

	public DeptService() {
		try {
			departRepository = Singleton.ins(DepartRepository.class);
			majorRepository = Singleton.ins(MajorRepository.class);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	
	/**
	 * 添加系部
	 * @param conn
	 * @param deptId
	 * @param deptName
	 * @param collageId 
	 * @param institutionId 
	 * @param nstitutionId
	 * @return
	 */
	public Dept addDept(DruidPooledConnection conn,Long deptId,String deptName, Long institutionId, Long collageId) throws Exception{
		
		Dept d = new Dept();
		d.id = deptId;
		d.departNmae = deptName;
		d.institutionId = institutionId;
		d.collageId = collageId;
		departRepository.insert(conn, d);
		return d;
	}

	/**
	 * 删除系部
	 * @param conn
	 * @param deptId
	 */
	public void deleteDept(DruidPooledConnection conn,Long deptId) throws Exception {
		departRepository.deleteByKey(conn, "id", deptId);
	}
	
	/**
	 * 修改系部
	 * @param conn
	 * @param deptId
	 */
	public Dept upDept(DruidPooledConnection conn,Long deptId,String deptName, Long institutionId, Long collageId) throws Exception{
		Dept d = new Dept();
		d.id = deptId;
		d.departNmae = deptName;
		d.institutionId = institutionId;
		d.collageId = collageId;
		departRepository.updateByKey(conn, "id", deptId, d, true);
		return d;
	}
	
	/**
	 * 根据系部id查询当前系部下所有专业
	 * @param conn
	 * @param deptId
	 * @return
	 */
	public List<Major> queryMajorById(DruidPooledConnection conn,Long deptId) throws Exception{
		
		return majorRepository.getListByKey(conn, "dept_id", deptId, 32, 0);
	}


	public Dept queryDeptById(DruidPooledConnection conn, Long deptId) throws Exception{
		return departRepository.getByKey(conn, "id", deptId);
	}
	
	
	
}

