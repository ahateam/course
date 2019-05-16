package zyxhj.course.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.druid.pool.DruidPooledConnection;

import zyxhj.course.domain.TeacherSchedule;
import zyxhj.course.repository.TeacherScheduleRepository;
import zyxhj.utils.ExcelUtils;
import zyxhj.utils.Singleton;

/**
 * @author 16436
 *
 */
public class importSchedule {
	private static Logger log = LoggerFactory.getLogger(importSchedule.class);

	private TeacherScheduleRepository teacherScheduleRepository;

	public importSchedule() {
		try {
			teacherScheduleRepository = Singleton.ins(TeacherScheduleRepository.class);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	public void importSchedules(DruidPooledConnection conn, Long orgId, String url) throws Exception {

		// 3行表头，23列，文件格式写死的
		List<List<Object>> table = ExcelUtils.readExcelOnline(url, 3, 23, 0); // readExcelOnline(文件地址,表头，多少列，第几个文件(sheet1,sheet2))

		// 遍历表数据 一行一行遍历
		for (List<Object> row : table) {
			// 获取行数据 ExcelUtils.getString(row.get(0)); 表示获取第一列的数据
			// 数据列的下标从0开始
			// 导入班级课表 获取到表内需要的数据 然后进行添加

			// 导入教师档期
			String teacherCode = ExcelUtils.getString(row.get(6)); // 职工号
			String teacherName = ExcelUtils.getString(row.get(7)); // 教师名称
			String courseCode = ExcelUtils.getString(row.get(2)); // 课程代码
			String courseName = ExcelUtils.getString(row.get(3)); // 课程名称 一个课程有多个教师上课 需解析

			// 教师课程档期 上课时间
			String teacherSchedule = ExcelUtils.getString(row.get(13)); // 这里要做文本解析
			// 这里获取到了上课时间 但是需要文本解析
			String weekDay = "周一"; // 拆分teacherSchedule获取到周几
			Integer startTime = 7; // 第几节课开始
			Integer endTime = 8; // 第节课结束 拆分获取
			Integer startWeek = 1; // 开始周次
			Integer endWeek = 17; // 结束周次

			String classAddress = ExcelUtils.getString(row.get(14)); // 上课地点 需解析
			String clazz = ExcelUtils.getString(row.get(16)); // 上课班级

			// 。。。。。。。。在所有数据添加完成后 开始导入到教师档期表内

			// 异常处理
			try {
				createTeacherSchedule(conn, teacherCode, teacherName, courseName, courseCode, weekDay, startTime,
						endTime, startWeek, endWeek, classAddress, clazz);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void createTeacherSchedule(DruidPooledConnection conn, String teacherCode, String teacherName,
			String courseName, String courseCode, String weekDay, Integer startTime, Integer endTime, Integer startWeek,
			Integer endWeek, String classAddress, String clazz) throws Exception {
		TeacherSchedule teacherSchedule = new TeacherSchedule();
		// 将值一一放入 对象中进行添加
		teacherSchedule.teacherCode = teacherCode;
		teacherSchedule.teacherName = teacherName;
		teacherSchedule.courseCode = courseCode;
		teacherSchedule.courseName = courseName;
		teacherSchedule.weekDay = weekDay;
		teacherSchedule.startTime = startTime;
		teacherSchedule.endTime = endTime;
		teacherSchedule.startWeek = startWeek;
		teacherSchedule.endWeek = endWeek;
		teacherSchedule.classAddress = classAddress;
		teacherSchedule.clazz = clazz;
		teacherScheduleRepository.insert(conn, teacherSchedule);

	}
}
