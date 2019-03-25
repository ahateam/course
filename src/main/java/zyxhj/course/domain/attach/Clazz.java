package zyxhj.course.domain.attach;

import zyxhj.utils.data.rds.RDSAnnEntity;
import zyxhj.utils.data.rds.RDSAnnField;
import zyxhj.utils.data.rds.RDSAnnID;

@RDSAnnEntity(alias = "tb_clazz")
public class Clazz {

	/**
	 * 班级id
	 */
	@RDSAnnID
	@RDSAnnField(column = RDSAnnField.ID)
	public Long Id;
	
	/**
	 * 班级名称
	 */
	@RDSAnnField(column = RDSAnnField.SHORT_TEXT)
	public String clazzName;
	
	/**
	 * 年级
	 */
	@RDSAnnField(column = RDSAnnField.SHORT_TEXT)
	public String grade;
	
	/**
	 * 专业id
	 */
	@RDSAnnField(column = RDSAnnField.ID)
	public Long majorId;
	
	/**
	 * 教师id
	 */
	@RDSAnnField(column = RDSAnnField.ID)
	public Long teacherId;
	
	/**
	 * 系部id
	 */
	@RDSAnnField(column = RDSAnnField.ID)
	public Long deptId;
	
	/**
	 * 学院id
	 */
	@RDSAnnField(column = RDSAnnField.ID)
	public Long instiutionId;
	
	/**
	 * 院校id
	 */
	@RDSAnnField(column = RDSAnnField.ID)
	public Long collageId;
}
