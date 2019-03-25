package zyxhj.course.repository;


import zyxhj.course.domain.attach.Institution;
import zyxhj.utils.data.rds.RDSRepository;

public class InstitutionRepository extends RDSRepository<Institution> {

	public InstitutionRepository() {
		super(Institution.class);
	}



}
