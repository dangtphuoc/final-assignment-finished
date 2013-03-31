package com.packtpub.springdata.jpa.controller;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.packtpub.springdata.jpa.common.ErrorType;
import com.packtpub.springdata.jpa.model.Course;
import com.packtpub.springdata.jpa.model.ResponseBean;
import com.packtpub.springdata.jpa.service.CourseService;


@RequestMapping("/courses")
@Controller
public class CoursesController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CoursesController.class);
	private static final String COURSE_HOME_VIEW = "courses";
	
	@Autowired
	private CourseService courseService;
	
	@RequestMapping(method = RequestMethod.GET, 
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@ResponseBody
	public List<Course> getCourses(@RequestParam(value="key", required=false) String key) {
		List<Course> courses = courseService.getCourses(key);
		return courses;
	}
	
	@RequestMapping(value="/search", method = RequestMethod.GET, 
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@ResponseBody
	public List<Course> searchCourses(@RequestParam(value="key", required=false) String key, 
			@RequestParam(value="startDate", required=false) Date startDate,
			@RequestParam(value="endDate", required=false) Date endDate) {
		List<Course> courses = courseService.searchCourses(key, startDate, endDate);
		return courses;
	}
	
    @RequestMapping(method = RequestMethod.POST, 
    				produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @Transactional
    @ResponseBody
    public ResponseBean createCourse(@RequestBody Course course, BindingResult result) {
    	course = courseService.createCourse(course);
    	ErrorType error = ErrorType.SUCCESS;
    	if(course == null) {
    		error = ErrorType.FAIL;
    	}
        return new ResponseBean(error);
    }
    
	@RequestMapping(value="/update", method = RequestMethod.POST)
	@Transactional
	@ResponseBody
	public ResponseBean updateCourse(@RequestBody Course course,
			BindingResult result) {
		// System.out.println(course.getTitle());
		course = courseService.updateCourse(course);
		ErrorType error = ErrorType.SUCCESS;
		if (course == null) {
			error = ErrorType.FAIL;
		}
		return new ResponseBean(error);
	}
    
	@RequestMapping(value = "{courseId}", method = RequestMethod.GET)
	@ResponseBody
	public Course getCourse(@PathVariable("courseId") Long courseId) 
	{
		Course course = courseService.getCourse(courseId);
		
		return course;
	}
	
	/**
     * Shows the courses home page.
     * @param model The model.
     * @return  The name of the course home view.
     */
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String showCoursesHomePage(Model model) {
        LOGGER.debug("Showing courses home page");
        return COURSE_HOME_VIEW;
    }
	
}
