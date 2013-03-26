package com.packtpub.springdata.jpa.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.packtpub.springdata.jpa.model.Course;


@Transactional
public interface CourseRepository extends CrudRepository<Course, Long> {

}
