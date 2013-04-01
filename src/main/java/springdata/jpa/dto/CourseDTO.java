package springdata.jpa.dto;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import springdata.jpa.model.ClassOffering;
import springdata.jpa.model.Course;

@XmlRootElement(name="course")
public class CourseDTO extends BaseDTO{
	protected List<ClassOfferingDTO> classOfferings;
	
	public CourseDTO() {
	}
	
	public CourseDTO(Course course) {
		super(course.getId(), course.getTitle(), course.getDescription());
		if(course.getClassOfferings() != null) {
			List<ClassOfferingDTO> classOfferingDTOs = new ArrayList<ClassOfferingDTO>();
			for(ClassOffering c : course.getClassOfferings()) {
				ClassOfferingDTO dto = new ClassOfferingDTO(c);
				classOfferingDTOs.add(dto);
			}
			setClassOfferings(classOfferingDTOs);
		}
	}

	public List<ClassOfferingDTO> getClassOfferings() {
		return classOfferings;
	}

	public void setClassOfferings(List<ClassOfferingDTO> classOfferings) {
		this.classOfferings = classOfferings;
	}

	/**
	 * transform the CourseDTO to Course
	 * @return Course
	 */
	public Course toCourse() {
		Course course = new Course(id, title, description);
		List<ClassOffering> classOfferings = new ArrayList<ClassOffering>();
		if(this.classOfferings != null) {
			for(ClassOfferingDTO c : this.classOfferings) {
				classOfferings.add(c.toClassOffering());
			}
		}
		course.setClassOfferings(classOfferings);
		return course;
	}
	
}
