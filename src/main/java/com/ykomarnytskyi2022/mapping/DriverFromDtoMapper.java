package com.ykomarnytskyi2022.mapping;

import java.util.Objects;

import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

import com.ykomarnytskyi2022.dao.dto.DriverDto;
import com.ykomarnytskyi2022.dao.entity.Driver;

@Component
public class DriverFromDtoMapper extends AbstractConverter<DriverDto, Driver> {

	@Override
	protected Driver convert(DriverDto source) throws IllegalArgumentException {
		if(Objects.isNull(source)) {
			throw new IllegalArgumentException("The source argument in a mapper cannot be null");
		}
		Driver driver = new Driver(source.getId());
		driver.setAge(source.getAge());
		driver.setAuthorizedDrivingCategories(source.getAuthorizedDrivingCategories());
		driver.setBillOfLadings(source.getBillOfLadings());
		driver.setCellPhone(source.getCellPhone());
		driver.setEmail(source.getEmail());
		driver.setEmergencyContacts(source.getEmergencyContacts());
		driver.setLastName(source.getLastName());
		driver.setName(source.getName());
		driver.setProofOfDeliveries(source.getProofOfDeliveries());
		return driver;
	}

}
