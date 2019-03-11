package zyxhj.course.repository;

import zyxhj.course.domain.attach.Major;
import zyxhj.utils.data.rds.RDSRepository;

public class MajorRepository extends RDSRepository<Major> {

	public MajorRepository() {
		super(Major.class);
	}


}
