package com.ykomarnytskyi2022.mapping;

import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

import com.ykomarnytskyi2022.dao.dto.LogisticsCoordinatorDto;
import com.ykomarnytskyi2022.dao.entity.LogisticsCoordinator;

@Component
public class LogisticsCoordinatorFromDtoMapper  extends AbstractConverter<LogisticsCoordinatorDto, LogisticsCoordinator>{

	@Override
	protected LogisticsCoordinator convert(LogisticsCoordinatorDto source) {
		return null;
	}

}
