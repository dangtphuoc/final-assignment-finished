package springdata.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import springdata.jpa.model.ClassOffering;


public interface ClassOfferingRepository extends CrudRepository<ClassOffering, Long>, ClassOfferingRepositoryCustom {
	
	@Query("select c from ClassOffering c where c.course.id = :courseId")
	public List<ClassOffering> getClassOfferingsByCourseId(@Param("courseId") Long courseId);
}
