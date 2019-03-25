package zyxhj.course.domain;

import zyxhj.utils.data.rds.RDSAnnEntity;
import zyxhj.utils.data.rds.RDSAnnField;
import zyxhj.utils.data.rds.RDSAnnID;

/**
 * 课程安排记录
 *
 */
@RDSAnnEntity(alias = "tb_courseSchedule")
public class CourseSchedule {

	@RDSAnnID
	@RDSAnnField(column = RDSAnnField.ID)
	public Long id;

	@RDSAnnField(column = RDSAnnField.ID)
	public Long termId;

	/**
	 * 课程编号
	 */
	@RDSAnnID
	@RDSAnnField(column = RDSAnnField.ID)
	public Long courseId;

	/**
	 * 教室id
	 */
	@RDSAnnField(column = RDSAnnField.ID)
	public Long classroomId;

	/**
	 * 班级信息（可能多个，合班课）
	 */
	@RDSAnnID
	@RDSAnnField(column = RDSAnnField.ID)
	public String classesIds;

	/**
	 * 教师信息（可能多个）
	 */
	@RDSAnnID
	@RDSAnnField(column = RDSAnnField.ID)
	public String teacherIds;
	
	/**
	 * 第几周开始
	 */
	@RDSAnnField(column = RDSAnnField.INTEGER)
	public Integer weekStart;

	/**
	 * 第几周结束
	 */
	@RDSAnnField(column = RDSAnnField.INTEGER)
	public Integer weekEnd;

	/**
	 * 第几天
	 */
	@RDSAnnField(column = RDSAnnField.INTEGER)
	public Integer numDay;

	/**
	 * 起始时间（半小时为最小单位，一天分为48个单位时间）
	 */
	@RDSAnnField(column = RDSAnnField.INTEGER)
	public Integer timeStart;

	/**
	 * 结束时间（半小时为最小单位，一天分为48个单位时间）
	 */
	@RDSAnnField(column = RDSAnnField.INTEGER)
	public Integer timeEnd;
	
	
}
