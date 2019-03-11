package zyxhj.course.domain.attach;

import zyxhj.utils.data.rds.RDSAnnEntity;
import zyxhj.utils.data.rds.RDSAnnField;
import zyxhj.utils.data.rds.RDSAnnID;

@RDSAnnEntity(alias = "tb_teacher")
public class Teacher {

	/**
	 * 教师id
	 * 
	 */
	@RDSAnnID
	@RDSAnnField(column = RDSAnnField.ID)
	public Long Id;
	
	/**
	 * 教师姓名
	 */
	@RDSAnnField(column = "VARCHAR(40)")
	public String teacherName;

	/**
	 * 教师电话
	 */
	@RDSAnnField(column = "VARCHAR(11)")
	public String teacherPhone;


	/**
	 * 教师邮箱
	 */
	@RDSAnnField(column = RDSAnnField.TEXT)
	public String teacherEmail;
	
	/**
	 * 专业id
	 */
	@RDSAnnField(column = RDSAnnField.ID)
	public Long majorId;
	
	/**
	 *系部id
	 */
	@RDSAnnField(column = RDSAnnField.ID)
	public Long departId;
	
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
	
	/**
	 * 信息
	 */
	@RDSAnnField(column = RDSAnnField.TEXT)
	public String information;
}
