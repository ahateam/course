package zyxhj.course.repository;

import zyxhj.course.domain.CourseScheduleTerm;
import zyxhj.utils.data.rds.RDSRepository;;

public class CourseScheduleTermRepository extends RDSRepository<CourseScheduleTerm>{
	
	public CourseScheduleTermRepository (){
		super(CourseScheduleTerm.class);
	}
}
