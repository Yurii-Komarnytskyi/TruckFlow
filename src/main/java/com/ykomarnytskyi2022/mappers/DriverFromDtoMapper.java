package com.ykomarnytskyi2022.mappers;

import java.util.Objects;

import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

import com.ykomarnytskyi2022.dao.dto.DriverDto;
import com.ykomarnytskyi2022.dao.entity.Driver;

@Component
public class DriverFromDtoMapper extends AbstractConverter<DriverDto, Driver> {

	@Override
	protected Driver convert(DriverDto source) {
		if(Objects.isNull(source)) {
			throw new IllegalArgumentException("The source argument in a mapper cannot be null");
		}
		Driver driver = new Driver(source.id());
		driver.setAuthorizedDrivingCategories(source.authorizedDrivingCategories());
		driver.setBillOfLadings(source.billOfLadings());
		driver.setCellPhone(source.cellPhone());
		driver.setEmail(source.email());
		driver.setEmergencyContacts(source.emergencyContacts());
		driver.setLastName(source.lastName());
		driver.setName(source.name());
		driver.setProofOfDeliveries(source.proofOfDeliveries());
		return driver;
	}

}
