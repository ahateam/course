package zyxhj.course.repository;

import zyxhj.course.domain.TempSchedule;
import zyxhj.utils.data.rds.RDSRepository;;

public class ScheduleRepository extends RDSRepository<TempSchedule> {

	public ScheduleRepository() {
		super(TempSchedule.class);
	}
}
