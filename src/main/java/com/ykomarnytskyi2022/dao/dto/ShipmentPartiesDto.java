package com.ykomarnytskyi2022.dao.dto;

import java.util.Objects;

public record ShipmentPartiesDto(DriverDto driver, LogisticsCoordinatorDto coordinator, CustomerDto customer) {
	public boolean hasDriver() {
		return Objects.nonNull(driver);
	}

	public boolean hasCoordinator() {
		return Objects.nonNull(coordinator);
	}

	public boolean hasCustomer() {
		return Objects.nonNull(customer);
	}
}
