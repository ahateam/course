package zyxhj.course.repository;

	
import zyxhj.utils.data.rds.RDSRepository;

import zyxhj.course.domain.Department;

public class DepartmentRepository extends RDSRepository<Department>{
	
	public DepartmentRepository (){
		super(Department.class);
	}
}
