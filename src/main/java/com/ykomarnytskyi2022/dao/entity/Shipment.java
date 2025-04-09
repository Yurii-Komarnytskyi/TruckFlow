package com.ykomarnytskyi2022.dao.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.ykomarnytskyi2022.enums.ShipmentStatus;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Shipment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST})
	@JoinColumn(name = "driver_id")
	private Driver driver;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST})
	@JoinColumn(name = "coordinator_id")
	private LogisticsCoordinator coordinator;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Customer customer;
	
	@OneToOne(fetch = FetchType.LAZY)
	private BillOfLading billOfLading;
	
	@OneToOne(fetch = FetchType.LAZY)
	private ProofOfDelivery proofOfDelivery;
	
	@OneToMany(mappedBy = "shipment", fetch = FetchType.LAZY)
	private List<RoadAccident> roadAccidents = new ArrayList<>();
	
	@Enumerated(EnumType.STRING)
	private ShipmentStatus status;
	
	@NotNull
	@NotBlank
    private String origin;

    @NotNull
    @NotBlank
    private String destination;
    
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    private LocalDateTime pickedUpAt;

    private LocalDateTime deliveredAt;
    
    @NonNull
    @NotBlank
    private String goodsDescription;
	
	public Shipment() {
		// default no-argument constructor for Spring JPA	
	}
	
	public Shipment(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	public BillOfLading getBillOfLading() {
		return billOfLading;
	}

	public void setBillOfLading(BillOfLading billOfLading) {
		this.billOfLading = billOfLading;
	}

	public ProofOfDelivery getProofOfDelivery() {
		return proofOfDelivery;
	}

	public void setProofOfDelivery(ProofOfDelivery proofOfDelivery) {
		this.proofOfDelivery = proofOfDelivery;
	}

	public ShipmentStatus getStatus() {
		return status;
	}

	public void setStatus(ShipmentStatus status) {
		this.status = status;
	}

	public LogisticsCoordinator getCoordinator() {
		return coordinator;
	}

	public void setCoordinator(LogisticsCoordinator coordinator) {
		this.coordinator = coordinator;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<RoadAccident> getRoadAccidents() {
		return roadAccidents;
	}

	public void setRoadAccidents(List<RoadAccident> roadAccidents) {
		this.roadAccidents = roadAccidents;
	}
	
	public boolean hasDriverAssigned() {
		return Objects.nonNull(driver) && Objects.nonNull(driver.getId());
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

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
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
