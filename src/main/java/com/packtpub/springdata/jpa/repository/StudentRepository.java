package com.packtpub.springdata.jpa.repository;

import org.springframework.data.repository.CrudRepository;

import com.packtpub.springdata.jpa.model.Location;
import com.packtpub.springdata.jpa.model.Student;

public interface StudentRepository extends CrudRepository<Student, Long> {

}
