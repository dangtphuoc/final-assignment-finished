package springdata.jpa.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Table(name="Tbl_Location")
public class Location extends AbstractEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name="Telephone")
	private String telephone;
	
	@Column(name="ContactPerson")
	private String contactPerson;
	
	@JsonIgnore
	@OneToMany(mappedBy="location", fetch=FetchType.LAZY)
	private List<ClassOffering> classOfferings;
	
	public Location() {
		
	}
	public Location(Long id, String title, String description) {
		super(id, title, description);
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getContactPerson() {
		return contactPerson;
	}
	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}
	public List<ClassOffering> getClassOfferings() {
		return classOfferings;
	}
	public void setClassOfferings(List<ClassOffering> classOfferings) {
		this.classOfferings = classOfferings;
	}
}
