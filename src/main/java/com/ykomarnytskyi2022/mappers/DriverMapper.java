package com.ykomarnytskyi2022.mappers;

import java.util.Objects;

import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

import com.ykomarnytskyi2022.dao.dto.DriverDto;
import com.ykomarnytskyi2022.dao.entity.Driver;

@Component
public class DriverMapper extends AbstractConverter<Driver, DriverDto> {

	@Override
	protected DriverDto convert(Driver source) throws IllegalArgumentException {
		if(Objects.isNull(source)) {
			throw new IllegalArgumentException("The source argument in a mapper cannot be null");
		}
		return new DriverDto(
				source.getId(), 
				source.getName(), 
				source.getLastName(), 
				source.getCellPhone(), 
				source.getEmail(), 
				source.getEmergencyContacts(), 
				source.getProofOfDeliveries(), 
				source.getBillOfLadings(), 
				source.getAuthorizedDrivingCategories());
	}
}
