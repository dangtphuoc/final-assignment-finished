package springdata.jpa.service;

import static springdata.jpa.querydsl.predicate.CoursePredicate.searchByTitleAndDescription;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import springdata.jpa.dto.CourseDTO;
import springdata.jpa.model.ClassOffering;
import springdata.jpa.model.Course;
import springdata.jpa.repository.ClassOfferingRepository;
import springdata.jpa.repository.CourseRepository;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.mysema.query.types.Predicate;

@Service
@Transactional(readOnly=true)
public class CourseService {
	
	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private ClassOfferingRepository classOfferingRepository;
	
	@Transactional(readOnly=false)
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
		return courseRepository.findOne(courseId);
	}

	@Transactional(readOnly=false)
	public Course updateCourse(CourseDTO courseDTO) {
		Course course = courseDTO.toCourse();
		Course managedCourse = courseRepository.findOne(course.getId());
		//remove all first
		if(managedCourse.getClassOfferings() != null) {
			List<ClassOffering> cloneOne = Lists.newArrayList(managedCourse.getClassOfferings());
			for(ClassOffering cla : cloneOne) {
				if(course.getClassOfferings() == null || !course.getClassOfferings().contains(cla)) managedCourse.removeClassOffering(cla);
			}
		}
		//update class offerings
		if(course.getClassOfferings() != null) {
			for(ClassOffering classOffering : course.getClassOfferings()) {
				if(classOffering.getId() == null) {
					managedCourse.addClassOffering(classOffering);
				}
				
			}
		}
		
		managedCourse.setTitle(course.getTitle());
		managedCourse.setDescription(course.getDescription());
		
		return managedCourse;
	}

	public List<Course> searchCourses(String key) {
		Predicate query = searchByTitleAndDescription(key);
		return Lists.newArrayList(courseRepository.findAll(query));
	}

	public List<Course> searchCourses(String key, Date startDate, Date endDate) {
		return courseRepository.searchCourses(key, startDate, endDate);
	}

	@Transactional(readOnly=false)
	public void deleteCourse(Long courseId) {
		Course course = courseRepository.findOne(courseId);
		if(course != null) {
			courseRepository.delete(course);
		}
	}
}
