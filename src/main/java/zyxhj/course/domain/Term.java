package zyxhj.course.domain;

import java.util.Date;

import zyxhj.utils.data.rds.RDSAnnEntity;
import zyxhj.utils.data.rds.RDSAnnField;
import zyxhj.utils.data.rds.RDSAnnID;

/**
  * 学期安排
 *
 */
@RDSAnnEntity(alias = "tb_term")
public class Term {

	/**
	 * 学期id
	 */
	@RDSAnnID
	@RDSAnnField(column = RDSAnnField.ID)
	public Long id;

	/**
	 * 第几学期
	 */
	@RDSAnnField(column = RDSAnnField.SHORT)
	public String name;
	
	/**
	 * 学期开始时间
	 */
	@RDSAnnField(column = RDSAnnField.TIME)
	public Date startDate;

	/**
	 * 学期结束时间
	 */
	@RDSAnnField(column = RDSAnnField.TIME)
	public Date endDate;

	/**
	 * 备注
	 */
	@RDSAnnField(column = RDSAnnField.TEXT)
	public String remark;
	

}
