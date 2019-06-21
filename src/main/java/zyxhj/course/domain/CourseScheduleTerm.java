package zyxhj.course.domain;

import zyxhj.utils.data.rds.RDSAnnEntity;
import zyxhj.utils.data.rds.RDSAnnField;
import zyxhj.utils.data.rds.RDSAnnID;

@RDSAnnEntity(alias = "tb_course_schedule_term")
public class CourseScheduleTerm {

	@RDSAnnID
	@RDSAnnField(column = RDSAnnField.ID)
	public Long id;

	@RDSAnnField(column = RDSAnnField.ID)
	public Long termId;

	/**
	 * 教室编号
	 */
	@RDSAnnField(column = RDSAnnField.TEXT_NAME)
	public String classroomId;

	/**
	 * 班级信息（班级档期比较重要）
	 */
	@RDSAnnField(column = RDSAnnField.SHORT_TEXT)
	public String classId;

	/**
	 * 课程代码
	 */
	@RDSAnnField(column = RDSAnnField.TEXT_NAME)
	public String courseNo;

	/**
	 * 课程名称
	 */
	@RDSAnnField(column = RDSAnnField.TEXT_NAME)
	public String courseName;

	/**
	 * 教师信息（可能多个）
	 */
	@RDSAnnField(column = RDSAnnField.SHORT_TEXT)
	public String teachers;

	/**
	 * 第几周
	 */
	@RDSAnnField(column = RDSAnnField.BYTE)
	public Byte weekNum;

	/**
	 * 周几
	 */
	@RDSAnnField(column = RDSAnnField.BYTE)
	public Byte weekTime;

	/**
	 * 第几节课
	 */
	@RDSAnnField(column = RDSAnnField.BYTE)
	public Byte lessonNum;
}
