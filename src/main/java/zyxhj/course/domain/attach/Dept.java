package zyxhj.course.domain.attach;

import zyxhj.utils.data.rds.RDSAnnEntity;
import zyxhj.utils.data.rds.RDSAnnField;
import zyxhj.utils.data.rds.RDSAnnID;

/**
 * 系(department)
 */
@RDSAnnEntity(alias = "tb_depat")
public class Dept {

	@RDSAnnID
	@RDSAnnField(column = RDSAnnField.ID)
	public Long id;
	
	/**
	 * 系部名称
	 */
	@RDSAnnField(column = RDSAnnField.TEXT)
	public String departNmae;
	
	/**
	 *学院id
	 */
	@RDSAnnField(column = RDSAnnField.ID)
	public Long institutionId;
	
	/**
	 * 院校id
	 */
	@RDSAnnField(column = RDSAnnField.ID)
	public Long collageId;
	
	
}
