package springdata.jpa.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import springdata.jpa.config.CustomDateSerializer;


@Entity
@XmlRootElement(name="classOffering")
@Table(name="Tbl_ClassOffering")
public class ClassOffering extends AbstractEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="locationId")
	private Location location;
	
	@JsonSerialize(using=CustomDateSerializer.class)
	@Column(name="StartTime")
	private Date startTime;
	
	@JsonSerialize(using=CustomDateSerializer.class)
	@Column(name="EndTime")
	private Date endTime;
	
	@JsonIgnore
	@ManyToOne(fetch=FetchType.EAGER, optional=false)
	@JoinColumn(name="CourseId")
	private Course course;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="InstructorId")
	private Student instructor;
	
	@OneToMany(mappedBy="classOffering", fetch=FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true)
	private Set<ClassOfferingRegistration> enrolledStudents;
	
	public ClassOffering() {
	}
	
	public ClassOffering(String title, String description) {
		super(title, description);
	}
	
	public ClassOffering(Long id, String title, String description) {
		super(id, title, description);
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Student getInstructor() {
		return instructor;
	}

	public void setInstructor(Student instructor) {
		this.instructor = instructor;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Set<ClassOfferingRegistration> getEnrolledStudents() {
		return enrolledStudents;
	}

	public void setEnrolledStudents(Set<ClassOfferingRegistration> enrolledStudents) {
		this.enrolledStudents = enrolledStudents;
	}
	
	public boolean isStudentAlreadyEnrolled(Student student) {
		if(enrolledStudents == null) return false;
		for(ClassOfferingRegistration claReg : enrolledStudents) {
			if(claReg.getStudent().equals(student)) return true;
		}
		return false;
	}
}
