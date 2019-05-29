package zyxhj.course.repository;

import zyxhj.utils.data.rds.RDSRepository; 
import zyxhj.course.domain.CourseOutline;;

public class CollageRepository extends RDSRepository<CourseOutline>{
	
	public CollageRepository (){
		super(CourseOutline.class);
	}
}
