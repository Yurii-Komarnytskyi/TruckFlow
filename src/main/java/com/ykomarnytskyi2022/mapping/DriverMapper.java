package com.ykomarnytskyi2022.mapping;

import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

import com.ykomarnytskyi2022.dao.dto.DriverDto;
import com.ykomarnytskyi2022.dao.entity.Driver;

@Component
public class DriverMapper extends AbstractConverter<Driver, DriverDto> {

	@Override
	protected DriverDto convert(Driver source) {
		DriverDto dto = new DriverDto();
		dto.setId(source.getId());
		dto.setAge(source.getAge());
		dto.setAuthorizedDrivingCategories(source.getAuthorizedDrivingCategories());
		dto.setBillOfLadings(source.getBillOfLadings());
		dto.setCellPhone(source.getCellPhone());
		dto.setEmail(source.getEmail());
		dto.setEmergencyContacts(source.getEmergencyContacts());
		dto.setLastName(source.getLastName());
		dto.setName(source.getName());
		dto.setProofOfDeliveries(source.getProofOfDeliveries());
		return dto;
	}

}
