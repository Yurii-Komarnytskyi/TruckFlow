package com.ykomarnytskyi2022.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.ykomarnytskyi2022.dao.dto.DriverDto;
import com.ykomarnytskyi2022.dao.dto.LogisticsCoordinatorDto;
import com.ykomarnytskyi2022.dao.dto.ShipmentDto;

@Service
public class ShipmentManagementServiceImpl implements ShipmentManagementService {

	@Override
	public boolean assignDriverForShipment(DriverDto driver, Long shipmentId) {
		return false;
	}

	@Override
	public boolean assignLogistCoordinatorForShipment(LogisticsCoordinatorDto logisticsCoordinator, Long shipmentId) {
		return false;
	}

	@Override
	public Optional<ShipmentDto> findShipmentById(Long id) {
		return Optional.empty();
	}

	@Override
	public void updateShipment(ShipmentDto shipment) {

	}

	@Override
	public Page<ShipmentDto> getShipmentsAssignedToDriver(Long driverId) {
		return null;
	}

}
