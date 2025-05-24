package com.ykomarnytskyi2022.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ykomarnytskyi2022.dao.dto.BillOfLadingDto;

@Service
public class BillOfLadingServiceImpl implements BillOfLadingService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BillOfLadingServiceImpl.class);

	@Override
	public BillOfLadingDto saveBillOfLading(BillOfLadingDto billOfLading, Long shipmentId) {
		return null;
	}

	@Override
	public BillOfLadingDto uploadBOL(BillOfLadingDto billOflading, Long shipmentId) {
		// TODO Auto-generated method stub
		return null;
	}

}
