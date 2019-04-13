package zyxhj.course.domain;

import zyxhj.utils.data.rds.RDSAnnEntity;
import zyxhj.utils.data.rds.RDSAnnField;
import zyxhj.utils.data.rds.RDSAnnID;

/**
 * 院校
 */
@RDSAnnEntity(alias = "tb_collage")
public class Collage {
	
	/**
	 * 院校id
	 */
	@RDSAnnID
	@RDSAnnField(column = RDSAnnField.ID)
	public Long Id;
	
	/**
	 * 院校名称
	 */
	@RDSAnnField(column = RDSAnnField.SHORT_TEXT)
	public String collageName;
	
	
	
}
