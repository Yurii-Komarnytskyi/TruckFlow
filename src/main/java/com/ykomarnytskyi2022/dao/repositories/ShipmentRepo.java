package com.ykomarnytskyi2022.dao.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ykomarnytskyi2022.dao.entity.Shipment;

public interface ShipmentRepo extends JpaRepository<Shipment, Long> {
	List<Shipment> findAllByDriverId(Long driverId);
}
