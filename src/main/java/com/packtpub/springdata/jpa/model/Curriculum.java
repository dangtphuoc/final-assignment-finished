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

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name="Tbl_Curriculum")
public class Curriculum extends AbstractBean {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ManyToMany()
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(name="Tbl_CurriculumCourse", joinColumns=@JoinColumn(name="CurriculumId"), inverseJoinColumns=@JoinColumn(name="CourseId"))
	private Set<Course> courses;

	public Set<Course> getCourses() {
		return courses;
	}

	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}

	public Curriculum updateCurriculum(Curriculum curriculum) {
		this.setTitle(curriculum.getTitle());
		this.setDescription(curriculum.getDescription());
		this.getCourses().addAll(curriculum.getCourses());
		this.getCourses().retainAll(curriculum.getCourses());
		return this;
	}
	
}
