package com.ykomarnytskyi2022.enums;

public enum VehicleType {
	VAN("Light goods vehicle up to 3.5 tons"),
    RIGID_TRUCK("Single-unit truck up to 12 meters"),
    ARTICULATED_TRUCK("Tractor with semi-trailer up to 16.5 meters"),
    ROAD_TRAIN("Truck with drawbar trailer up to 18.75 meters"),
    REEFER_TRUCK("Refrigerated truck for temperature-sensitive goods"),
    FLATBED_TRUCK("Truck with flat platform for oversized loads"),
    BOX_TRUCK("Enclosed truck for secure cargo"),
    CURTAIN_SIDER("Truck with flexible side curtains"),
    TANKER_TRUCK("Truck for liquids or gases"),
    CAR_TRANSPORTER("Truck for vehicle transport"),
    LONGER_HEAVIER_VEHICLE("Extended combination up to 25.25 meters, used in some EU countries");
	
    private final String description;

    VehicleType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
