package springdata.jpa.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name="Tbl_Curriculum")
public class Curriculum extends AbstractEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ManyToMany
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(name="Tbl_CurriculumCourse", joinColumns=@JoinColumn(name="CurriculumId"), inverseJoinColumns=@JoinColumn(name="CourseId"))
	private Set<Course> courses;
	
	@OneToMany(mappedBy="curriculum", fetch=FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true)
	private Set<CurriculumRegistration> enrolledStudents;

	public Curriculum() {
	}
	
	public Curriculum(Long id, String title, String description) {
		super(id, title, description);
	}

	public Set<Course> getCourses() {
		return courses;
	}

	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}

	public Set<CurriculumRegistration> getEnrolledStudents() {
		return enrolledStudents;
	}

	public void setEnrolledStudents(Set<CurriculumRegistration> enrolledStudents) {
		this.enrolledStudents = enrolledStudents;
	}

	public Curriculum updateCurriculum(Curriculum curriculum) {
		this.setTitle(curriculum.getTitle());
		this.setDescription(curriculum.getDescription());
		this.getCourses().addAll(curriculum.getCourses());
		this.getCourses().retainAll(curriculum.getCourses());
		return this;
	}

	public boolean isStudentAlreadyEnrolled(Student student) {
		if(enrolledStudents == null) return false;
		for(CurriculumRegistration curReg : enrolledStudents) {
			if(curReg.getStudent().equals(student)) return true;
		}
		return false;
	}
	
}
