package com.packtpub.springdata.jpa.querydsl.predicate;

import com.mysema.query.types.Predicate;
import com.packtpub.springdata.jpa.model.QCourse;

public class CoursePredicate {
	public static Predicate searchByTitleAndDescription(String key) {
		QCourse course = QCourse.course;
		Predicate query = course.title.contains(key).or(course.description.contains(key));
		return query;
	}
}
