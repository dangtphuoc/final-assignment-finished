package com.packtpub.springdata.jpa.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Table(name="Tbl_Role")
public class Role extends AbstractBean {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8301074172411393574L;
	
	public Role() {
	}
	
	@JsonIgnore
	@ManyToMany(mappedBy="roles", fetch=FetchType.LAZY)
	private Set<Student> students;

	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}
	
}
