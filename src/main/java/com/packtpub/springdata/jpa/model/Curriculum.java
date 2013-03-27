package com.packtpub.springdata.jpa.model;

import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="Tbl_Curriculum")
public class Curriculum extends AbstractBean {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="Tbl_CurriculumCourse", joinColumns=@JoinColumn(name="CurriculumId"), inverseJoinColumns=@JoinColumn(name="CourseId"))
	private List<Course> courses;

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
	
}
