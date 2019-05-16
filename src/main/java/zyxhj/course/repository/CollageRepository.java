package zyxhj.course.repository;

import zyxhj.utils.data.rds.RDSRepository; 
import zyxhj.course.domain.CollageClass;;

public class CollageRepository extends RDSRepository<CollageClass>{
	
	public CollageRepository (){
		super(CollageClass.class);
	}
}
