package com.ykomarnytskyi2022.service;

import com.ykomarnytskyi2022.dao.dto.TransportationIssueDto;

public interface TransportationIssueSesvice {
	TransportationIssueDto saveTransportationIssue(TransportationIssueDto transportationIssue);
	TransportationIssueDto updateTransportationIssue(TransportationIssueDto transportationIssue);
}
