package springdata.jpa.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.bind.annotation.XmlRootElement;

import springdata.jpa.model.Role;
import springdata.jpa.model.Student;

@XmlRootElement(name="student")
public class StudentDTO extends BaseDTO {
	
	private String firstName;
	
	private String lastName;
	
	private Date birthday;
	
	private StudentDTO manager;
	
	private List<RoleDTO> roles;
	
	public StudentDTO() {
	}
	
	public StudentDTO(Student instructor) {
		super(instructor.getId(), instructor.getFirstName(), instructor.getLastName());
		setBirthday(instructor.getBirthday());
		List<RoleDTO> roles = new ArrayList<RoleDTO>();
		if(instructor.getRoles() != null) {
			for(Role r : instructor.getRoles()) {
				RoleDTO roleDTO = new RoleDTO(r.getId(), r.getTitle(), r.getDescription());
				roles.add(roleDTO);
			}
		}
		setRoles(roles);
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
	public StudentDTO getManager() {
		return manager;
	}
	public void setManager(StudentDTO manager) {
		this.manager = manager;
	}
	public List<RoleDTO> getRoles() {
		return roles;
	}
	public void setRoles(List<RoleDTO> roles) {
		this.roles = roles;
	}
	
	/**
	 * Transform StudentDTO to Student
	 * @return Student
	 */
	public Student toStudent() {
		Student student = new Student(id, firstName, lastName);
		student.setBirthday(birthday);
		if(manager != null) {
			student.setManager(manager.toStudent());
		}
		
		Set<Role> roles = new HashSet<Role>();
		if(this.roles != null) {
			for(RoleDTO r : this.roles) {
				Role role = new Role(r.getId(), r.getTitle(), r.getDescription());
				roles.add(role);
			}
		}
		student.setRoles(roles);
		return student;
	}
}
