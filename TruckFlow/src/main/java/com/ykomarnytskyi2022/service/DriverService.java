package com.ykomarnytskyi2022.service;

import java.util.Optional;

import com.ykomarnytskyi2022.dao.dto.DriverDto;
import com.ykomarnytskyi2022.dao.dto.DriverShipmentDto;
import com.ykomarnytskyi2022.dao.dto.PageableDto;
import com.ykomarnytskyi2022.enums.ShipmentStatus;

public interface DriverService {
	void acceptShipment(DriverShipmentDto driverShipment);
	void updateProgressOnShipment(Long shipmentId, ShipmentStatus status);
	PageableDto<DriverShipmentDto> getDriverShipments(Long driverId);
	Optional<DriverDto> findDriverById(Long driverId);
	Optional<DriverDto> findDriverByCellPhone(String cellPhone);
}
