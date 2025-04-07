package com.ykomarnytskyi2022.dao.dto;

import com.ykomarnytskyi2022.enums.ShipmentStatus;

public class ShipmentDto {
	private ShipmentStatus status;

	private DriverDto driver;
	
	private LogisticsCoordinatorDto coordinator;

	public ShipmentStatus getStatus() {
		return status;
	}

	public void setStatus(ShipmentStatus status) {
		this.status = status;
	}

	public DriverDto getDriver() {
		return driver;
	}

	public void setDriver(DriverDto driver) {
		this.driver = driver;
	}

	public LogisticsCoordinatorDto getCoordinator() {
		return coordinator;
	}

	public void setCoordinator(LogisticsCoordinatorDto coordinator) {
		this.coordinator = coordinator;
	}
}
