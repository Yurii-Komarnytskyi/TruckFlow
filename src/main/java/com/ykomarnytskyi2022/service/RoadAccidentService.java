package com.ykomarnytskyi2022.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ykomarnytskyi2022.dao.dto.RoadAccidentDto;

public interface RoadAccidentService {
	RoadAccidentDto saveRoadAccident(RoadAccidentDto roadAccident);
	List<RoadAccidentDto> getRoadAccidentsByDriverId(Long id);
	RoadAccidentDto reportRoadAccident(RoadAccidentDto roadAccident);
}
