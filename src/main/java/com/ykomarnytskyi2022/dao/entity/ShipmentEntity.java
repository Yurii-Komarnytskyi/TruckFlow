package com.ykomarnytskyi2022.dao.entity;

import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

public class ShipmentEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne(fetch = FetchType.LAZY)
	private Driver driver;
	
	@OneToOne(fetch = FetchType.LAZY)
	private BillOfLading billOfLading;
	
	@OneToOne(fetch = FetchType.LAZY)
	private ProofOfDelivery proofOfDelivery;
	
	public ShipmentEntity() {
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
}
