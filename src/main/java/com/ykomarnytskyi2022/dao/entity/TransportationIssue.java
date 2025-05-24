package com.ykomarnytskyi2022.dao.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class TransportationIssue {

	@Id
	private Long id;
	
	private Long shipmentId;
}
