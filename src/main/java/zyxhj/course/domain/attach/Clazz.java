package zyxhj.course.domain.attach;

import zyxhj.utils.data.rds.RDSAnnField;
import zyxhj.utils.data.rds.RDSAnnID;

/**
 * 班
 */
public class Clazz {

	@RDSAnnID
	@RDSAnnField(column = RDSAnnField.ID)
	public Long id;

	/**
	 * 名称
	 */
	@RDSAnnField(column = RDSAnnField.TEXT_NAME)
	public String name;

	/**
	 * 人数
	 */
	@RDSAnnField(column = RDSAnnField.INTEGER)
	public Integer peopleNum;

}
