package springdata.jpa.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Table(name="Tbl_Student")
public class Student extends AbstractBean {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7762883391696239894L;

	@Column(name="FirstName")
	private String firstName;
	
	@Column(name="LastName")
	private String lastName;
	
	@Column(name="Birthday")
	private Date birthday;
	
	@JsonIgnore
	@OneToMany(mappedBy="instructor", fetch=FetchType.LAZY)
	private Set<ClassOffering> classOfferings;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="ManagerId")
	private Student manager;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="Tbl_StudentRole", joinColumns=@JoinColumn(name="StudentId"), inverseJoinColumns=@JoinColumn(name="RoleId"))
	private Set<Role> roles;
	
	public Student() {
	}
	
	public Student(Long id, String firstName, String lastName) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public Set<ClassOffering> getClassOfferings() {
		return classOfferings;
	}
	public void setClassOfferings(Set<ClassOffering> classOfferings) {
		this.classOfferings = classOfferings;
	}
	public Student getManager() {
		return manager;
	}
	public void setManager(Student manager) {
		this.manager = manager;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
}
