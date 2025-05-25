package com.ykomarnytskyi2022.dao.dto;

import jakarta.validation.constraints.NotNull;

public class DriverShipmentDto {
	@NotNull
	private Long id;
	
	@NotNull
	private Long driverId;
	
	private boolean isAccepted;
	
	public DriverShipmentDto() {
	}
	
	public DriverShipmentDto(Long id) {
		this.id = id;
	}
	
	
	public Long getId() {
		return id;
	}

	public Long getDriverId() {
		return driverId;
	}

	public void setDriverId(Long driverId) {
		this.driverId = driverId;
	}

	public boolean isAccepted() {
		return isAccepted;
	}

	public void setAccepted(boolean isAccepted) {
		this.isAccepted = isAccepted;
	}
}
