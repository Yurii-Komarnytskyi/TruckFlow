package com.ykomarnytskyi2022.dao.entity;

import java.time.LocalDate;
import java.util.Optional;

import com.ykomarnytskyi2022.enums.ShippingUnit;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class BillOfLading extends PDFDocument {
	@NotNull
	private String shipperDetails;
	
	@NotNull
	private String consigneeDetails;
	
	private String goodsDescription;
	
	@NotNull
	private Integer totalWeightInKilograms;
	
	@Enumerated(EnumType.STRING)
	private ShippingUnit shippingUnit;
	
	@NotNull
	@OneToOne(mappedBy = "proofOfDelivery")
	private ShipmentEntity shipment;
	
	@ManyToOne
	private Optional<Driver> driver;

	public ShipmentEntity getShipment() {
		return shipment;
	}

	public void setShipment(ShipmentEntity shipment) {
		this.shipment = shipment;
	}

	public BillOfLading() {
		// default no-argument constructor for Spring JPA
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public byte[] getDocument() {
		return document;
	}

	public void setDocument(byte[] document) {
		this.document = document;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIssuer() {
		return issuer;
	}

	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}

	public LocalDate getDateOfUploading() {
		return dateOfUploading;
	}

	public void setDateOfUploading(LocalDate dateOfUploading) {
		this.dateOfUploading = dateOfUploading;
	}

	public String getShipperDetails() {
		return shipperDetails;
	}

	public void setShipperDetails(String shipperDetails) {
		this.shipperDetails = shipperDetails;
	}

	public Optional<Driver> getDriver() {
		return driver;
	}

	public void setDriver(Optional<Driver> driver) {
		this.driver = driver;
	}

	public String getConsigneeDetails() {
		return consigneeDetails;
	}

	public void setConsigneeDetails(String consigneeDetails) {
		this.consigneeDetails = consigneeDetails;
	}

	public String getGoodsDescription() {
		return goodsDescription;
	}

	public void setGoodsDescription(String goodsDescription) {
		this.goodsDescription = goodsDescription;
	}

	public Integer getTotalWeightInKilograms() {
		return totalWeightInKilograms;
	}

	public void setTotalWeightInKilograms(Integer totalWeightInKilograms) {
		this.totalWeightInKilograms = totalWeightInKilograms;
	}

	public ShippingUnit getShippingUnit() {
		return shippingUnit;
	}

	public void setShippingUnit(ShippingUnit shippingUnit) {
		this.shippingUnit = shippingUnit;
	}
}
