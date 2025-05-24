package com.ykomarnytskyi2022.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ykomarnytskyi2022.dao.dto.ProofOfDeliveryDto;

@Service
public class ProofOfDeliveryServiceImpl implements ProofOfDeliveryService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProofOfDeliveryServiceImpl.class);
	@Override
	public ProofOfDeliveryDto saveProofOfDelivery(ProofOfDeliveryDto proofOfDelivery, Long shipmentId) {
		return null;
	}

}
