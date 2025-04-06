package com.ykomarnytskyi2022.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ykomarnytskyi2022.dao.dto.DriverDto;
import com.ykomarnytskyi2022.dao.dto.LogisticsCoordinatorDto;
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
	Page<ShipmentDto> getShipmentsAssignedToDriver(Long driverId);
	Page<ShipmentDto> getShipmentsAssignedToCoordinator(Long coordinatorId);
	Page<ShipmentDto> getAllShipments(Pageable pageable);
	Page<ShipmentDto> getShipmentsByStatus(ShipmentStatus status);
	Page<ShipmentDto> getShipmentsForLogisticsCoordinator(Long logisticsCoordinatorId);
	Page<ShipmentDto> getShipmentsAssignedToCustomer(Long customerId);
	Page<ShipmentDto> searchShipments(ShipmentSearchCriteria criteria, Pageable pageable);
}
