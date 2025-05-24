package com.ykomarnytskyi2022.dao.dto;

import java.time.LocalDate;

import com.ykomarnytskyi2022.enums.ShipmentStatus;

public record ShipmentSearchCriteriaDto(
		Long id, 
		String origin, 
		String destination,
		ShipmentStatus status,
		LocalDate pickedUpAt , 
		LocalDate deliveredAt) {
	
	public boolean hasIdCriteria() {
		return id != null;
	}

	public boolean hasOriginCriteria() {
		return origin != null;
	}

	public boolean hasDestinationCriteria() {
		return destination != null;
	}

	public boolean hasStatusCriteria() {
		return status != null;
	}

	public boolean hasPickedUpAtCriteria() {
		return pickedUpAt != null;
	}
	public boolean hasDeliveredAtCriteria() {
		return deliveredAt != null;
	}
	
}
