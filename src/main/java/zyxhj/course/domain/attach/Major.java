package zyxhj.course.domain.attach;

import zyxhj.utils.data.rds.RDSAnnEntity;
import zyxhj.utils.data.rds.RDSAnnField;
import zyxhj.utils.data.rds.RDSAnnID;

/**
 * 专业
 */
@RDSAnnEntity(alias = "tb_major")
public class Major {

	/**
	 * 专业id
	 */
	@RDSAnnID
	@RDSAnnField(column = RDSAnnField.ID)
	public Long id;
	
	/**
	 * 专业名称
	 */
	@RDSAnnField(column = RDSAnnField.SHORT_TEXT)
	public String majorName;
	
	/**
	 * 系部id
	 */
	@RDSAnnField(column = RDSAnnField.ID)
	public Long deptId;
	
	/**
	 * 学院id
	 */
	@RDSAnnField(column = RDSAnnField.ID)
	public Long institutionId;
	
	/**
	 * 院校id
	 */
	@RDSAnnField(column = RDSAnnField.ID)
	public Long collageId;
}
