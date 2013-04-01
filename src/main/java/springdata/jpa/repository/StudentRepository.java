package springdata.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import springdata.jpa.model.Student;


public interface StudentRepository extends CrudRepository<Student, Long> {
	
	@Query("Select s From Student s join s.roles r where r.id = :roleId")
	public List<Student> getStudents(@Param("roleId") Long filterRole);
}
