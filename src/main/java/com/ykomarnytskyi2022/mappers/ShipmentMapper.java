package com.ykomarnytskyi2022.mappers;

import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

import com.ykomarnytskyi2022.dao.dto.ShipmentDto;
import com.ykomarnytskyi2022.dao.entity.Shipment;

@Component
public class ShipmentMapper extends AbstractConverter<Shipment, ShipmentDto> {

	@Override
	protected ShipmentDto convert(Shipment source) {
		return null;
	}

}
