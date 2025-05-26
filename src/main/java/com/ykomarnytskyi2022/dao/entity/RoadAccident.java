package com.ykomarnytskyi2022.dao.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

@Entity
public class RoadAccident {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private Shipment shipment;

	@ManyToOne
	@NotNull
	private Driver driver;

	@NotNull
	@Column(name = "accident_description")
	private String accidentDescription;

	@NotNull
	@Column(name = "involved_party_name")
	private String involvedPartyName;

	@NotNull
	@Column(name = "involved_party_phoneNumber")
	private String involvedPartyPhoneNumber;

	@NotNull
	@Column(name = "accident_timestamp")
	private LocalDateTime accidentTime;

	@Enumerated(EnumType.STRING)
	private Severity severity;
	
	@Lob 
	@Basic(fetch = FetchType.LAZY)
	@Column(name = "european_accident_statement", nullable = true)
	private byte[] europeanAccidentStatement;
	
	public RoadAccident() {
		// default no-argument constructor for Spring JPA
	}

	public enum Severity {
		FATAL("Fatal - Death, Severe Vehilce Damage"),
		SERIOUS_INJURY("Serious Injury - Major Health Damage, Significant Vehilce Damage"),
		MINOR_INJURY("Minor Injury - Minor Health Damage, Minor Vehilce Damage"),
		PROPERTY_DAMAGE_ONLY("Property Damage - No Health Damage, Varying Vehilce Damage");

		private final String displayName;

		private Severity(String displayName) {
			this.displayName = displayName;
		}

		public String getDisplayName() {
			return displayName;
		}
	}

	public Shipment getShipment() {
		return shipment;
	}

	public void setShipment(Shipment shipment) {
		this.shipment = shipment;
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	public String getAccidentDescription() {
		return accidentDescription;
	}

	public void setAccidentDescription(String accidentDescription) {
		this.accidentDescription = accidentDescription;
	}

	public String getInvolvedPartyName() {
		return involvedPartyName;
	}

	public void setInvolvedPartyName(String involvedPartyName) {
		this.involvedPartyName = involvedPartyName;
	}

	public String getInvolvedPartyPhoneNumber() {
		return involvedPartyPhoneNumber;
	}

	public void setInvolvedPartyPhoneNumber(String involvedPartyPhoneNumber) {
		this.involvedPartyPhoneNumber = involvedPartyPhoneNumber;
	}

	public LocalDateTime getAccidentTime() {
		return accidentTime;
	}

	public void setAccidentTime(LocalDateTime accidentTime) {
		this.accidentTime = accidentTime;
	}

	public Severity getSeverity() {
		return severity;
	}

	public void setSeverity(Severity severity) {
		this.severity = severity;
	}

	public byte[] getEuropeanAccidentStatement() {
		return europeanAccidentStatement;
	}

	public void setEuropeanAccidentStatement(byte[] europeanAccidentStatement) {
		this.europeanAccidentStatement = europeanAccidentStatement;
	}

	public Long getId() {
		return id;
	}
	
	
}
