package com.packtpub.springdata.jpa.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.packtpub.springdata.jpa.model.ClassOffering;
import com.packtpub.springdata.jpa.model.Course;
import com.packtpub.springdata.jpa.repository.ClassOfferingRepository;
import com.packtpub.springdata.jpa.repository.CourseRepository;

@Service
public class CourseService {
	
	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private ClassOfferingRepository classOfferingRepository;
	
	public Course createCourse(Course course) {
		
		if(course.getClassOfferings() != null) {
			for(ClassOffering c : course.getClassOfferings()) {
				c.setCourse(course);
			}
		}
		Course managedCourse = courseRepository.save(course);
		return managedCourse;
	}

	public List<Course> getCourses() {
		return Lists.newArrayList(courseRepository.findAll());
	}

	public Course getCourse(Long courseId) {
		List<ClassOffering> offerings = classOfferingRepository.getClassOfferingsByCourseId(courseId);
		return courseRepository.findOne(courseId);
	}

	@Transactional
	public Course updateCourse(Course course) {
		Course oldCourse = courseRepository.findOne(course.getId());
		//remove all first
		if(oldCourse.getClassOfferings() != null) {
			List<ClassOffering> cloneOne = oldCourse.cloneClassOfferings();
			for(ClassOffering cla : cloneOne) {
				oldCourse.removeClassOffering(cla);
			}
		}
		//update class offerings
		if(course.getClassOfferings() != null) {
			for(ClassOffering classOffering : course.getClassOfferings()) {
				if(classOffering.getId() == null) {
					oldCourse.addClassOffering(classOffering);
				}
				
			}
		}
		
		oldCourse.setTitle(course.getTitle());
		oldCourse.setDescription(course.getDescription());
		
		return oldCourse;
	}
}
