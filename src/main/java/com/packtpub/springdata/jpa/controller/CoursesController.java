package com.packtpub.springdata.jpa.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.packtpub.springdata.jpa.common.ErrorType;
import com.packtpub.springdata.jpa.model.Course;
import com.packtpub.springdata.jpa.model.ResponseBean;
import com.packtpub.springdata.jpa.service.CourseService;


@RequestMapping("/courses")
@Controller
public class CoursesController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CoursesController.class);
	private final String COURSE_VIEW = "course";
	
	@Autowired
	private CourseService courseService;
	
	@RequestMapping(method = RequestMethod.GET, 
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@ResponseBody
	public List<Course> getCourses() 
	{
		List<Course> courses = courseService.getCourses();
		return courses;
	}
	
    @RequestMapping(method = RequestMethod.POST, 
    				produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @Transactional
    @ResponseBody
    public ResponseBean createCourse(@RequestBody Course course, BindingResult result) {
    	//System.out.println(course.getTitle());
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
     * Shows the add contact page.
     * @param model The model.
     * @return  The name of the add contact view.
     */
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String showAddContactPage(Model model) {
        LOGGER.debug("Showing course page");
        return COURSE_VIEW;
    }
	
}
