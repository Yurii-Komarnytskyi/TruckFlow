package com.ykomarnytskyi2022.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.ykomarnytskyi2022.dao.dto.DriverDto;
import com.ykomarnytskyi2022.dao.dto.DriverShipmentDto;
import com.ykomarnytskyi2022.dao.dto.PageableDto;
import com.ykomarnytskyi2022.dao.dto.ShipmentDto;
import com.ykomarnytskyi2022.dao.entity.Driver;
import com.ykomarnytskyi2022.dao.repositories.DriverRepo;
import com.ykomarnytskyi2022.enums.ShipmentStatus;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Service
@Validated
public class DriverServiceImpl implements DriverService {
	private final DriverRepo driverRepo;
	private final ShipmentManagementService shipmentManagementService;
	private final ModelMapper mapper;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DriverServiceImpl.class);
	private static final int STANDARD_PAGE_SIZE = 12;

	@Autowired
	public DriverServiceImpl(DriverRepo driverRepo, 
			ShipmentManagementService shipmentManagementService,
			ModelMapper mapper) {
		this.driverRepo = driverRepo;
		this.shipmentManagementService = shipmentManagementService;
		this.mapper = mapper;
	}

	@Override
	public void acceptShipment(@Valid DriverShipmentDto driverShipment) {
		if(Objects.isNull(driverShipment)) {
			throw new IllegalArgumentException("driverShipment argument cannot be null");
		}
		shipmentManagementService.assignDriverForShipment(driverShipment, driverShipment.getId());
	}

	@Override
	public void updateProgressOnShipment(@NotNull Long shipmentId, ShipmentStatus status) {
		if(Objects.isNull(status)) {
			throw new IllegalArgumentException("status arg cannot be null");
		} else {
			ShipmentDto shipment = shipmentManagementService.findShipmentById(shipmentId)
					.orElseThrow(() -> new EntityNotFoundException("Shipment entity with Id: %s does not exist".formatted(shipmentId)));
			shipment.setStatus(status);
			shipmentManagementService.updateShipment(shipment);
			
		}
	}

	@Override
	public PageableDto<DriverShipmentDto> getDriverShipments(@NotNull Long driverId) {
		List<DriverShipmentDto> page = shipmentManagementService.getShipmentsAssignedToDriver(driverId).page().stream()
				.map(s -> mapper.map(s, DriverShipmentDto.class)).toList();
		return new PageableDto<>(page, page.size(), PageableDto.FIRST_PAGE_INDEX, PageableDto.calculateTotalPages(page.size(), STANDARD_PAGE_SIZE));
	}

	@Override
	public Optional<DriverDto> findDriverById(@NotNull Long driverId) {
		Optional<Driver> driverOpt = driverRepo.findById(driverId);
		return (driverOpt.isPresent())
				? Optional.of(mapDriverToDto(driverOpt.get())) 
				: Optional.empty();
	}

	@Override
	public Optional<DriverDto> findDriverByCellPhone(@NotNull String cellPhone) {
		Optional<Driver> driverOpt = driverRepo.findDriverByCellPhone(cellPhone);
		return (driverOpt.isPresent()) 
				? Optional.of(mapDriverToDto(driverOpt.get())) 
				: Optional.empty();
	}

	private final DriverDto mapDriverToDto(Driver driver) {
		return mapper.map(driver, DriverDto.class);
	}
}
