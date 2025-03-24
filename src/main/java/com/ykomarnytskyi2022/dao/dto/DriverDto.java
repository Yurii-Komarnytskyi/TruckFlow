package com.ykomarnytskyi2022.dao.dto;

import java.util.List;
import java.util.Objects;
import java.util.Set;

import com.ykomarnytskyi2022.dao.entity.BillOfLading;
import com.ykomarnytskyi2022.dao.entity.ProofOfDelivery;
import com.ykomarnytskyi2022.enums.DrivingLicence;

public class DriverDto {

	private Long id;
	
	private String name;
	
	private String lastName;
	
	private String cellPhone;
	
	
	private Integer age;
	
	private String email;
	
	private List<String> emergencyContacts;
	
	private Set<ProofOfDelivery> proofOfDeliveries;

	private Set<BillOfLading> billOfLadings;

	private Set<DrivingLicence> authorizedDrivingCategories;
	
	public DriverDto() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCellPhone() {
		return cellPhone;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<String> getEmergencyContacts() {
		return emergencyContacts;
	}

	public void setEmergencyContacts(List<String> emergencyContacts) {
		this.emergencyContacts = emergencyContacts;
	}

	public Set<ProofOfDelivery> getProofOfDeliveries() {
		return proofOfDeliveries;
	}

	public void setProofOfDeliveries(Set<ProofOfDelivery> proofOfDeliveries) {
		this.proofOfDeliveries = proofOfDeliveries;
	}

	public Set<BillOfLading> getBillOfLadings() {
		return billOfLadings;
	}

	public void setBillOfLadings(Set<BillOfLading> billOfLadings) {
		this.billOfLadings = billOfLadings;
	}

	public Set<DrivingLicence> getAuthorizedDrivingCategories() {
		return authorizedDrivingCategories;
	}

	public void setAuthorizedDrivingCategories(Set<DrivingLicence> authorizedDrivingCategories) {
		this.authorizedDrivingCategories = authorizedDrivingCategories;
	}

	@Override
	public String toString() {
		return "DriverDto [id=" + id + ", name=" + name + ", lastName=" + lastName + ", cellPhone=" + cellPhone
				+ ", age=" + age + ", email=" + email + ", emergencyContacts=" + emergencyContacts
				+ ", proofOfDeliveries=" + proofOfDeliveries + ", billOfLadings=" + billOfLadings
				+ ", authorizedDrivingCategories=" + authorizedDrivingCategories + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(age, authorizedDrivingCategories, billOfLadings, cellPhone, email, emergencyContacts, id,
				lastName, name, proofOfDeliveries);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof DriverDto)) {
			return false;
		}
		DriverDto other = (DriverDto) obj;
		return Objects.equals(age, other.age)
				&& Objects.equals(authorizedDrivingCategories, other.authorizedDrivingCategories)
				&& Objects.equals(billOfLadings, other.billOfLadings) && Objects.equals(cellPhone, other.cellPhone)
				&& Objects.equals(email, other.email) && Objects.equals(emergencyContacts, other.emergencyContacts)
				&& Objects.equals(id, other.id) && Objects.equals(lastName, other.lastName)
				&& Objects.equals(name, other.name) && Objects.equals(proofOfDeliveries, other.proofOfDeliveries);
	}
}
