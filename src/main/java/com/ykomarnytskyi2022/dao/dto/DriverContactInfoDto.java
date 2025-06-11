package com.ykomarnytskyi2022.dao.dto;

import java.util.Set;

import com.ykomarnytskyi2022.enums.DrivingLicence;

public record DriverContactInfoDto(
		Long id, 
		String name,
		String lastName,
		String cellPhone,
		String email,
		Set<DrivingLicence> authorizedDrivingCategories) {
}
