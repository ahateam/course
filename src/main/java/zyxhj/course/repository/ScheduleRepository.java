package zyxhj.course.repository;

import zyxhj.course.domain.Schedule;
import zyxhj.utils.data.rds.RDSRepository;;

public class ScheduleRepository extends RDSRepository<Schedule> {

	public ScheduleRepository() {
		super(Schedule.class);
	}
}
