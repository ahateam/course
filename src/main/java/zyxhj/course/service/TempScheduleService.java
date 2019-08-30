package zyxhj.course.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.druid.pool.DruidPooledConnection;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import zyxhj.course.domain.CourseScheduleTerm;
import zyxhj.course.domain.TempSchedule;
import zyxhj.course.repository.CourseScheduleTermRepository;
import zyxhj.course.repository.ScheduleRepository;
import zyxhj.utils.ExcelUtils;
import zyxhj.utils.IDUtils;
import zyxhj.utils.Singleton;
import zyxhj.utils.api.ServerException;
import zyxhj.utils.data.EXP;

public class TempScheduleService {
	private static Logger log = LoggerFactory.getLogger(TempScheduleService.class);

	private ScheduleRepository scheduleRepository;
	private CourseScheduleTermRepository courseScheduleTermRepository;

	public TempScheduleService() {
		try {
			scheduleRepository = Singleton.ins(ScheduleRepository.class);
			courseScheduleTermRepository = Singleton.ins(CourseScheduleTermRepository.class);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	// 导入档期到临时表
	public void importTempSchedule(DruidPooledConnection conn, String url) throws Exception {

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
			String classAddress = ExcelUtils.getString(row.get(14)); // 上课时间
			String college = ExcelUtils.getString(row.get(15)); // 开课院校
			String clazz = ExcelUtils.getString(row.get(16)); // 教学班组成
			String courseSelectionCode = ExcelUtils.getString(row.get(17)); // 选课课号
			String courseSelectionType = ExcelUtils.getString(row.get(18)); // 选课状态
			String knowledgeNum = ExcelUtils.getString(row.get(19)); // 总学识
			String theoryKnowledgeNum = ExcelUtils.getString(row.get(20)); // 理论学识
			String experimentKnowledgeNum = ExcelUtils.getString(row.get(21)); // 实验学识
			String computerKnowledgeNum = ExcelUtils.getString(row.get(22)); // 上机学识
			createSchedule(conn, grade, courseCode, courseNode, courseNature, courseType, credit, teacherCode,
					teacherName, teacherTitle, teacherEducation, teacherAcademic, studentNumber, courseSelectionNum,
					classTime, classAddress, college, clazz, courseSelectionCode, courseSelectionType, knowledgeNum,
					theoryKnowledgeNum, experimentKnowledgeNum, computerKnowledgeNum);

		}
	}

	private void createSchedule(DruidPooledConnection conn, String grade, String courseCode, String courseNode,
			String courseNature, String courseType, String credit, String teacherCode, String teacherName,
			String teacherTitle, String teacherEducation, String teacherAcademic, String studentNumber,
			String courseSelectionNum, String classTime, String classAddress, String college, String clazz,
			String courseSelectionCode, String courseSelectionType, String knowledgeNum, String theoryKnowledgeNum,
			String experimentKnowledgeNum, String computerKnowledgeNum) throws Exception {
		TempSchedule s = new TempSchedule();
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
		s.classAddress = classAddress;
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

	public List<TempSchedule> getSchedule(DruidPooledConnection conn, Integer count, Integer offset) throws Exception {
//		return scheduleRepository.getList(conn, count, offset);
		return scheduleRepository.getList(conn, null, count, offset);
	}

	public void deleteSchedule(DruidPooledConnection conn, Long schId) throws Exception {
//		scheduleRepository.deleteByKey(conn, "id", schId);
		scheduleRepository.delete(conn, EXP.INS().key("id", schId));
	}

	// 正式导入全校课程
	public void ImportCourseScheduleTerm(DruidPooledConnection conn, Long termId) throws Exception {
		// 从数据库中把需要的数据拿出来
		Integer count = scheduleRepository.countAll(conn);
		// 把数据100条100条拿出来解析
		Integer offset = 0;
		for (int i = 0; i < count / 100 + 1; i++) {
			List<TempSchedule> sc = scheduleRepository.getList(conn,null,100,offset);
			
			// 1：先拆班级 把班级取出来
			for (TempSchedule tempSchedule : sc) {
				CourseScheduleTerm courseScheduleTerm = new CourseScheduleTerm();

				courseScheduleTerm.termId = termId;
				courseScheduleTerm.courseNo = tempSchedule.courseCode;
				courseScheduleTerm.courseName = tempSchedule.courseNode;
				courseScheduleTerm.teachers = tempSchedule.teacherName;

				// 拆分班级
				String clazzs = tempSchedule.clazz;
				StringUtils.substring(clazzs, 1, clazzs.length());
				String[] clazz = StringUtils.split(clazzs, ",");

				// 拆分教室
				String classAddress = tempSchedule.classAddress;
				StringUtils.substring(classAddress, 1, classAddress.length());// 切掉首位’
				String[] classAddres = StringUtils.split(classAddress, ";"); // 用；把字符串切割成字符串数组

				// 分号分隔（要小心中英文符号可能不同）
				String src = tempSchedule.classTime;
				String[] nodes = StringUtils.split(src, ';');

				for (int j = 0; j < clazz.length; j++) {
					courseScheduleTerm.classId = clazz[j]; // 班级
					// 班级信息有了 遍历上课教室
					for (int k = 0; k < classAddres.length; k++) {

						courseScheduleTerm.classroomId = classAddres[k]; // 教室

						// 拆分上课时间 上课时间与上课地点是一一对应的 所以 此处只用一层循环 但是内容为多重循环
						String node = nodes[k];

						// 开始解析 每一段的课程安排

						// 找第一个“周”字
						int indWeekStart = node.indexOf("周");
						String week = node.substring(indWeekStart + 1, indWeekStart + 2);

						courseScheduleTerm.weekTime = weekTime(week); // 周几

						int indLessonStart = node.indexOf("第", indWeekStart + 2);
						int indLessonEnd = node.indexOf("节", indLessonStart + 1);
						String strLessons = node.substring(indLessonStart + 1, indLessonEnd); // 切出来为 例：9,10 这里需要拆分
																								// 然后转换成数字后遍历
						String[] lesson = StringUtils.split(strLessons, ",");
						Integer lessonFirst = Integer.parseInt(lesson[0]);
						Integer lessonSecend = Integer.parseInt(lesson[1]);
						for (int x = lessonFirst; x <= lessonSecend; x++) {
							// 第几节课遍历出来了
							courseScheduleTerm.lessonNum = (byte) x; // 第几节课
							// 开始解析周次 先找{}内的内容
							String weekTimeContent = node.substring(indLessonEnd + 1, node.length());

							int indWeekTimeStart = weekTimeContent.indexOf("第");
							int indWeekTimeEnd = weekTimeContent.indexOf("周", indWeekTimeStart + 1);
							String strWeekTimes = weekTimeContent.substring(indWeekTimeStart + 1, indWeekTimeEnd);

							int ddd = weekTimeContent.indexOf("|");
							String singleandDouble = "单周"; // 默认为单周
							if (ddd != -1) {
								String yyy = weekTimeContent.substring(ddd + 1, weekTimeContent.length() - 1); // 取到单双周
								if (yyy.equals("双周")) {
									singleandDouble = "双周";
								}
							}

							String[] weekNum = StringUtils.split(strWeekTimes, "-");
							Integer weekFirst = Integer.parseInt(weekNum[0]);
							Integer weekSecend = Integer.parseInt(weekNum[1]);
							for (int y = weekFirst; y <= weekSecend;) {
								// 取出了周次
								courseScheduleTerm.weekNum = (byte) y; // 第几周

								// 添加到档期表ImportCourseScheduleTerm 注：主要是以班级的课程档期为主s
								courseScheduleTerm.id = IDUtils.getSimpleId();
								courseScheduleTermRepository.insert(conn, courseScheduleTerm);
								if ("单周".equals(singleandDouble)) {
									y++;
								} else {
									y = y + 2;
								}
							}

						}

					}

				}

			}

		}

	}

	// 周转换
	private Byte weekTime(String week) {
		if (week.equals("一")) {
			return 1;
		}
		if (week.equals("二")) {
			return 2;
		}
		if (week.equals("三")) {
			return 3;
		}
		if (week.equals("四")) {
			return 4;
		}
		if (week.equals("五")) {
			return 5;
		}
		if (week.equals("六")) {
			return 6;
		} else {
			return 7;
		}

	}

	// 根据多个班级编号读取班级信息
	public JSONObject getCourseScheduleTermByclasss(DruidPooledConnection conn, String clazzs) throws Exception {

		// 将clazzs转换为json格式 遍历json
		JSONArray json = JSONArray.parseArray(clazzs);
		// 查询班级的档期 将值放入到一个JSONObject中 {clazz1:[{xxx},{xxxx}],clazz2[{xxxx},{xxx}]}
		JSONObject jo = new JSONObject();
		for (int i = 0; i < json.size(); i++) {
			List<CourseScheduleTerm> course = courseScheduleTermRepository.
					getList(conn,EXP.INS().key("class_id", json.getString(i)),512,0);
			jo.put(json.getString(i), course);
		}
		// 最后返回JSONObjectF

		return jo;
	}

}
