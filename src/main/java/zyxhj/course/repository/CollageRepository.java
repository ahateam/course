package zyxhj.course.repository;

import zyxhj.course.domain.attach.Collage;
import zyxhj.utils.data.rds.RDSRepository;

public class CollageRepository extends RDSRepository<Collage> {

	public CollageRepository() {
		super(Collage.class);
	}

}
