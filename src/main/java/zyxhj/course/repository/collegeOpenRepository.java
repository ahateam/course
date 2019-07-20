package zyxhj.course.repository;


import zyxhj.course.domain.collegeOpen;
import zyxhj.utils.data.rds.RDSRepository;

public class collegeOpenRepository extends RDSRepository<collegeOpen> {
	public collegeOpenRepository(){
		super(collegeOpen.class);
	}
}
