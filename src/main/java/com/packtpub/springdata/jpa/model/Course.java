package com.packtpub.springdata.jpa.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@XmlRootElement(name="course")
@Table(name="Tbl_Course")
public class Course extends AbstractBean {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@ManyToMany(mappedBy="courses",  fetch=FetchType.LAZY)
	private List<Curriculum> curricula;
	
	@OneToMany(mappedBy="course", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private List<ClassOffering> classOfferings;
	
	public List<ClassOffering> getClassOfferings() {
		return classOfferings;
	}

	public void setClassOfferings(List<ClassOffering> classOfferings) {
		this.classOfferings = classOfferings;
	}

	public Course() {
	}
	
	public Course(String title, String description) {
		super(title, description);
	}

	public List<Curriculum> getCurricula() {
		return curricula;
	}

	public void setCurricula(List<Curriculum> curricula) {
		this.curricula = curricula;
	}

	public void removeClassOffering(ClassOffering cla) {
		this.classOfferings.remove(cla);
		cla.setCourse(null);
	}

	public void addClassOffering(ClassOffering classOffering) {
		if(this.classOfferings == null) this.classOfferings = new ArrayList<ClassOffering>();
		this.classOfferings.add(classOffering);
		classOffering.setCourse(this);
	}

	public List<ClassOffering> cloneClassOfferings() {
		List<ClassOffering> offerings = new ArrayList<ClassOffering>();
		if(this.classOfferings != null) {
			for(ClassOffering c : this.classOfferings) {
				offerings.add(c);
			}
		}
		return offerings;
	}
}
