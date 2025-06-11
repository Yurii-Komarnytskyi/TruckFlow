package com.ykomarnytskyi2022.mappers;

import java.util.Objects;

import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

import com.ykomarnytskyi2022.dao.dto.DriverShipmentDto;
import com.ykomarnytskyi2022.dao.entity.Driver;
import com.ykomarnytskyi2022.exceptions.AbstractConverterSourceIsNullException;

@Component
public class DriverFromDriverShipmentDtoMapper extends AbstractConverter<DriverShipmentDto, Driver>{

	@Override
	protected Driver convert(DriverShipmentDto source) throws IllegalArgumentException {
		if(Objects.isNull(source)) {
			throw new AbstractConverterSourceIsNullException();
		} 
		return new Driver(source.driverId());
	}

}
