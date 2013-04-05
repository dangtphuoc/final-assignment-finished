package springdata.jpa.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;

import springdata.jpa.model.Course;
import springdata.jpa.model.QClassOffering;
import springdata.jpa.model.QCourse;

public class CourseRepositoryImpl extends QueryDslRepositorySupport implements CourseRepositoryCustom {

	public CourseRepositoryImpl() {
		super(Course.class);
	}
	
	@Override
	public List<Course> searchCourses(String key, Date startDate, Date endDate) {
		QCourse course = QCourse.course;
		QClassOffering classOffering = QClassOffering.classOffering;
		return from(course).innerJoin(course.classOfferings, classOffering)
		.where(course.title.containsIgnoreCase(key)
				.and(classOffering.startTime.goe(startDate))
						.and(classOffering.endTime.loe(endDate))).distinct().list(course);
	}

}
