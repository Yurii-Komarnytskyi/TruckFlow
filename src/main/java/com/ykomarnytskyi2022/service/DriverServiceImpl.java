package com.ykomarnytskyi2022.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ykomarnytskyi2022.dao.dto.DriverDto;
import com.ykomarnytskyi2022.dao.dto.DriverShipmentDto;
import com.ykomarnytskyi2022.dao.dto.PageableDto;
import com.ykomarnytskyi2022.dao.dto.ShipmentDto;
import com.ykomarnytskyi2022.dao.entity.Driver;
import com.ykomarnytskyi2022.dao.repositories.DriverRepo;
import com.ykomarnytskyi2022.enums.ShipmentStatus;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@Service
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
	public void acceptShipment(@Valid DriverShipmentDto driverShipment) throws EntityNotFoundException , UnsupportedOperationException {
		try {
			DriverDto driver = mapper.map(driverRepo.findById(driverShipment.getDriverId()).orElseThrow(), DriverDto.class);
			shipmentManagementService.assignDriverForShipment(driver, driverShipment.getId());
		} catch (NoSuchElementException e) {
			throw new EntityNotFoundException("Driver entity with Id : %s does not exist".formatted(driverShipment.getDriverId()));
		} catch (EntityNotFoundException | UnsupportedOperationException e) {
			throw e;
		}
				
	}

	@Override
	public void updateProgressOnShipment(Long shipmentId, ShipmentStatus status) {
		Optional<ShipmentDto> shipmentOptional = shipmentManagementService.findShipmentById(shipmentId);
		if (shipmentOptional.isPresent()) {
			ShipmentDto shipment = shipmentOptional.get();
			shipment.setStatus(status);
			shipmentManagementService.updateShipment(shipment);
		}
	}

	@Override
	public PageableDto<DriverShipmentDto> getDriverShipments(Long driverId) {
		List<DriverShipmentDto> page = shipmentManagementService.getShipmentsAssignedToDriver(driverId).page().stream()
				.map(s -> mapper.map(s, DriverShipmentDto.class)).toList();
		return new PageableDto<>(page, page.size(), PageableDto.FIRST_PAGE_INDEX, PageableDto.calculateTotalPages(page.size(), STANDARD_PAGE_SIZE));
	}

	@Override
	public Optional<DriverDto> findDriverById(Long driverId) {
		return Optional.of(mapDriverToDto(driverRepo.getReferenceById(driverId)));
	}

	@Override
	public Optional<DriverDto> findDriverByCellPhone(String cellPhone) {
		return Optional.ofNullable(mapDriverToDto(driverRepo.findDriverByCellPhone(cellPhone).orElseThrow()));
	}

	private final DriverDto mapDriverToDto(Driver driver) {
		return mapper.map(driver, DriverDto.class);
	}
}
