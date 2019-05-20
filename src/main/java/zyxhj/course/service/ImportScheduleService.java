package zyxhj.course.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.druid.pool.DruidPooledConnection;

import zyxhj.course.domain.Schedule;
import zyxhj.course.repository.ScheduleRepository;
import zyxhj.utils.ExcelUtils;
import zyxhj.utils.IDUtils;
import zyxhj.utils.Singleton;
import zyxhj.utils.api.ServerException;

/**
 * @author 16436
 *
 */
public class ImportScheduleService {
	private static Logger log = LoggerFactory.getLogger(ImportScheduleService.class);

	private ScheduleRepository scheduleRepository;

	public ImportScheduleService() {
		try {
			scheduleRepository = Singleton.ins(ScheduleRepository.class);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	public void importSchedules(DruidPooledConnection conn, String url) throws Exception {

		// 3行表头，23列，文件格式写死的
		List<List<Object>> table = ExcelUtils.readExcelOnline(url, 3, 23, 0); // readExcelOnline(文件地址,表头，多少列，第几个文件(sheet1,sheet2))

		// 遍历表数据 一行一行遍历
		for (List<Object> row : table) {
			// 获取行数据 ExcelUtils.getString(row.get(0)); 表示获取第一列的数据
			// 数据列的下标从0开始
			// 导入班级课表 获取到表内需要的数据 然后进行添加
			String grade = ExcelUtils.getString(row.get(0)); // 年级
			String courseCode = ExcelUtils.getString(row.get(1)); // 课程代码
			String courseNode = ExcelUtils.getString(row.get(2)); // 课程名称
			String courseNature = ExcelUtils.getString(row.get(3)); // 课程性质
			String courseType = ExcelUtils.getString(row.get(4)); // 课程类型
			String credit = ExcelUtils.getString(row.get(5)); // 学分
			String teacherCode = ExcelUtils.getString(row.get(6)); // 职工号
			String teacherName = ExcelUtils.getString(row.get(7)); // 教师姓名
			String teacherTitle = ExcelUtils.getString(row.get(8)); // 教师职称
			String teacherEducation = ExcelUtils.getString(row.get(9)); // 教师学历
			String teacherAcademic = ExcelUtils.getString(row.get(10)); // 教师学位
			String studentNumber = ExcelUtils.getString(row.get(11)); // 学生人数
			String courseSelectionNum = ExcelUtils.getString(row.get(12)); // 选课人数
			String classTime = ExcelUtils.getString(row.get(13)); // 上课时间
			String college = ExcelUtils.getString(row.get(14)); // 开课院校
			String clazz = ExcelUtils.getString(row.get(15)); // 教学班组成
			String courseSelectionCode = ExcelUtils.getString(row.get(16)); // 选课课号
			String courseSelectionType = ExcelUtils.getString(row.get(17)); // 选课状态
			String knowledgeNum = ExcelUtils.getString(row.get(18)); // 总学识
			String theoryKnowledgeNum = ExcelUtils.getString(row.get(19)); // 理论学识
			String experimentKnowledgeNum = ExcelUtils.getString(row.get(20)); // 实验学识
			String computerKnowledgeNum = ExcelUtils.getString(row.get(21)); // 上机学识
			createSchedule(conn, grade, courseCode, courseNode, courseNature, courseType, credit, teacherCode,
					teacherName, teacherTitle, teacherEducation, teacherAcademic, studentNumber, courseSelectionNum,
					classTime, college, clazz, courseSelectionCode, courseSelectionType, knowledgeNum,
					theoryKnowledgeNum, experimentKnowledgeNum, computerKnowledgeNum);

		}
	}

	private void createSchedule(DruidPooledConnection conn, String grade, String courseCode, String courseNode,
			String courseNature, String courseType, String credit, String teacherCode, String teacherName,
			String teacherTitle, String teacherEducation, String teacherAcademic, String studentNumber,
			String courseSelectionNum, String classTime, String college, String clazz, String courseSelectionCode,
			String courseSelectionType, String knowledgeNum, String theoryKnowledgeNum, String experimentKnowledgeNum,
			String computerKnowledgeNum) throws Exception {
		Schedule s = new Schedule();
		s.id = IDUtils.getSimpleId();
		s.grade = grade;
		s.courseCode = courseCode;
		s.courseNode = courseNode;
		s.courseNature = courseNature;
		s.courseType = courseType;
		s.credit = credit;
		s.teacherCode = teacherCode;
		s.teacherName = teacherName;
		s.teacherTitle = teacherTitle;
		s.teacherEducation = teacherEducation;
		s.teacherAcademic = teacherAcademic;
		s.studentNumber = studentNumber;
		s.courseSelectionNum = courseSelectionNum;
		s.classTime = classTime;
		s.college = college;
		s.clazz = clazz;
		s.courseSelectionCode = courseSelectionCode;
		s.courseSelectionType = courseSelectionType;
		s.knowledgeNum = knowledgeNum;
		s.theoryKnowledgeNum = theoryKnowledgeNum;

		s.experimentKnowledgeNum = experimentKnowledgeNum;
		s.computerKnowledgeNum = computerKnowledgeNum;

		scheduleRepository.insert(conn, s);

	}

	public List<Schedule> getSchedule(DruidPooledConnection conn, Integer count, Integer offset) throws Exception {
		return scheduleRepository.getList(conn, count, offset);
	}

	public void deleteSchedule(DruidPooledConnection conn,Long schId) throws Exception {
		scheduleRepository.deleteByKey(conn, "id", schId);
	}

}
