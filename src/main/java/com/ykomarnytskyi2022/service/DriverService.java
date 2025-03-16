package com.ykomarnytskyi2022.service;

import com.ykomarnytskyi2022.dto.BillOfLadingDto;
import com.ykomarnytskyi2022.dto.DriverShipmentDto;
import com.ykomarnytskyi2022.dto.ProofOfDeliveryDto;
import com.ykomarnytskyi2022.freight.BillOfLading;
import com.ykomarnytskyi2022.freight.ProofOfDelivery;
import com.ykomarnytskyi2022.freight.RoadAccident;
import com.ykomarnytskyi2022.freight.ShipmentStatus;
import com.ykomarnytskyi2022.freight.TransportationIssue;

public interface DriverService {
	boolean acceptShipment(DriverShipmentDto driverShipment);
	boolean declineShipment(DriverShipmentDto driverShipment);
	ShipmentStatus reportProgress();
	TransportationIssue reportTransportationIssue();
	RoadAccident reportRoadAccident();
	BillOfLading uploadBOL(BillOfLadingDto billOflading);
	ProofOfDelivery uploadPOD(ProofOfDeliveryDto proofOfDelivery);
}
