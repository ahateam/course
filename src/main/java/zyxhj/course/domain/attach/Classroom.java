package zyxhj.course.domain.attach;

import zyxhj.utils.data.rds.RDSAnnEntity;
import zyxhj.utils.data.rds.RDSAnnField;
import zyxhj.utils.data.rds.RDSAnnID;

@RDSAnnEntity(alias = "tb_classroom")
public class Classroom {

	/**
	 * 教室id
	 */
	@RDSAnnID
	@RDSAnnField(column = RDSAnnField.ID)
	public Long Id;
	
	/**
	 * 教室名
	 */
	@RDSAnnField(column = RDSAnnField.SHORT_TEXT)
	public String classroomName;
	
	/**
	 * 楼号
	 */
	@RDSAnnField(column = RDSAnnField.SHORT_TEXT)
	public String buildNum;
}
