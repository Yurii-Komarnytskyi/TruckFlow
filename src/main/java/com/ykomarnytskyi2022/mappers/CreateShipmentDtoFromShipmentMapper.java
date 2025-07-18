package com.ykomarnytskyi2022.mappers;

import java.util.Objects;

import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

import com.ykomarnytskyi2022.dao.dto.CreateShipmentDto;
import com.ykomarnytskyi2022.dao.dto.CustomerDto;
import com.ykomarnytskyi2022.dao.dto.DriverContactInfoDto;
import com.ykomarnytskyi2022.dao.dto.LogisticsCoordinatorDto;
import com.ykomarnytskyi2022.dao.entity.Customer;
import com.ykomarnytskyi2022.dao.entity.Driver;
import com.ykomarnytskyi2022.dao.entity.LogisticsCoordinator;
import com.ykomarnytskyi2022.dao.entity.Shipment;
import com.ykomarnytskyi2022.exceptions.AbstractConverterSourceIsNullException;

@Component
public class CreateShipmentDtoFromShipmentMapper extends AbstractConverter<Shipment, CreateShipmentDto>{

	@Override
	protected CreateShipmentDto convert(Shipment source) {
		if(Objects.isNull(source)) {
			throw new AbstractConverterSourceIsNullException();
		}
		CreateShipmentDto createShipment =  new CreateShipmentDto();
		Driver driver = source.getDriver();
		Customer customer = source.getCustomer();
		LogisticsCoordinator coordinator = source.getCoordinator();
		createShipment.setDeliveredAt(source.getDeliveredAt());
		createShipment.setDestination(source.getDestination());
		createShipment.setGoodsDescription(source.getGoodsDescription());
		createShipment.setId(source.getId());
		createShipment.setOrigin(source.getOrigin());
		createShipment.setPickedUpAt(source.getPickedUpAt());
		createShipment.setStatus(source.getStatus());
		
		if (Objects.nonNull(driver)) {
			DriverContactInfoDto driverDto = new DriverContactInfoDto(
					driver.getId(), 
					driver.getName(), 
					driver.getLastName(), 
					driver.getCellPhone(), 
					driver.getEmail(), 
					driver.getAuthorizedDrivingCategories());
			createShipment.setDriverOptional(driverDto);
		}
		
		if (Objects.nonNull(customer)) {
			CustomerDto customerDto = new CustomerDto(customer.getId());
			createShipment.setCustomerOptional(customerDto);
		}
		
		if(Objects.nonNull(coordinator)) {
			LogisticsCoordinatorDto coordinatorDto = new LogisticsCoordinatorDto(coordinator.getId());
			createShipment.setLogisticsCoordinatorOptional(coordinatorDto);
		} 
		
		return createShipment;
	}

	

}
