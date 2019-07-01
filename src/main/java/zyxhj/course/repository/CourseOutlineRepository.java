package zyxhj.course.repository;

import zyxhj.course.domain.CourseOutline;
import zyxhj.utils.data.rds.RDSRepository;;

public class CourseOutlineRepository extends RDSRepository<CourseOutline>{
	
	public CourseOutlineRepository (){
		super(CourseOutline.class);
	}
}

