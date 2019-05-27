package zyxhj.course.service;

import org.apache.commons.lang3.StringUtils;

public class Test {

	public static void main(String[] args) {

		String src = "'周一第9,10节{第1-2周};周一第9,10节{第5-18周};周四第1,2节{第1-2周};周四第1,2节{第5-18周};周五第9,10节{第1-2周};周五第9,10节{第5-6周}";

		// 分号分隔（要小心中英文符号可能不同）
		String[] nodes = StringUtils.split(src, ';');
		if (nodes != null && nodes.length > 0) {
			for (String node : nodes) {
				System.out.println("node>>>" + node);

				// 开始解析每一段的课程安排

				// 找第一个“周”字
				int indWeekStart = node.indexOf("周");
				String week = node.substring(indWeekStart + 1, indWeekStart + 2);
				System.out.println(">>>week=" + week);
				
				int indLessonStart = node.indexOf("第", indWeekStart + 2);
				int indLessonEnd = node.indexOf("节", indLessonStart + 1);
				String strLessons = node.substring(indLessonStart + 1, indLessonEnd);
				System.out.println(">>>lessons=" + strLessons);
				
				//开始解析lesson
				String[] lessons = StringUtils.split(strLessons,',');
				
				//开始解析周次
				int indWeekTimeStart = node.indexOf("第", indLessonEnd+1);
				int indWeekTimeEnd = node.indexOf("周", indWeekTimeStart+1);
				String strWeekTimes = node.substring(indWeekTimeStart + 1, indWeekTimeEnd);
				System.out.println(">>>WeekTimes=" + strWeekTimes);
				
				//开始解析WeekTimes
				String[] weekTimes = StringUtils.split(strWeekTimes,'-');
			}
		} else {
			// 异常报警
		}
	}
}
