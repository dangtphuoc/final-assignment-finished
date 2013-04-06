package springdata.jpa.model;

import java.io.Serializable;
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
@Table(name="Tbl_CurriculumRegistration")
@IdClass(CurriculumRegistrationId.class)
public class CurriculumRegistration implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5316662002869550876L;

	@Id
	@ManyToOne
	@JoinColumn(name="CurriculumId")
	private Curriculum curriculum;
	
	@Id
	@ManyToOne
	@JoinColumn(name="student")
	private Student student;

	@Temporal(TemporalType.DATE)
	@Column(name="EnrolledDate")
	private Date enrolledDate;
	
	public Date getEnrolledDate() {
		return enrolledDate;
	}

	public void setEnrolledDate(Date enrolledDate) {
		this.enrolledDate = enrolledDate;
	}

	public Curriculum getCurriculum() {
		return curriculum;
	}

	public void setCurriculum(Curriculum curriculum) {
		this.curriculum = curriculum;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
	
}
