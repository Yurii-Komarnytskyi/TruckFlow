package com.ykomarnytskyi2022.dao.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import com.ykomarnytskyi2022.enums.DeliveryStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;

@Entity
public class ProofOfDelivery extends PDFDocument {

	private LocalDateTime dateOfDelivery;
	
	@ManyToOne
	private Driver driver;
	
	@NotNull
	private String recipientInformation;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private DeliveryStatus deliveryStatus;
	
	private String shipmentReference;
	
	@NotNull
	@OneToOne(mappedBy = "proofOfDelivery")
	private Shipment shipment;
	
	public ProofOfDelivery() {
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

	public LocalDateTime getDateOfDelivery() {
		return dateOfDelivery;
	}

	public void setDateOfDelivery(LocalDateTime dateOfDelivery) {
		this.dateOfDelivery = dateOfDelivery;
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	public String getRecipientInformation() {
		return recipientInformation;
	}

	public void setRecipientInformation(String recipientInformation) {
		this.recipientInformation = recipientInformation;
	}

	public DeliveryStatus getDeliveryStatus() {
		return deliveryStatus;
	}

	public void setDeliveryStatus(DeliveryStatus deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}

	public Optional<String> getShipmentReference() {
		return Optional.ofNullable(shipmentReference);
	}

	public void setShipmentReference(String shipmentReference) {
		this.shipmentReference = shipmentReference;
	}

	public Shipment getShipment() {
		return shipment;
	}

	public void setShipment(Shipment shipment) {
		this.shipment = shipment;
	}
}
