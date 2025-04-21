package com.ykomarnytskyi2022.mapping;

import java.util.Objects;

import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

import com.ykomarnytskyi2022.dao.dto.CreateShipmentDto;
import com.ykomarnytskyi2022.dao.dto.CustomerDto;
import com.ykomarnytskyi2022.dao.dto.DriverDto;
import com.ykomarnytskyi2022.dao.dto.LogisticsCoordinatorDto;
import com.ykomarnytskyi2022.dao.entity.Customer;
import com.ykomarnytskyi2022.dao.entity.Driver;
import com.ykomarnytskyi2022.dao.entity.LogisticsCoordinator;
import com.ykomarnytskyi2022.dao.entity.Shipment;

@Component
public class CreateShipmentDtoFromShipmentMapper extends AbstractConverter<Shipment, CreateShipmentDto>{

	@Override
	protected CreateShipmentDto convert(Shipment source) {
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
			DriverDto driverDto = new DriverDto();
			driverDto.setId(driver.getId());
			driverDto.setName(driver.getName());
			driverDto.setName(driver.getLastName());
			driverDto.setCellPhone(driver.getCellPhone());
			driverDto.setEmail(driver.getEmail());
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
