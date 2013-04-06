package springdata.jpa.model;

import java.io.Serializable;

public class ClassOfferingRegistrationId implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7875638913414266481L;
	
	private Long classOffering;
	private Long student;
	
	public Long getClassOffering() {
		return classOffering;
	}
	public void setClassOffering(Long classOffering) {
		this.classOffering = classOffering;
	}
	public Long getStudent() {
		return student;
	}
	public void setStudent(Long student) {
		this.student = student;
	}

}
