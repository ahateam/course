	package zyxhj.course.domain;

import zyxhj.utils.data.rds.RDSAnnEntity;
import zyxhj.utils.data.rds.RDSAnnField;
import zyxhj.utils.data.rds.RDSAnnID;

/**
 * @author 16436
 * 
 * 课程中，课程大纲名与课程是书与书目录的关系，所以在这里定义了课程大纲Id,以便分清归属问题。
 * 教室信息来自教室实体类
 * 老师信息来自"老师用户类"
 * 所属机构ID来自课程大纲
 *
 */
@RDSAnnEntity(alias = "tb_course") public class Course {

	/**
	 * 课程大纲id
	 */
	@RDSAnnID
	@RDSAnnField(column = RDSAnnField.ID)
	public Long collageId;
	
	/**
	 * 课程id
	 */
	@RDSAnnField(column = RDSAnnField.ID)
	public Long courseId;
	
	/**
	 * 课程名称
	 */
	@RDSAnnField(column = RDSAnnField.SHORT_TEXT)
	public String courseName;
	
	/**
	 * 学时
	 */
	@RDSAnnField(column = RDSAnnField.FLOAT)
	public Long knowledge;
	
	/**
	 * 上课时间
	 */
	@RDSAnnField(column = RDSAnnField.TIME)
	public Long courseTime;
	
	/**
	 * 学院id，留着，但不展示
	 */
	@RDSAnnField(column = RDSAnnField.ID) public Long institutionId;
	
	/**
	  * 所属专业班级id,即所教的专业班级，
	 */
	@RDSAnnField(column = RDSAnnField.ID)
	public Long majorId;
	
	/**
	 * 教师id
	 */
	@RDSAnnField(column = RDSAnnField.ID)
	public Long teacherId;
	
	/**
	 * 老师职称id
	 */
	@RDSAnnField(column = RDSAnnField.ID)
	public Long positionName;
	

	
	/**
	 * 课程类型ID，课带，独立，混合
	 */
	@RDSAnnField(column = RDSAnnField.ID)
	public int typeId;
	
}
