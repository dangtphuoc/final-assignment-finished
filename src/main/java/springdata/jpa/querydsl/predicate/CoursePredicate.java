package springdata.jpa.querydsl.predicate;

import springdata.jpa.model.QCourse;

import com.mysema.query.types.Predicate;

public class CoursePredicate {
	public static Predicate searchByTitleAndDescription(String key) {
		QCourse course = QCourse.course;
		Predicate query = course.title.contains(key).or(course.description.contains(key));
		return query;
	}
}
