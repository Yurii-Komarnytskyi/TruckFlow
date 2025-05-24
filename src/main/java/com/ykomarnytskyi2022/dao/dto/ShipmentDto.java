package com.ykomarnytskyi2022.dao.dto;

import java.time.LocalDateTime;

import com.ykomarnytskyi2022.enums.ShipmentStatus;

public class ShipmentDto {
	private Long id;
	
	private ShipmentStatus status;
	
    private String origin;

    private String destination;
    
    private LocalDateTime pickedUpAt;

    private LocalDateTime deliveredAt;
    
    private String goodsDescription;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ShipmentStatus getStatus() {
		return status;
	}

	public void setStatus(ShipmentStatus status) {
		this.status = status;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public LocalDateTime getPickedUpAt() {
		return pickedUpAt;
	}

	public void setPickedUpAt(LocalDateTime pickedUpAt) {
		this.pickedUpAt = pickedUpAt;
	}

	public LocalDateTime getDeliveredAt() {
		return deliveredAt;
	}

	public void setDeliveredAt(LocalDateTime deliveredAt) {
		this.deliveredAt = deliveredAt;
	}

	public String getGoodsDescription() {
		return goodsDescription;
	}

	public void setGoodsDescription(String goodsDescription) {
		this.goodsDescription = goodsDescription;
	}
}
