package com.ykomarnytskyi2022.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ykomarnytskyi2022.dao.dto.RoadAccidentDto;

@Service
public class RoadAccidentServiceImpl implements RoadAccidentService {
	private static final Logger LOGGER = LoggerFactory.getLogger(RoadAccidentServiceImpl.class);

	@Override
	public RoadAccidentDto saveRoadAccident(RoadAccidentDto roadAccident) {
		return null;
	}

	@Override
	public List<RoadAccidentDto> getRoadAccidentsByDriverId(Long id) {
		return null;
	}
}
