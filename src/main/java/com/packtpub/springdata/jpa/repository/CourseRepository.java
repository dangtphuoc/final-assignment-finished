package com.packtpub.springdata.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.packtpub.springdata.jpa.model.Course;


@Transactional
public interface CourseRepository extends JpaRepository<Course, Long>, QueryDslPredicateExecutor<Course>, CourseRepositoryCustom {

	@Query("Select c From Course c Where c.title LIKE :key")
	public List<Course> getCourses(@Param("key") String key);

}
