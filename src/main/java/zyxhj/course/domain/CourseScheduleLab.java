package zyxhj.course.domain;

import zyxhj.utils.data.rds.RDSAnnEntity;
import zyxhj.utils.data.rds.RDSAnnField;
import zyxhj.utils.data.rds.RDSAnnID;

/**
 * 课程安排（实验室）
 *
 */
@RDSAnnEntity(alias = "tb_course_schedule_lab")
public class CourseScheduleLab {

	@RDSAnnID
	@RDSAnnField(column = RDSAnnField.ID)
	public Long id;

	@RDSAnnField(column = RDSAnnField.ID)
	public Long termId;
	
	/**
	 * 所属大纲id
	 */
	@RDSAnnField(column = RDSAnnField.ID)
	public Long courseOutlineId;

	/**
	 * 课程编号
	 */
	@RDSAnnField(column = RDSAnnField.ID)
	public Long courseId;

	/**
	 * 课程名称
	 */
	@RDSAnnField(column = RDSAnnField.TEXT_NAME)
	public Long courseName;

	/**
	 * 教室id
	 */
	@RDSAnnField(column = RDSAnnField.ID)
	public Long classroomId;

	/**
	 * 教师编号（可能多个）
	 */
	@RDSAnnField(column = RDSAnnField.SHORT_TEXT)
	public String teacherIds;

	/**
	 * 班级编号（可能多个）
	 */
	@RDSAnnField(column = RDSAnnField.SHORT_TEXT)
	public String classIds;

	/**
	 * 第几周
	 */
	@RDSAnnField(column = RDSAnnField.BYTE)
	public Byte weekNum;

	/**
	 * 第几节课
	 */
	public Byte lessonNum;

}
