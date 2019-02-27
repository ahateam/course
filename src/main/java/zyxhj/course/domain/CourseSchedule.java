package zyxhj.course.domain;

/**
 * 课程安排记录
 *
 */
public class CourseSchedule {

	public Long id;

	public Long termId;

	/**
	 * 课程编号
	 */
	public Long courseId;

	/**
	 * 教室信息
	 */
	public String classroom;

	/**
	 * 班级信息（可能多个，合班课）
	 */
	public String classesIds;

	/**
	 * 教师信息（可能多个）
	 */
	public String teacherIds;

	/**
	 * 第几周开始
	 */
	public Integer weekStart;

	/**
	 * 第几周结束
	 */
	public Integer weekEnd;

	/**
	 * 第几天
	 */
	public Integer numDay;

	/**
	 * 起始时间（半小时为最小单位，一天分为48个单位时间）
	 */
	public Integer timeStart;

	/**
	 * 结束时间（半小时为最小单位，一天分为48个单位时间）
	 */
	public Integer timeEnd;
}
