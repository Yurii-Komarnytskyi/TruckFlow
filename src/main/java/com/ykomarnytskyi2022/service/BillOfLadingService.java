package com.ykomarnytskyi2022.service;

import com.ykomarnytskyi2022.dao.dto.BillOfLadingDto;

public interface BillOfLadingService {
	BillOfLadingDto saveBillOfLading(BillOfLadingDto billOfLading, Long shipmentId);
}
