package zyxhj.course.repository;

import zyxhj.course.domain.Department;
import zyxhj.utils.data.rds.RDSRepository;;

public class DepartmentRepository extends RDSRepository<Department>{
	
	public DepartmentRepository (){
		super(Department.class);
	}
}
