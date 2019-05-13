package zyxhj.course.domain;

import zyxhj.utils.data.rds.RDSAnnEntity;
import zyxhj.utils.data.rds.RDSAnnField;
import zyxhj.utils.data.rds.RDSAnnID;

/**
 * @author 16436
 * 课程大纲实体类
 * 
 * 课程大纲中，课程大纲名与课程是书与书目录的关系
 * 课程所属机构来自教室实体类
 * 
 */
@RDSAnnEntity(alias = "tb_collage")

public class CollageClass {
	/**
	 * 课程大纲id，与课程的区别在于，课程大纲相当于书，课程相当于书的目录
	 */
	@RDSAnnID
	@RDSAnnField(column = RDSAnnField.ID)
	public Long collageId;
	
	/**
	 * 课程大纲名称。
	 */
	@RDSAnnField(column = RDSAnnField.TEXT)
	public Long collageName;
	
	/**
	 * 学时，用于计算工作量
	 */
	@RDSAnnField(column = RDSAnnField.FLOAT)
	public Long knowledge;
	
	/**
	 * 课程所属机构id，所属实验室或所属院校。
	 */
	@RDSAnnField(column = RDSAnnField.ID)
	public Long institutionId;
	
	/**
	 * 课程学分。
	 */
	@RDSAnnField(column = RDSAnnField.INTEGER)
	public Long credit;
	
	/**
	 * 考核方式id。卷面，实验
	 */
	@RDSAnnField(column = RDSAnnField.INTEGER)
	public Long examId;
	
	/**
	 * 所用的实验室Id,与教室实体类相关联。
	 */
	@RDSAnnField(column = RDSAnnField.TEXT_NAME)
	public String classroomName;
	
	
}
