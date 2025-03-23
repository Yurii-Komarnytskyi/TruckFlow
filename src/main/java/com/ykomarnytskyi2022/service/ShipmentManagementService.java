package com.ykomarnytskyi2022.service;

import java.util.Optional;

import org.springframework.data.domain.Page;

import com.ykomarnytskyi2022.dao.dto.DriverDto;
import com.ykomarnytskyi2022.dao.dto.LogisticsCoordinatorDto;
import com.ykomarnytskyi2022.dao.dto.ShipmentDto;

public interface ShipmentManagementService {
	boolean assignDriverForShipment(DriverDto driver, Long shipmentId);
	boolean assignLogistCoordinatorForShipment(LogisticsCoordinatorDto logisticsCoordinator, Long shipmentId);
	Optional<ShipmentDto> findShipmentById(Long id);
	void updateShipment(ShipmentDto shipment);
	Page<ShipmentDto> getShipmentsAssignedToDriver(Long driverId);
}
