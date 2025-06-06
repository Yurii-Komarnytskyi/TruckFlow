package com.ykomarnytskyi2022.dao.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import com.ykomarnytskyi2022.enums.DrivingLicence;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Driver extends Employee {
	@OneToMany(mappedBy = "driver", fetch = FetchType.LAZY)
	@Column(name = "proof_of_deliveries")
	private Set<ProofOfDelivery> proofOfDeliveries = new HashSet<>();

	@OneToMany(mappedBy = "driver", fetch = FetchType.LAZY)
	@Column(name = "bill_of_ladings")
	private Set<BillOfLading> billOfLadings = new HashSet<>();

	@Enumerated(EnumType.STRING)
	@ElementCollection
	@Column(name = "driving_categories")
	private Set<DrivingLicence> authorizedDrivingCategories = new HashSet<>();
	
	@OneToOne
	private FreightVehicle currentlyOperatedVehicle;
	
	@OneToMany(mappedBy = "driver", fetch = FetchType.LAZY)
	private List<RoadAccident> roadAccidents = new ArrayList<>();
	
	@OneToMany(mappedBy = "driver", fetch = FetchType.LAZY)
	private List<Shipment> shipments = new ArrayList<>();

	public Driver() {
		// default no-argument constructor for Spring JPA
	}

	public Driver(Long id) {
		this.id = id;
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
	
	public FreightVehicle getCurrentlyOperatedVehicle() {
		return currentlyOperatedVehicle;
	}

	public void setCurrentlyOperatedVehicle(FreightVehicle currentlyOperatedVehicle) {
		this.currentlyOperatedVehicle = currentlyOperatedVehicle;
	}
	

	public List<RoadAccident> getRoadAccidents() {
		return roadAccidents;
	}

	public void setRoadAccidents(List<RoadAccident> roadAccidents) {
		this.roadAccidents = roadAccidents;
	}

	public List<Shipment> getShipments() {
		return shipments;
	}

	public void setShipments(List<Shipment> shipments) {
		this.shipments = shipments;
	}

	@Override
	public String toString() {
		return "Driver [authorizedDrivingCategories=" + authorizedDrivingCategories + ", id=" + id + ", name=" + name
				+ ", lastName=" + lastName + ", cellPhone=" + cellPhone + ", birthDate=" + birthDate + ", sex=" + sex
				+ ", age=" + age + ", email=" + email + "]";
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (!(obj instanceof Driver)) {
			return false;
		}
		Driver other = (Driver) obj;
		return Objects.equals(authorizedDrivingCategories, other.authorizedDrivingCategories);
	}
}
