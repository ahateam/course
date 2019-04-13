package zyxhj.course.domain;

import java.util.Date;

import zyxhj.utils.data.rds.RDSAnnEntity;
import zyxhj.utils.data.rds.RDSAnnField;
import zyxhj.utils.data.rds.RDSAnnID;

/**
 * 课程安排记录
 *
 */
@RDSAnnEntity(alias = "tb_schedule_record")
public class CourseScheduleRecord {

	@RDSAnnID
	@RDSAnnField(column = RDSAnnField.ID)
	public Long id;

	@RDSAnnField(column = RDSAnnField.ID)
	public Long termId;

	/**
	 * 课程编号
	 */
	@RDSAnnField(column = RDSAnnField.ID)
	public Long courseId;

	/**
	 * 教室id
	 */
	@RDSAnnField(column = RDSAnnField.ID)
	public Long classroomId;

	/**
	 * 教师信息（可能多个）
	 */
	@RDSAnnField(column = RDSAnnField.ID)
	public String teacherIds;

	/**
	 * 日期
	 */
	@RDSAnnField(column = RDSAnnField.TIME)
	public Date date;

	/**
	 * 开始时间（将来可以考虑按时间段去做，不用date型会比较方便）
	 */
	@RDSAnnField(column = RDSAnnField.TIME)
	public Date timeStart;

	/**
	 * 结束时间（将来可以考虑按时间段去做，不用date型会比较方便）
	 */
	@RDSAnnField(column = RDSAnnField.TIME)
	public Date timeEnd;

}
