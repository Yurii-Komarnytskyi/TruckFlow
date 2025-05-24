package com.ykomarnytskyi2022.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ykomarnytskyi2022.dao.dto.TransportationIssueDto;

@Service
public class TransportationIssueSesviceImpl implements TransportationIssueSesvice {
	private static final Logger LOGGER = LoggerFactory.getLogger(TransportationIssueSesviceImpl.class);

	@Override
	public TransportationIssueDto saveTransportationIssue(TransportationIssueDto transportationIssue) {
		return null;
	}

	@Override
	public TransportationIssueDto updateTransportationIssue(TransportationIssueDto transportationIssue) {
		return null;
	}

}
