package zyxhj.course.domain;

public class CollageUser {

	/**
	 * 院校编号
	 */
	public Long colleageId;

	public Long userId;

	/**
	 * 所属学院编号(考虑双学位的学生，需要存储多个编号)
	 */
	public String institutionIds;

	/**
	 * 所属系编号(考虑双学位的学生，需要存储多个编号)
	 */
	public String deptIds;

	/**
	 * 专业编号(考虑双学位的学生，需要存储多个编号)
	 */
	public Long majorIds;

	/**
	 * 角色（老师，学生，管理员等等）
	 */
	public String roles;

	/**
	 * 教师（学生）编号
	 */
	public String sn;

	/**
	 * 身份证号
	 */
	public String idNumber;

	/**
	 * 手机号
	 */
	public String mobile;

	/**
	 * 学生年级（教师不填）
	 */
	public String grade;

	/**
	 * 班级信息（教师不填,考虑双学位的学生，需要存储多个编号）
	 */
	public String Classes;

}
