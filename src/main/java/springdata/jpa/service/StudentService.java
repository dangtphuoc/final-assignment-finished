package springdata.jpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import springdata.jpa.model.Student;
import springdata.jpa.repository.StudentRepository;

import com.google.common.collect.Lists;

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
