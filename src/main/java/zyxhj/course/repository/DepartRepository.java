package zyxhj.course.repository;

import zyxhj.course.domain.attach.Dept;
import zyxhj.utils.data.rds.RDSRepository;

public class DepartRepository extends RDSRepository<Dept> {

	public DepartRepository() {
		super(Dept.class);
	}


}
