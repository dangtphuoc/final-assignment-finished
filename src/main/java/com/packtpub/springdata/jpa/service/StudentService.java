package com.packtpub.springdata.jpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.packtpub.springdata.jpa.model.Student;
import com.packtpub.springdata.jpa.repository.StudentRepository;

@Service
public class StudentService {
	
	@Autowired
	private StudentRepository studentRepository;
	
	public List<Student> getStudents() {
		return Lists.newArrayList(studentRepository.findAll());
	}

	public Student getStudent(Long studentId) {
		return studentRepository.findOne(studentId);
	}

	@Transactional
	public Student createUpdateStudent(Student student) {
		Student managedStudent = null;
		if(student.getId() == null) {
			managedStudent = studentRepository.save(student);
		} else {
			managedStudent = studentRepository.findOne(student.getId());
			managedStudent.setFirstName(student.getFirstName());
			managedStudent.setLastName(managedStudent.getLastName());
		}
		return managedStudent;
	}

	public List<Student> getStudents(Long filterRole) {
		return studentRepository.getStudents(filterRole);
	}

}
