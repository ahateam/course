package zyxhj.course.domain;

import zyxhj.utils.data.rds.RDSAnnEntity;
import zyxhj.utils.data.rds.RDSAnnField;
import zyxhj.utils.data.rds.RDSAnnID;


/**
 * @author 16436
 * 院系实体类，主要用于教务处管理各院系和实验室。
 *
 */

@RDSAnnEntity(alias = "tb_department")
public class Department {

	
	@RDSAnnID
	@RDSAnnField(column = RDSAnnField.ID)
	public Long collegeId;

	@RDSAnnField(column = RDSAnnField.TEXT_NAME)
	public String name;
}
