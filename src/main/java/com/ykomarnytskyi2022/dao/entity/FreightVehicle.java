package com.ykomarnytskyi2022.dao.entity;

import java.time.LocalDate;

import com.ykomarnytskyi2022.enums.DrivingLicence;
import com.ykomarnytskyi2022.enums.FuelType;
import com.ykomarnytskyi2022.enums.VehicleType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "freight_vehicle")
public class FreightVehicle {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String make;
	
	@NotNull
	private LocalDate manufacturingYear;
	
	private LocalDate lastMaintenanceDate;
	
	@Enumerated(EnumType.STRING)
	private VehicleType type;
	
	@Enumerated(EnumType.STRING)
	private DrivingLicence drivingLicenceRequired;
	
	@Enumerated(EnumType.STRING)
	private FuelType fuelType;
	
	@Min(100)
	private Integer maxLoadCapacityKg;
	
	@Max(19)
	private Double vehicleLengthInMeters;
	
	@Max(3)
	private Double vehicleWidthInMeters;
	
	@Max(4)
	private Double vehicleHeightInMeters;
	
	@NotNull
	private String licensePlateNumber;

	public FreightVehicle() {
		// default no-argument constructor for Spring JPA
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public LocalDate getManufacturingYear() {
		return manufacturingYear;
	}

	public void setManufacturingYear(LocalDate manufacturingYear) {
		this.manufacturingYear = manufacturingYear;
	}

	public LocalDate getLastMaintenanceDate() {
		return lastMaintenanceDate;
	}

	public void setLastMaintenanceDate(LocalDate lastMaintenanceDate) {
		this.lastMaintenanceDate = lastMaintenanceDate;
	}

	public VehicleType getType() {
		return type;
	}

	public void setType(VehicleType type) {
		this.type = type;
	}

	public DrivingLicence getDrivingLicenceRequired() {
		return drivingLicenceRequired;
	}

	public void setDrivingLicenceRequired(DrivingLicence drivingLicenceRequired) {
		this.drivingLicenceRequired = drivingLicenceRequired;
	}

	public FuelType getFuelType() {
		return fuelType;
	}

	public void setFuelType(FuelType fuelType) {
		this.fuelType = fuelType;
	}

	public Integer getMaxLoadCapacityKg() {
		return maxLoadCapacityKg;
	}

	public void setMaxLoadCapacityKg(Integer maxLoadCapacityKg) {
		this.maxLoadCapacityKg = maxLoadCapacityKg;
	}

	public Double getVehicleLengthInMeters() {
		return vehicleLengthInMeters;
	}

	public void setVehicleLengthInMeters(Double vehicleLengthInMeters) {
		this.vehicleLengthInMeters = vehicleLengthInMeters;
	}

	public Double getVehicleWidthInMeters() {
		return vehicleWidthInMeters;
	}

	public void setVehicleWidthInMeters(Double vehicleWidthInMeters) {
		this.vehicleWidthInMeters = vehicleWidthInMeters;
	}

	public Double getVehicleHeightInMeters() {
		return vehicleHeightInMeters;
	}

	public void setVehicleHeightInMeters(Double vehicleHeightInMeters) {
		this.vehicleHeightInMeters = vehicleHeightInMeters;
	}

	public String getLicensePlateNumber() {
		return licensePlateNumber;
	}

	public void setLicensePlateNumber(String licensePlateNumber) {
		this.licensePlateNumber = licensePlateNumber;
	}
}
