package zyxhj.course.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.druid.pool.DruidPooledConnection;

import zyxhj.course.domain.CourseOutline;

public class CourseOutlineService {
	private static Logger log = LoggerFactory.getLogger(CourseOutlineService.class);

	public CourseOutlineService() {
		try {
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	// 添加课程大纲
	public CourseOutline createCourseOutline(DruidPooledConnection conn, Long departmentId, String courseOutlineName,
			Integer classHour, Float credit, Byte exam, Byte elect, String classIds) {
		// INSERT INTO table_name (列1, 列2,...) VALUES (值1, 值2,....)
		// 或者 INSERT INTO 表名称 VALUES (值1, 值2,....)

		// Inset into tb_course_outline(字段,字段,字段) value(值 使用？代替,?,?)

		// 定义好参数后 将参数放入CourseOutline实体类中

		// 执行添加课程大纲
		return null;
	}

	// 修改课程大纲
	public CourseOutline editCourseOutline(DruidPooledConnection conn, Long courseOutlineId, Long departmentId,
			String courseOutlineName, Integer classHour, Float credit, Byte exam, Byte elect, String classIds) {
		// UPDATE 表名称 SET 列名称 = 新值 WHERE 列名称 = 某值

		// 定义好参数后 将参数放入CourseOutline实体类中

		// 执行修改课程大纲 根据id进行修改
		return null;
	}

	// 删除课程大纲
	public int delCourseOutline(DruidPooledConnection conn, Long courseOutlineId) {
		// DELETE FROM 表名称 WHERE 列名称 = 值

		// Delete from tb_course_outline where id = ?
		// 执行删除课程大纲 根据id进行删除
		return 0;
	}

	// 分页查询课程大纲
	public List<CourseOutline> getCourseOutline(DruidPooledConnection conn, Integer count, Integer offset) {
		// SELECT 列名称 FROM 表名称 WHERE 条件

		// select * from tb_course_outline limit 10 offset 0
		// 分页查询课程大纲
		return null;
	}

}
