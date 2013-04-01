package springdata.jpa.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import springdata.jpa.dto.CourseDTO;
import springdata.jpa.model.ClassOffering;
import springdata.jpa.model.Course;
import springdata.jpa.repository.ClassOfferingRepository;
import springdata.jpa.repository.CourseRepository;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.mysema.query.types.Predicate;

import static springdata.jpa.querydsl.predicate.CoursePredicate.searchByTitleAndDescription;

@Service
public class CourseService {
	
	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private ClassOfferingRepository classOfferingRepository;
	
	public Course createCourse(CourseDTO courseDTO) {
		Course course = courseDTO.toCourse();
		if(course.getClassOfferings() != null) {
			for(ClassOffering c : course.getClassOfferings()) {
				c.setCourse(course);
			}
		}
		Course managedCourse = courseRepository.save(course);
		return managedCourse;
	}

	public List<Course> getCourses(String key) {
		if(Strings.isNullOrEmpty(key)) {
			return Lists.newArrayList(courseRepository.findAll());
		} else {
			return courseRepository.getCourses("%" + key + "%");
		}
	}

	public Course getCourse(Long courseId) {
		List<ClassOffering> offerings = classOfferingRepository.getClassOfferingsByCourseId(courseId);
		return courseRepository.findOne(courseId);
	}

	@Transactional
	public Course updateCourse(CourseDTO courseDTO) {
		Course course = courseDTO.toCourse();
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

	public List<Course> searchCourses(String key) {
		Predicate query = searchByTitleAndDescription(key);
		return Lists.newArrayList(courseRepository.findAll(query));
	}

	public List<Course> searchCourses(String key, Date startDate, Date endDate) {
		return courseRepository.searchCourses(key, startDate, endDate);
	}
}
