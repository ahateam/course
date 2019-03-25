package zyxhj.course.domain.attach;

import zyxhj.utils.data.rds.RDSAnnEntity;
import zyxhj.utils.data.rds.RDSAnnField;
import zyxhj.utils.data.rds.RDSAnnID;

@RDSAnnEntity(alias = "tb_course")
public class Course {

	/**
	 * 课程id
	 */
	@RDSAnnID
	@RDSAnnField(column = RDSAnnField.ID)
	public Long Id;
	
	/**
	 * 课程名称
	 */
	@RDSAnnField(column = RDSAnnField.SHORT_TEXT)
	public String courseName;
	
	/**
	 * 学识
	 */
	@RDSAnnField(column = RDSAnnField.FLOAT)
	public Long knowledge;
	
	/**
	  * 所属专业id
	 */
	@RDSAnnField(column = RDSAnnField.ID)
	public Long majorId;
	
	/**
	 * 教师id
	 */
	@RDSAnnField(column = RDSAnnField.ID)
	public Long teacherId;
	
	/**
	 * 部门id
	 */
	@RDSAnnField(column = RDSAnnField.ID)
	public Long depatId;
	
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
}
