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
		return dto;
	}

}
