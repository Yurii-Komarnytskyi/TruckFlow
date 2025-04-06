package com.ykomarnytskyi2022.service;

import java.util.Optional;

import org.springframework.data.domain.Pageable;

import com.ykomarnytskyi2022.dao.dto.DriverDto;
import com.ykomarnytskyi2022.dao.dto.LogisticsCoordinatorDto;
import com.ykomarnytskyi2022.dao.dto.PageableDto;
import com.ykomarnytskyi2022.dao.dto.ShipmentDto;
import com.ykomarnytskyi2022.enums.ShipmentStatus;

public interface ShipmentManagementService {
	void assignDriverForShipment(DriverDto driver, Long shipmentId);
	void assignLogisticsCoordinatorForShipment(LogisticsCoordinatorDto logisticsCoordinator, Long shipmentId);
	Optional<ShipmentDto> findShipmentById(Long id);
	ShipmentDto createShipment(ShipmentDto shipment);
	void updateShipment(ShipmentDto shipment);
	void cancelShipment(Long shipmentId);
	void deleteShipment(Long shipmentId);
	void updateShipmentStatus(Long shipmentId, ShipmentStatus status);
	PageableDto<ShipmentDto> getShipmentsAssignedToDriver(Long driverId);
	PageableDto<ShipmentDto> getShipmentsAssignedToCoordinator(Long coordinatorId);
	PageableDto<ShipmentDto> getAllShipments(Pageable pageable);
	PageableDto<ShipmentDto> getShipmentsByStatus(ShipmentStatus status);
	PageableDto<ShipmentDto> getShipmentsForLogisticsCoordinator(Long logisticsCoordinatorId);
	PageableDto<ShipmentDto> getShipmentsAssignedToCustomer(Long customerId);
	PageableDto<ShipmentDto> searchShipments(ShipmentSearchCriteria criteria, Pageable pageable);
}
