package com.ykomarnytskyi2022.mappers;

import java.util.Objects;

import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

import com.ykomarnytskyi2022.dao.dto.ShipmentDto;
import com.ykomarnytskyi2022.dao.entity.Shipment;
import com.ykomarnytskyi2022.exceptions.AbstractConverterSourceIsNullException;

@Component
public class ShipmentMapper extends AbstractConverter<Shipment, ShipmentDto> {

	@Override
	protected ShipmentDto convert(Shipment source) {
		if (Objects.isNull(source)) {
			throw new AbstractConverterSourceIsNullException();
		}
		ShipmentDto destination = new ShipmentDto();
		destination.setId(source.getId());
		destination.setDeliveredAt(source.getDeliveredAt());
		destination.setDestination(source.getDestination());
		destination.setGoodsDescription(source.getGoodsDescription());
		destination.setOrigin(source.getOrigin());
		destination.setPickedUpAt(source.getPickedUpAt());
		destination.setStatus(source.getStatus());

		return destination;
	}

}
