package springdata.jpa.repository;

import java.util.Date;
import java.util.List;

import springdata.jpa.model.ClassOffering;
import springdata.jpa.model.Course;


public interface CourseRepositoryCustom {

	public List<Course> searchCourses(String key, Date startDate, Date endDate);

}
