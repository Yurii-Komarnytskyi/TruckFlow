package com.ykomarnytskyi2022.dao.dto;

import java.util.List;
import java.util.Set;

import com.ykomarnytskyi2022.dao.entity.BillOfLading;
import com.ykomarnytskyi2022.dao.entity.ProofOfDelivery;
import com.ykomarnytskyi2022.enums.DrivingLicence;

public record DriverDto (
		Long id, 
		String name,
		String lastName,
		String cellPhone,
		String email,
		List<String> emergencyContacts, 
		Set<ProofOfDelivery> proofOfDeliveries,
		Set<BillOfLading> billOfLadings, 
		Set<DrivingLicence> authorizedDrivingCategories) {
}
