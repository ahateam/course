package zyxhj.course.repository;

import zyxhj.course.domain.CourseScheduleLab;
import zyxhj.utils.data.rds.RDSRepository;;

public class CourseScheduleLabRepository extends RDSRepository<CourseScheduleLab>{
	
	public CourseScheduleLabRepository (){
		super(CourseScheduleLab.class);
	}
}
