package com.ykomarnytskyi2022.mappers;

import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

import com.ykomarnytskyi2022.dao.dto.CustomerDto;
import com.ykomarnytskyi2022.dao.entity.Customer;

@Component
public class CustomerFromDtoMapper extends AbstractConverter<CustomerDto, Customer> {

	@Override
	protected Customer convert(CustomerDto source) {
		return new Customer(source.id());
	}

}
