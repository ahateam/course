package zyxhj.course.domain;

import zyxhj.utils.data.rds.RDSAnnEntity;
import zyxhj.utils.data.rds.RDSAnnField;
import zyxhj.utils.data.rds.RDSAnnID;

/**
 * 院系
 *
 */
@RDSAnnEntity(alias = "tb_department")
public class Department {

	@RDSAnnID
	@RDSAnnField(column = RDSAnnField.ID)
	public Long id;

	@RDSAnnField(column = RDSAnnField.TEXT_NAME)
	public String name;
}
