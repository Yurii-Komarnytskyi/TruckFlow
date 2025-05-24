package com.ykomarnytskyi2022.mapping;

import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

import com.ykomarnytskyi2022.dao.dto.DriverDto;
import com.ykomarnytskyi2022.dao.entity.Driver;

@Component
public class DriverFromDtoMapper extends AbstractConverter<DriverDto, Driver> {

	@Override
	protected Driver convert(DriverDto source) {
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
