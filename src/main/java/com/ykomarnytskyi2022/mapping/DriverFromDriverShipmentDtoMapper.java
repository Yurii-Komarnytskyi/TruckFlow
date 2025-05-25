package com.ykomarnytskyi2022.mapping;

import java.util.Objects;

import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

import com.ykomarnytskyi2022.dao.dto.DriverShipmentDto;
import com.ykomarnytskyi2022.dao.entity.Driver;

@Component
public class DriverFromDriverShipmentDtoMapper extends AbstractConverter<DriverShipmentDto, Driver>{

	@Override
	protected Driver convert(DriverShipmentDto source) throws IllegalArgumentException {
		if(Objects.isNull(source)) {
			throw new IllegalArgumentException("The source argument in a mapper cannot be null");
		} 
		return new Driver(source.getDriverId());
	}

}
