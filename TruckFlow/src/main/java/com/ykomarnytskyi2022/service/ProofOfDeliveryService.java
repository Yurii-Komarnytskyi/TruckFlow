package com.ykomarnytskyi2022.service;

import com.ykomarnytskyi2022.dao.dto.ProofOfDeliveryDto;

public interface ProofOfDeliveryService {
	ProofOfDeliveryDto saveProofOfDelivery(ProofOfDeliveryDto proofOfDelivery, Long shipmentId);
	ProofOfDeliveryDto uploadPOD(ProofOfDeliveryDto proofOfDelivery, Long shipmentId);
}
