package com.ykomarnytskyi2022.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ykomarnytskyi2022.dao.dto.BillOfLadingDto;
import com.ykomarnytskyi2022.dao.dto.DriverDto;
import com.ykomarnytskyi2022.dao.dto.DriverShipmentDto;
import com.ykomarnytskyi2022.dao.dto.PageableDto;
import com.ykomarnytskyi2022.dao.dto.ProofOfDeliveryDto;
import com.ykomarnytskyi2022.dao.dto.RoadAccidentDto;
import com.ykomarnytskyi2022.dao.dto.ShipmentDto;
import com.ykomarnytskyi2022.dao.dto.TransportationIssueDto;
import com.ykomarnytskyi2022.dao.entity.Driver;
import com.ykomarnytskyi2022.freight.ShipmentStatus;
import com.ykomarnytskyi2022.repositories.DriverRepo;

@Service
public class DriverServiceImpl implements DriverService {
	private final DriverRepo driverRepo;
	private final BillOfLadingService billOfLadingService;
	private final ProofOfDeliveryService proofOfDeliveryService;
	private final ShipmentManagementService shipmentManagementService;
	private final TransportationIssueSesvice transportationIssueSesvice;
	private final RoadAccidentService roadAccidentService;
	private final ModelMapper mapper;
	private static final int STANDARD_PAGE_SIZE = 12;

	@Autowired
	public DriverServiceImpl(DriverRepo driverRepo, BillOfLadingService billOfLadingService,
			ProofOfDeliveryService proofOfDeliveryService, ShipmentManagementService shipmentManagementService,
			TransportationIssueSesvice transportationIssueSesvice, RoadAccidentService roadAccidentService,
			ModelMapper mapper) {
		this.driverRepo = driverRepo;
		this.billOfLadingService = billOfLadingService;
		this.proofOfDeliveryService = proofOfDeliveryService;
		this.shipmentManagementService = shipmentManagementService;
		this.transportationIssueSesvice = transportationIssueSesvice;
		this.roadAccidentService = roadAccidentService;
		this.mapper = mapper;
	}

	@Override
	public boolean acceptShipment(DriverShipmentDto driverShipment) {
		Optional<Driver> optional = driverRepo.findById(driverShipment.getDriverId());
		if (optional.isEmpty()) {
			return false;
		}
		return shipmentManagementService.assignDriverForShipment(mapper.map(optional, DriverDto.class),
				driverShipment.getId());
	}

	@Override
	public boolean declineShipment(DriverShipmentDto driverShipment) {
		return true;
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
	public TransportationIssueDto reportTransportationIssue(TransportationIssueDto transportationIssue) {
		return transportationIssueSesvice.saveTransportationIssue(transportationIssue);
	}

	@Override
	public RoadAccidentDto reportRoadAccident(RoadAccidentDto roadAccident) {
		return roadAccidentService.saveRoadAccident(roadAccident);
	}

	@Override
	public BillOfLadingDto uploadBOL(BillOfLadingDto billOflading, Long shipmentId) {
		return billOfLadingService.saveBillOfLading(billOflading, shipmentId);
	}

	@Override
	public ProofOfDeliveryDto uploadPOD(ProofOfDeliveryDto proofOfDelivery, Long shipmentId) {
		return proofOfDeliveryService.saveProofOfDelivery(proofOfDelivery, shipmentId);
	}

	@Override
	public PageableDto<DriverShipmentDto> getDriverShipments(Long driverId) {
		List<DriverShipmentDto> page = shipmentManagementService.getShipmentsAssignedToDriver(driverId).stream()
				.map(s -> mapper.map(s, DriverShipmentDto.class)).toList();
		return new PageableDto<>(page, page.size(), 0, calculateTotalPagesPerPegeableDto(page.size()));
	}

	@Override
	public List<RoadAccidentDto> getRoadAccidentsByDriverId(Long id) {
		return roadAccidentService.getRoadAccidentsByDriverId(id);
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

	private static int calculateTotalPagesPerPegeableDto(int listSize) {
		return (listSize + STANDARD_PAGE_SIZE - 1) / listSize;
	}
}
