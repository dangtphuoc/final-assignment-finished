package springdata.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import springdata.jpa.model.Curriculum;


public interface CurriculumRepository extends JpaRepository<Curriculum, Long>, QueryDslPredicateExecutor<Curriculum>, CurriculumRepositoryCustom {

	public Curriculum getCurriculumById(Long id);

}
