package com.packtpub.springdata.jpa.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Table(name="Tbl_Location")
public class Location extends AbstractBean {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Address address;
	
	@Column(name="Telephone")
	private String telephone;
	
	@Column(name="ContactPerson")
	private String contactPerson;
	
	@JsonIgnore
	@OneToMany(mappedBy="location", fetch=FetchType.LAZY)
	private List<ClassOffering> classOfferings;
	
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
}
