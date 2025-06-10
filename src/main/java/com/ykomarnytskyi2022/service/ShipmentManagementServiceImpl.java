package com.ykomarnytskyi2022.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.ykomarnytskyi2022.dao.dto.CreateShipmentDto;
import com.ykomarnytskyi2022.dao.dto.DriverDto;
import com.ykomarnytskyi2022.dao.dto.DriverShipmentDto;
import com.ykomarnytskyi2022.dao.dto.LogisticsCoordinatorDto;
import com.ykomarnytskyi2022.dao.dto.PageableDto;
import com.ykomarnytskyi2022.dao.dto.ShipmentDto;
import com.ykomarnytskyi2022.dao.dto.ShipmentSearchCriteriaDto;
import com.ykomarnytskyi2022.dao.entity.Customer;
import com.ykomarnytskyi2022.dao.entity.Driver;
import com.ykomarnytskyi2022.dao.entity.LogisticsCoordinator;
import com.ykomarnytskyi2022.dao.entity.Shipment;
import com.ykomarnytskyi2022.dao.repositories.ShipmentRepo;
import com.ykomarnytskyi2022.dao.repositories.specifications.ShipmentSpecifications;
import com.ykomarnytskyi2022.enums.ShipmentStatus;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Service
@Validated
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
	public void assignDriverForShipment(@Valid DriverShipmentDto driverShipment, @NotNull Long id) {
		Shipment shipment = getShipmentByIdOrThrowEntityNotFound(id);
		if (shipment.hasDriverAssigned() && !shipment.getDriver().getId().equals(driverShipment.shipmentId())) {
			throw new UnsupportedOperationException(
					"Shipment entity with Id: %s already has a driver assigned to it".formatted(id));
		}
		shipment.setDriver(mapper.map(driverShipment, Driver.class));
		shipmentRepo.save(shipment);
	}

	@Override
	public Optional<ShipmentDto> findShipmentById(@NotNull Long id) {
		return shipmentRepo.findById(id).map(this::mapShipmentToDto);
	}

	@Override
	public void updateShipment(@Valid ShipmentDto dto) throws EntityNotFoundException {
		Shipment shipment = shipmentRepo.getReferenceById(dto.getId());
		shipment.setDeliveredAt(dto.getDeliveredAt());
		shipment.setDestination(dto.getDestination());
		shipment.setGoodsDescription(dto.getGoodsDescription());
		shipment.setOrigin(dto.getOrigin());
		shipment.setPickedUpAt(dto.getPickedUpAt());
		shipment.setStatus(dto.getStatus());
		shipmentRepo.save(shipment);
	}

	@Override
	public PageableDto<ShipmentDto> getShipmentsAssignedToDriver(@NotNull Long id) {
		List<ShipmentDto> page = shipmentRepo.findAllByDriverId(id).stream().map(this::mapShipmentToDto).toList();
		return getStandardSizedPageableDto(page);
	}

	@Override
	public CreateShipmentDto createShipment(@Valid CreateShipmentDto shipmentDto) {
		Shipment shipment = new Shipment();
		shipment.setDestination(shipmentDto.getDestination());
		shipment.setGoodsDescription(shipmentDto.getGoodsDescription());
		shipment.setOrigin(shipmentDto.getOrigin());
		shipment.setStatus(shipmentDto.getStatus());
		
		if(shipmentDto.isCustomerPresent()) {
			shipment.setCustomer(mapper.map(shipmentDto.getCustomerOptional().get(), Customer.class));
		}  
		if (shipmentDto.isDriverPresent()) {
			shipment.setDriver(mapper.map(shipmentDto.getDriverOptional().get(), Driver.class));
		}
		if(shipmentDto.isLogisticsCoordinatorPresent()) {
			shipment.setCoordinator(mapper.map(shipmentDto.getLogisticsCoordinatorOptional().get(), LogisticsCoordinator.class));
		}
		
		Shipment persisted = shipmentRepo.save(shipment);
		
		return mapper.map(persisted, CreateShipmentDto.class);
	}

	@Override
	public void cancelShipment(Long shipmentId) throws EntityNotFoundException {
		updateShipmentStatus(shipmentId, ShipmentStatus.CANCELLED);
	}

	@Override
	public void updateShipmentStatus(@NotNull Long shipmentId, @NotNull ShipmentStatus status) {
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
	public PageableDto<ShipmentDto> searchShipments(ShipmentSearchCriteriaDto criteria, Pageable pageable) {
		Specification<Shipment> specification = ShipmentSpecifications.withCriteria(criteria);
		Page<Shipment> shipmentsPage = shipmentRepo.findAll(specification, pageable);
		List<ShipmentDto> page = shipmentsPage.stream()
				.map(this::mapShipmentToDto)
				.toList();
		return getStandardSizedPageableDto(page);
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

	private Shipment getShipmentByIdOrThrowEntityNotFound(@NotNull Long id) {
		return shipmentRepo.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Shipment entity with Id: %s does not exist".formatted(id)));
	}

	private static <T> PageableDto<T> getStandardSizedPageableDto(List<T> page) {
		return new PageableDto<>(page, page.size(), STANDARD_PAGE_SIZE,
				PageableDto.calculateTotalPages(page.size(), STANDARD_PAGE_SIZE));
	}

	private ShipmentDto mapShipmentToDto(Shipment shipment) {
		return mapper.map(shipment, ShipmentDto.class);
	}
}