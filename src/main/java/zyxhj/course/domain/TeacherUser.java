package zyxhj.course.domain;

import zyxhj.utils.data.rds.RDSAnnEntity;
import zyxhj.utils.data.rds.RDSAnnField;
import zyxhj.utils.data.rds.RDSAnnID;

/**
 * @author 16436
 * 老师用户实体类
 * 
 * 可当做“用户”来对待，登录什么的
 */
@RDSAnnEntity(alias = "tb_teacher_user")
public class TeacherUser {

	/**
	 * 教师工号，可做登录账号
	 */
	@RDSAnnID
	@RDSAnnField(column = RDSAnnField.ID)
	public Long teacherId;
	
	/**
	 * 老师登录密码
	 */
	@RDSAnnField(column = RDSAnnField.TEXT_PWD)
	public Long teacherPassword;
	
	
	/**
	 * 老师用户管理员级别Id,普通教师，排课教师，教务科，教务处，后台人员
	 */
	@RDSAnnField(column = RDSAnnField.ID)
	public Long adminId;
	
	/**
	 * 教师姓名
	 */
	@RDSAnnField(column = RDSAnnField.TEXT_NAME)
	public String teacherName;
	
	/**
	 * 老师职称id
	 */
	@RDSAnnField(column = RDSAnnField.ID)
	public Long positionId;
	
	/**
	 * 老师职称
	 */
	@RDSAnnField(column = RDSAnnField.TEXT_NAME)
	public String positionName;
	
	/**
	 * 老师所属学院id
	 */
	@RDSAnnField(column = RDSAnnField.ID)
	public Long Tcollage;
	
	/**
	 * 老师所属学院
	 */
	@RDSAnnField(column = RDSAnnField.TEXT_NAME)
	public Long TcollageName;
	
	/**
	 * 老师所教的课程Id
	 */
	@RDSAnnField(column = RDSAnnField.ID)
	public Long TcourseId;
	
	/**
	 * 老师所教课程名称
	 */
	@RDSAnnField(column = RDSAnnField.TEXT)
	public Long TcourseName; 
	
	
}
