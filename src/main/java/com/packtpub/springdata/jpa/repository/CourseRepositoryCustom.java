package com.packtpub.springdata.jpa.repository;

import java.util.Date;
import java.util.List;

import com.packtpub.springdata.jpa.model.Course;

public interface CourseRepositoryCustom {

	public List<Course> searchCourses(String key, Date startDate, Date endDate);

}
