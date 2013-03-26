package com.packtpub.springdata.jpa.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.packtpub.springdata.jpa.common.ErrorType;
import com.packtpub.springdata.jpa.model.ResponseBean;
import com.packtpub.springdata.jpa.model.Student;
import com.packtpub.springdata.jpa.service.StudentService;


@RequestMapping("/students")
@Controller
public class StudentsController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(StudentsController.class);
	
	@Autowired
	private StudentService studentService;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET, produces={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@ResponseBody
	public Student getStudent(@PathVariable("id") Long studentId) {
		return studentService.getStudent(studentId);
	}
	
	@RequestMapping(method=RequestMethod.GET, produces={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@ResponseBody
	public List<Student> getStudents() {
		return studentService.getStudents();
	}

	@RequestMapping(method=RequestMethod.POST, produces={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@ResponseBody
	public ResponseBean createStudent(@RequestBody Student student) {
		student = studentService.createUpdateStudent(student);
		ErrorType error = ErrorType.SUCCESS;
		if(student.getId() != null) {
    		error = ErrorType.FAIL;
    	}
        return new ResponseBean(error);
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST, produces={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@ResponseBody
	public ResponseBean updateStudent(@RequestBody Student student) {
		student = studentService.createUpdateStudent(student);
		ErrorType error = ErrorType.SUCCESS;
		if(student.getId() != null) {
    		error = ErrorType.FAIL;
    	}
        return new ResponseBean(error);
	}
}
