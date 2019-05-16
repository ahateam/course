package zyxhj.course.domain;

import zyxhj.utils.data.rds.RDSAnnEntity;
import zyxhj.utils.data.rds.RDSAnnField;

/**
 * 教师档期表 TODO 不完善 测试用
 */
@RDSAnnEntity(alias = "tb_teacher_user")
public class TeacherSchedule {

	@RDSAnnField(column = "VARCHAR(32)")
	public String teacherCode;

	@RDSAnnField(column = RDSAnnField.TEXT_NAME)
	public String teacherName;

	@RDSAnnField(column = "VARCHAR(32)")
	public String courseCode;

	@RDSAnnField(column = RDSAnnField.TEXT_NAME)
	public String courseName;

	@RDSAnnField(column = RDSAnnField.TEXT_NAME)
	public String weekDay;

	@RDSAnnField(column = RDSAnnField.INTEGER)
	public Integer startTime;

	@RDSAnnField(column = RDSAnnField.INTEGER)
	public Integer endTime;

	@RDSAnnField(column = RDSAnnField.INTEGER)
	public Integer startWeek;

	@RDSAnnField(column = RDSAnnField.INTEGER)
	public Integer endWeek;

	@RDSAnnField(column = RDSAnnField.TEXT_TITLE)
	public String classAddress;

	@RDSAnnField(column = RDSAnnField.TEXT_TITLE)
	public String clazz;

}
