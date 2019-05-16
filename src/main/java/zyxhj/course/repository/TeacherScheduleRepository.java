package zyxhj.course.repository;

import zyxhj.course.domain.TeacherSchedule;
import zyxhj.utils.data.rds.RDSRepository;;

public class TeacherScheduleRepository extends RDSRepository<TeacherSchedule> {

	public TeacherScheduleRepository() {
		super(TeacherSchedule.class);
	}
}
