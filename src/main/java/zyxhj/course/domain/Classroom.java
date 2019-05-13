package zyxhj.course.domain;

import zyxhj.utils.data.rds.RDSAnnEntity;
import zyxhj.utils.data.rds.RDSAnnField;
import zyxhj.utils.data.rds.RDSAnnID;

/**
 * @author 16436
 *
 */
@RDSAnnEntity(alias = "tb_classroom")
public class Classroom {

	/**
	 * 教室id
	 */
	@RDSAnnID
	@RDSAnnField(column = RDSAnnField.ID)
	public Long ClassroomId;
	
	/**
	 * 教室名
	 */
	@RDSAnnField(column = RDSAnnField.TEXT_NAME)
	public String classroomName;
	
	/**
	 * 楼号
	 */
	@RDSAnnField(column = RDSAnnField.SHORT_TEXT)
	public String buildNum;
	
	/**
	 * 实验室类型，用来做什么实验的，与课程相对应
	 */
	
	@RDSAnnField(column = RDSAnnField.SHORT_TEXT)
	public String Purpose;
	
	/**
	 * 实验室类型负责人
	 */
	@RDSAnnField(column = RDSAnnField.TEXT_NAME)
	public String Person;
	
}
