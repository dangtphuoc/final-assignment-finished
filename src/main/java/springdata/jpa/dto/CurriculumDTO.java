package springdata.jpa.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.bind.annotation.XmlRootElement;

import springdata.jpa.model.Course;
import springdata.jpa.model.Curriculum;

@XmlRootElement(name="curriculum")
public class CurriculumDTO extends BaseDTO {
	
	private List<CourseDTO> courses;
	
	public CurriculumDTO() {
	}
	
	public CurriculumDTO(Curriculum curriculum) {
		super(curriculum.getId(), curriculum.getTitle(), curriculum.getDescription());
		courses = new ArrayList<CourseDTO>();
		if(curriculum.getCourses() != null) {
			for(Course course : curriculum.getCourses()) {
				courses.add(new CourseDTO(course.getId(), course.getTitle(), course.getDescription()));
			}
		}
	}
	public List<CourseDTO> getCourses() {
		return courses;
	}

	public void setCourses(List<CourseDTO> courses) {
		this.courses = courses;
	}

	public Curriculum toCurriculum() {
		Curriculum curriculum = new Curriculum(getId(), getTitle(), getDescription());
		
		Set<Course> courses = new HashSet<Course>();
		if(courses != null) {
			for(CourseDTO courseDTO : this.courses) {
				courses.add(courseDTO.toCourse());
			}
			curriculum.setCourses(courses);
		}
		return curriculum;
	}
}
