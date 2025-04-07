package com.ykomarnytskyi2022.dao.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.ykomarnytskyi2022.enums.ShipmentStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Shipment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST})
	private Driver driver;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST})
	private LogisticsCoordinator coordinator;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Customer customer;
	
	@OneToOne(fetch = FetchType.LAZY)
	private BillOfLading billOfLading;
	
	@OneToOne(fetch = FetchType.LAZY)
	private ProofOfDelivery proofOfDelivery;
	
	@OneToMany(mappedBy = "shipment", fetch = FetchType.LAZY)
	private List<RoadAccident> roadAccidents = new ArrayList<>();
	
	@Enumerated(EnumType.STRING)
	private ShipmentStatus status;
	
	public Shipment() {
		// default no-argument constructor for Spring JPA	
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	public BillOfLading getBillOfLading() {
		return billOfLading;
	}

	public void setBillOfLading(BillOfLading billOfLading) {
		this.billOfLading = billOfLading;
	}

	public ProofOfDelivery getProofOfDelivery() {
		return proofOfDelivery;
	}

	public void setProofOfDelivery(ProofOfDelivery proofOfDelivery) {
		this.proofOfDelivery = proofOfDelivery;
	}

	public ShipmentStatus getStatus() {
		return status;
	}

	public void setStatus(ShipmentStatus status) {
		this.status = status;
	}

	public LogisticsCoordinator getCoordinator() {
		return coordinator;
	}

	public void setCoordinator(LogisticsCoordinator coordinator) {
		this.coordinator = coordinator;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<RoadAccident> getRoadAccidents() {
		return roadAccidents;
	}

	public void setRoadAccidents(List<RoadAccident> roadAccidents) {
		this.roadAccidents = roadAccidents;
	}
	
	public boolean hasDriverAssigned() {
		return Objects.nonNull(driver) && Objects.nonNull(driver.getId());
	}
}
