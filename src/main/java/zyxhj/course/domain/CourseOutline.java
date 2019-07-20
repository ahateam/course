package zyxhj.course.domain;

import zyxhj.utils.api.Controller.ENUMVALUE;
import zyxhj.utils.data.rds.RDSAnnEntity;
import zyxhj.utils.data.rds.RDSAnnField;
import zyxhj.utils.data.rds.RDSAnnID;

/**
 * 课程大纲实体类
 * 
 * 课程大纲中，课程大纲名与课程是书与书目录的关系 课程所属机构来自教室实体类
 * 
 */
@RDSAnnEntity(alias = "tb_course_outline")

public class CourseOutline {

	/**
	 * 
	 * @author 16436 考核方式对应Id
	 */

	public static enum EXAM implements ENUMVALUE {
		ROLLTEST((byte) 0, "卷面考试"), //
		THESIS((byte) 1, "论文"), //
		EXPERIMENT((byte) 2, "实验操作"), //
		OTHER((byte) 3, "其它"), //

		;

		private byte v;
		private String txt;

		private EXAM(Byte v, String txt) {
			this.v = v;
			this.txt = txt;
		}

		@Override
		public byte v() {
			return v;
		}

		@Override
		public String txt() {
			return txt;
		}
	}

	public static enum ELECT implements ENUMVALUE {

		OBLIGATORY((byte) 0, "通识必修课"), //
		ELECTIVE((byte) 1, "通识选修课"), //
		OTHER((byte) 2, "其它"), //

		;

		private byte v;
		private String txt;

		private ELECT(Byte v, String txt) {
			this.v = v;
			this.txt = txt;
		}

		@Override
		public byte v() {
			return v;
		}

		@Override
		public String txt() {
			return txt;
		}
	}

	/**
	 * 课程大纲id，与课程的区别在于，课程大纲相当于书，课程相当于书的目录
	 */
	@RDSAnnID
	@RDSAnnField(column = RDSAnnField.ID)
	public Long Id;
	
	/**
	 * 课程大纲名称。
	 */
	@RDSAnnField(column = RDSAnnField.TEXT_TITLE)
	public String name;

	/**
	 * 所属院系id，所属实验室或所属院校。
	 */
	@RDSAnnField(column = RDSAnnField.ID)
	public Long collegeId;
	
	/**
	 * 所属学院名称
	 */
	@RDSAnnField(column = "VARCHAR(255)")
	public String collegeName;

	/**
	 * 学期id
	 */
	@RDSAnnField(column = RDSAnnField.ID)
	public Long termId;

	

	/**
	 * 学时，用于计算工作量
	 */
	@RDSAnnField(column = RDSAnnField.INTEGER)
	public Integer classHour;

	/**
	 * 课程学分
	 */
	@RDSAnnField(column = RDSAnnField.FLOAT)
	public Float credit;

	/**
	 * 考核方式id。卷面,实验,论文
	 */
	@RDSAnnField(column = RDSAnnField.INTEGER)
	public Byte exam;

	/**
	 * 课程性质Id,对应的 通识必修 选修 其它。
	 */

	@RDSAnnField(column = RDSAnnField.INTEGER)
	public Byte elect;

	/**
	 * 参加课程的班级编号列表(JSONArray)
	 */
	@RDSAnnField(column = RDSAnnField.SHORT_TEXT)
	public String classIds;

}
