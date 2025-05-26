package com.ykomarnytskyi2022.service;

import java.util.Optional;

import org.springframework.data.domain.Pageable;

import com.ykomarnytskyi2022.dao.dto.CreateShipmentDto;
import com.ykomarnytskyi2022.dao.dto.DriverShipmentDto;
import com.ykomarnytskyi2022.dao.dto.LogisticsCoordinatorDto;
import com.ykomarnytskyi2022.dao.dto.PageableDto;
import com.ykomarnytskyi2022.dao.dto.ShipmentDto;
import com.ykomarnytskyi2022.dao.dto.ShipmentSearchCriteriaDto;
import com.ykomarnytskyi2022.enums.ShipmentStatus;

public interface ShipmentManagementService {
	void assignDriverForShipment(DriverShipmentDto driver, Long shipmentId);
	void assignLogisticsCoordinatorForShipment(LogisticsCoordinatorDto logisticsCoordinator, Long shipmentId);
	Optional<ShipmentDto> findShipmentById(Long id);
	CreateShipmentDto createShipment(CreateShipmentDto shipmentDto);
	void updateShipment(ShipmentDto shipment);
	void cancelShipment(Long shipmentId);
	void deleteShipment(Long shipmentId);
	void updateShipmentStatus(Long shipmentId, ShipmentStatus status);
	PageableDto<ShipmentDto> getShipmentsAssignedToDriver(Long driverId);
	PageableDto<ShipmentDto> getShipmentsAssignedToCoordinator(Long coordinatorId);
	PageableDto<ShipmentDto> getAllShipments(Pageable pageable);
	PageableDto<ShipmentDto> getShipmentsByStatus(ShipmentStatus status);
	PageableDto<ShipmentDto> getShipmentsAssignedToCustomer(Long customerId);
	PageableDto<ShipmentDto> searchShipments(ShipmentSearchCriteriaDto criteria, Pageable pageable);
}
