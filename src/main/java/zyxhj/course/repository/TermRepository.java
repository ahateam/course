package zyxhj.course.repository;

import zyxhj.course.domain.Term;
import zyxhj.utils.data.rds.RDSRepository;;

public class TermRepository extends RDSRepository<Term>{
	
	public TermRepository (){
		super(Term.class);
	}
}
