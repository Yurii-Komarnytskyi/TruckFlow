package com.ykomarnytskyi2022.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ykomarnytskyi2022.dao.dto.DriverDto;
import com.ykomarnytskyi2022.dao.dto.LogisticsCoordinatorDto;
import com.ykomarnytskyi2022.dao.dto.PageableDto;
import com.ykomarnytskyi2022.dao.dto.ShipmentDto;
import com.ykomarnytskyi2022.dao.entity.Driver;
import com.ykomarnytskyi2022.dao.entity.LogisticsCoordinator;
import com.ykomarnytskyi2022.dao.entity.Shipment;
import com.ykomarnytskyi2022.dao.repositories.ShipmentRepo;
import com.ykomarnytskyi2022.enums.ShipmentStatus;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Service
public class ShipmentManagementServiceImpl implements ShipmentManagementService {

	private final ShipmentRepo shipmentRepo;
	private final ModelMapper mapper;
	private static final Logger logger = LoggerFactory.getLogger(ShipmentManagementServiceImpl.class);
	private static final int STANDARD_PAGE_SIZE = 20;

	@Autowired
	public ShipmentManagementServiceImpl(ShipmentRepo shipmentRepo, ModelMapper mapper) {
		this.shipmentRepo = shipmentRepo;
		this.mapper = mapper;
	}

	@Override
	public void assignDriverForShipment(@Valid DriverDto driver, @NotNull Long id)
			throws EntityNotFoundException, UnsupportedOperationException {
		Shipment shipment = getShipmentByIdOrThrowEntityNotFound(id);
		if (shipment.hasDriverAssigned() && !shipment.getDriver().getId().equals(driver.getId())) {
			throw new UnsupportedOperationException(
					"Shipment entity with Id: %s already has a driver assigned to it".formatted(id));
		}
		shipment.setDriver(mapper.map(driver, Driver.class));
		shipmentRepo.save(shipment);
	}

	@Override
	public Optional<ShipmentDto> findShipmentById(Long id) {
		return shipmentRepo.findById(id).map(this::mapShipmentToDto);
	}

	@Override
	public void updateShipment(@Valid ShipmentDto dto) throws EntityNotFoundException {
		Shipment shipment = shipmentRepo.getReferenceById(dto.getId());
		shipment.setCreatedAt(dto.getCreatedAt());
		shipment.setDeliveredAt(dto.getDeliveredAt());
		shipment.setDestination(dto.getDestination());
		shipment.setGoodsDescription(dto.getGoodsDescription());
		shipment.setOrigin(dto.getOrigin());
		shipment.setPickedUpAt(dto.getPickedUpAt());
		shipment.setStatus(dto.getStatus());
		shipment.setUpdatedAt(dto.getUpdatedAt());
		shipmentRepo.save(shipment);
	}

	@Override
	public PageableDto<ShipmentDto> getShipmentsAssignedToDriver(Long driverId) {
		List<ShipmentDto> page = shipmentRepo.findAllByDriverId(driverId).stream().map(this::mapShipmentToDto).toList();
		return getStandardSizedPageableDto(page);
	}

	@Override
	public ShipmentDto createShipment(ShipmentDto shipment) {
		return null;
	}

	@Override
	public void cancelShipment(Long shipmentId) throws EntityNotFoundException {
		updateShipmentStatus(shipmentId, ShipmentStatus.CANCELLED);
	}

	@Override
	public void updateShipmentStatus(@NotNull Long shipmentId, @NotNull ShipmentStatus status)
			throws EntityNotFoundException {
		Shipment shipment = getShipmentByIdOrThrowEntityNotFound(shipmentId);
		shipment.setStatus(status);
		shipmentRepo.save(shipment);
	}

	@Override
	public PageableDto<ShipmentDto> getShipmentsAssignedToCoordinator(Long id) {
		List<ShipmentDto> page = shipmentRepo.findAllByCoordinatorId(id).stream().map(this::mapShipmentToDto).toList();
		return getStandardSizedPageableDto(page);
	}

	@Override
	public PageableDto<ShipmentDto> getAllShipments(Pageable pageable) {
		List<ShipmentDto> page = shipmentRepo.findAll().stream().map(this::mapShipmentToDto).toList();
		return getStandardSizedPageableDto(page);
	}

	@Override
	public PageableDto<ShipmentDto> getShipmentsByStatus(ShipmentStatus status) {
		List<ShipmentDto> page = shipmentRepo.findAllByStatus(status).stream().map(this::mapShipmentToDto).toList();
		return getStandardSizedPageableDto(page);
	}

	@Override
	public PageableDto<ShipmentDto> getShipmentsAssignedToCustomer(Long id) {
		List<ShipmentDto> page = shipmentRepo.findAllByCustomerId(id).stream().map(this::mapShipmentToDto).toList();
		return getStandardSizedPageableDto(page);
	}

	@Override
	public PageableDto<ShipmentDto> searchShipments(ShipmentSearchCriteria criteria, Pageable pageable) {
		return null;
	}

	@Override
	public void assignLogisticsCoordinatorForShipment(@Valid LogisticsCoordinatorDto coordinator, @NotNull Long id)
			throws EntityNotFoundException {
		Shipment shipment = getShipmentByIdOrThrowEntityNotFound(id);
		shipment.setCoordinator(mapper.map(coordinator, LogisticsCoordinator.class));
		shipmentRepo.save(shipment);

	}

	@Override
	public void deleteShipment(@NotNull Long shipmentId) throws EntityNotFoundException {
		getShipmentByIdOrThrowEntityNotFound(shipmentId);
		shipmentRepo.deleteById(shipmentId);
	}

	private Shipment getShipmentByIdOrThrowEntityNotFound(Long id)
			throws EntityNotFoundException, IllegalArgumentException {
		Shipment shipment = null;
		try {
			Optional<Shipment> optional = shipmentRepo.findById(id);
			if (optional.isEmpty()) {
				throw new EntityNotFoundException("Shipment with Id: %s does not exist".formatted(id));
			}
			shipment = optional.get();
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Shipment Id parameter cannot be null");
		}
		return shipment;
	}

	private static <T> PageableDto<T> getStandardSizedPageableDto(List<T> page) {
		return new PageableDto<>(page, page.size(), STANDARD_PAGE_SIZE,
				PageableDto.calculateTotalPages(page.size(), STANDARD_PAGE_SIZE));
	}

	private ShipmentDto mapShipmentToDto(Shipment shipment) {
		return mapper.map(shipment, ShipmentDto.class);
	}
}