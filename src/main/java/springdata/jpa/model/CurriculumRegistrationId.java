package springdata.jpa.model;

import java.io.Serializable;

public class CurriculumRegistrationId implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7441455377546510054L;
	private Long curriculum;
	private Long student;
	public Long getCurriculum() {
		return curriculum;
	}
	public void setCurriculum(Long curriculum) {
		this.curriculum = curriculum;
	}
	public Long getStudent() {
		return student;
	}
	public void setStudent(Long student) {
		this.student = student;
	}
}
