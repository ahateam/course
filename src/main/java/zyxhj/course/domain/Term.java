package zyxhj.course.domain;

import java.util.Date;

import zyxhj.utils.data.rds.RDSAnnEntity;
import zyxhj.utils.data.rds.RDSAnnField;
import zyxhj.utils.data.rds.RDSAnnID;


/**
 * @author 16436
 * 学期安排，创建学期，初始化排课过程可考虑将大纲界面与该界面合并也，减少多余界面，增加体验
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
	@RDSAnnField(column = RDSAnnField.SHORT_TEXT)
	public String name;

	/**
	 * 总共有多少周
	 */
	@RDSAnnField(column = RDSAnnField.INTEGER)
	public Integer weekCount;

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
	 * 创建时间
	 */
	@RDSAnnField(column = RDSAnnField.TIME)
	public Date createTime;

	/**
	 * 备注
	 */
	@RDSAnnField(column = RDSAnnField.SHORT_TEXT)
	public String remark;

}
