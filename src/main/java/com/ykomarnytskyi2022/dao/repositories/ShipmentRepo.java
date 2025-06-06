package com.ykomarnytskyi2022.dao.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.ykomarnytskyi2022.dao.entity.Shipment;
import com.ykomarnytskyi2022.enums.ShipmentStatus;

public interface ShipmentRepo extends JpaRepository<Shipment, Long>, JpaSpecificationExecutor<Shipment> {
	List<Shipment> findAllByDriverId(Long id);
	List<Shipment> findAllByStatus(ShipmentStatus status);
	List<Shipment> findAllByCoordinatorId(Long id);
	List<Shipment> findAllByCustomerId(Long id);
}
