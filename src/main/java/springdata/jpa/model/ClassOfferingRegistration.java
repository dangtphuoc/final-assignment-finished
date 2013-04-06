package springdata.jpa.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="Tbl_ClassOfferingRegistration")
@IdClass(ClassOfferingRegistrationId.class)
public class ClassOfferingRegistration {
	
	@Id
	@ManyToOne
	@JoinColumn(name="ClassOfferingId")
	private ClassOffering classOffering;
	
	@Id
	@ManyToOne
	@JoinColumn(name="StudentId")
	private Student student;
	
	@Temporal(TemporalType.DATE)
	@Column(name="EnrolledDate", updatable=false)
	private Date enrolledDate;
	
	public ClassOffering getClassOffering() {
		return classOffering;
	}
	public void setClassOffering(ClassOffering classOffering) {
		this.classOffering = classOffering;
	}
	public Date getEnrolledDate() {
		return enrolledDate;
	}
	public void setEnrolledDate(Date enrolledDate) {
		this.enrolledDate = enrolledDate;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	
}
