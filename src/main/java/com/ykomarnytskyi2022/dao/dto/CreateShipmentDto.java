package com.ykomarnytskyi2022.dao.dto;

import java.util.Optional;

public class CreateShipmentDto extends ShipmentDto {
	private Optional<DriverContactInfoDto> driverOptional;
	private Optional<CustomerDto> customerOptional;
	private Optional<LogisticsCoordinatorDto> logisticsCoordinatorOptional;

	public Optional<DriverContactInfoDto> getDriverOptional() {
		return driverOptional;
	}

	public void setDriverOptional(DriverContactInfoDto driver) {
		this.driverOptional = Optional.of(driver);
	}

	public Optional<CustomerDto> getCustomerOptional() {
		return customerOptional;
	}

	public void setCustomerOptional(CustomerDto customer) {
		this.customerOptional = Optional.of(customer);
	}

	public Optional<LogisticsCoordinatorDto> getLogisticsCoordinatorOptional() {
		return logisticsCoordinatorOptional;
	}

	public void setLogisticsCoordinatorOptional(LogisticsCoordinatorDto logisticsCoordinator) {
		this.logisticsCoordinatorOptional = Optional.of(logisticsCoordinator);
	}

	public boolean isDriverPresent() {
		return driverOptional.isPresent();
	}

	public boolean isCustomerPresent() {
		return customerOptional.isPresent();
	}

	public boolean isLogisticsCoordinatorPresent() {
		return logisticsCoordinatorOptional.isPresent();
	}
}
