package zyxhj.course.domain;

import zyxhj.utils.data.rds.RDSAnnEntity;
import zyxhj.utils.data.rds.RDSAnnField;
import zyxhj.utils.data.rds.RDSAnnID;

/**
 * 教师档期表 TODO 不完善 测试用
 */
@RDSAnnEntity(alias = "tb_temp_schedule")
public class TempSchedule {

	@RDSAnnID
	@RDSAnnField(column = RDSAnnField.ID)
	public Long id;

	@RDSAnnField(column = "VARCHAR(255)")
	public String grade;

	@RDSAnnField(column = "VARCHAR(255)")
	public String courseCode;

	@RDSAnnField(column = "VARCHAR(255)")
	public String courseNode;

	@RDSAnnField(column = "VARCHAR(255)")
	public String courseNature;

	@RDSAnnField(column = "VARCHAR(255)")
	public String courseType;

	@RDSAnnField(column = "VARCHAR(255)")
	public String credit;

	@RDSAnnField(column = "VARCHAR(255)")
	public String teacherCode;

	@RDSAnnField(column = "VARCHAR(255)")
	public String teacherName;

	@RDSAnnField(column = "VARCHAR(255)")
	public String teacherTitle;

	@RDSAnnField(column = "VARCHAR(255)")
	public String teacherEducation;

	@RDSAnnField(column = "VARCHAR(255)")
	public String teacherAcademic;

	@RDSAnnField(column = "VARCHAR(255)")
	public String studentNumber;

	@RDSAnnField(column = "VARCHAR(255)")
	public String courseSelectionNum;

	@RDSAnnField(column = RDSAnnField.SHORT_TEXT)
	public String classTime;

	@RDSAnnField(column = "VARCHAR(255)")
	public String college;

	@RDSAnnField(column = "VARCHAR(255)")
	public String clazz;

	@RDSAnnField(column = "VARCHAR(255)")
	public String courseSelectionCode;

	@RDSAnnField(column = "VARCHAR(255)")
	public String courseSelectionType;

	@RDSAnnField(column = "VARCHAR(255)")
	public String knowledgeNum;

	@RDSAnnField(column = "VARCHAR(255)")
	public String theoryKnowledgeNum;

	@RDSAnnField(column = "VARCHAR(255)")
	public String experimentKnowledgeNum;

	@RDSAnnField(column = "VARCHAR(255)")
	public String computerKnowledgeNum;

}
