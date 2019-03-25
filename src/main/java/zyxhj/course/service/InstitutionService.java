package zyxhj.course.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.druid.pool.DruidPooledConnection;

import zyxhj.course.domain.attach.Institution;
import zyxhj.course.repository.InstitutionRepository;
import zyxhj.utils.IDUtils;
import zyxhj.utils.Singleton;

public class InstitutionService {
	private static Logger log = LoggerFactory.getLogger(InstitutionService.class);

	private InstitutionRepository institutionRepository;
	
	public InstitutionService() {
		try {
			institutionRepository = Singleton.ins(InstitutionRepository.class);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	/**
	 * 添加院系
	 * @param conn
	 * @param institutionId
	 * @param institutionName
	 * @param colleageId
	 * @return
	 */
	public Institution addInstitution(DruidPooledConnection conn,String institutionName,Long collageId) throws Exception{
		Institution i = new Institution();
		
		i.Id = IDUtils.getSimpleId();
		i.institutionName = institutionName;
			i.collageId = collageId;
			institutionRepository.insert(conn, i);
		
		return i;
	}
	
	/**
	 * 删除院系
	 * @param conn
	 * @param institutionId
	 */
	public void deleteInstitution(DruidPooledConnection conn,Long institutionId) throws Exception{
		institutionRepository.deleteByKey(conn, "id", institutionId);
	}
	
	/**
	 * 更新院系
	 * @param conn
	 * @param institutionId
	 * @param institutionName
	 * @param colleageId
	 */
	public Institution upInstitution(DruidPooledConnection conn,Long institutionId,String institutionName,Long collageId) throws Exception{
		
		Institution i = new Institution();
		i.Id = institutionId;
		i.institutionName = institutionName;
			i.collageId = collageId;
		institutionRepository.updateByKey(conn, "id", institutionId, i, true);
		return i;
	}

	/**
	 * 根据id查询院系信息
	 * @param conn
	 * @param institutionId
	 * @return
	 */
	public Institution queryById(DruidPooledConnection conn,Long institutionId) throws Exception{
		return institutionRepository.getByKey(conn, "id", institutionId);
	}
	
	
}
