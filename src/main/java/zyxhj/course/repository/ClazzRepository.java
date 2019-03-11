package zyxhj.course.repository;

import zyxhj.course.domain.attach.Clazz;
import zyxhj.utils.data.rds.RDSRepository;

public class ClazzRepository extends RDSRepository<Clazz> {

	public ClazzRepository() {
		super(Clazz.class);
	}


}
