package zyxhj.course.domain.attach;

import zyxhj.utils.data.rds.RDSAnnEntity;
import zyxhj.utils.data.rds.RDSAnnField;
import zyxhj.utils.data.rds.RDSAnnID;

/**
 * 学院
 */
@RDSAnnEntity(alias = "tb_institution")
public class Institution {

	/**
	 * 学院id
	 */
	@RDSAnnID
	@RDSAnnField(column = RDSAnnField.ID)
	public Long Id;
	
	/**
	 * 学院名称
	 */
	@RDSAnnField(column = RDSAnnField.SHORT_TEXT)
	public String institutionName;
	
	/**
	 * 院校id
	 */
	@RDSAnnField(column = RDSAnnField.ID)
	public Long collageId;
	
}
