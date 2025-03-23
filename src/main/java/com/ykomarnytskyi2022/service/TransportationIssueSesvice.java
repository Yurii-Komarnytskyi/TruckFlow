package com.ykomarnytskyi2022.service;

import org.springframework.stereotype.Service;

import com.ykomarnytskyi2022.dao.dto.TransportationIssueDto;

@Service
public interface TransportationIssueSesvice {
	TransportationIssueDto saveTransportationIssue(TransportationIssueDto transportationIssue);
	TransportationIssueDto updateTransportationIssue(TransportationIssueDto transportationIssue);
}
