package zyxhj.course.domain;

import zyxhj.utils.data.rds.RDSAnnEntity;
import zyxhj.utils.data.rds.RDSAnnField;
import zyxhj.utils.data.rds.RDSAnnID;

/**
 * @author 16436
 * 班级档期，摒弃之前的实体，用更为简洁的导入
 */

@RDSAnnEntity(alias = "tb_class_schedule")
public class ClassScheduleTerm {
	
	/**
	 * 班级Id
	 */
	@RDSAnnID
	@RDSAnnField(column = RDSAnnField.ID)
	public Long id;
	

	
	/**
	 * 所属学院Id，主码
	 */
	@RDSAnnField(column = RDSAnnField.ID)
	public Long collegeID;
	
	/**
	 * 年级
	 */
	@RDSAnnField(column = "VARCHAR(255)")
	public String grade;
	
	@RDSAnnField(column = "VARCHAR(255)")
	public String className;
	
	@RDSAnnField(column = "VARCHAR(255)")
	public String courseType;
	
	/**
	 * 学生数
	 */	
	@RDSAnnField(column = "VARCHAR(255)")
	public String studentNumber;
	
	/**
	 * 上课时间
	 */
	@RDSAnnField(column = RDSAnnField.SHORT_TEXT)
	public String classTime;
}
