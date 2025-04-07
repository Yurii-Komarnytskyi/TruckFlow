package com.ykomarnytskyi2022.dao.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;

@Entity
public class LogisticsCoordinator extends Employee{
	
	public LogisticsCoordinator() {
		// default no-argument constructor for Spring JPA	
	}
	
	@OneToMany(mappedBy = "coordinator", fetch = FetchType.LAZY)
	private Set<Shipment> shipments = new HashSet<>();

	public Set<Shipment> getShipments() {
		return shipments;
	}

	public void setShipments(Set<Shipment> shipments) {
		this.shipments = shipments;
	}
	
	
}
