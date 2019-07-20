package zyxhj.course.domain;

import zyxhj.utils.data.rds.RDSAnnEntity;
import zyxhj.utils.data.rds.RDSAnnField;
import zyxhj.utils.data.rds.RDSAnnID;

@RDSAnnEntity(alias = "tb_college_open")
public class collegeOpen {
	
	@RDSAnnID
	@RDSAnnField(column = RDSAnnField.ID)
	public Long collegeId;
	
	@RDSAnnField(column = "VARCHAR(30)")
	public String courseName;
	
	@RDSAnnField(column = RDSAnnField.ID)
	public Long courseCode;
	
	@RDSAnnField(column = "VARCHAR(30)")
	public String collegeName;
	
	@RDSAnnField(column = "VARCHAR(30)")
	public String collegeOpenExamStatus;
}
