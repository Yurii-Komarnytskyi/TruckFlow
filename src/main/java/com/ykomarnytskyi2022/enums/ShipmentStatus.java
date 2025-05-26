package com.ykomarnytskyi2022.enums;

public enum ShipmentStatus {
	PENDING("Pending - Awaiting assignment or preparation"),
    ASSIGNED("Assigned - Driver and/or coordinator assigned, ready to start"),
    AT_PICK_UP("At Pickup - Shipment is at the pickup location, loading in progress"),
    IN_TRANSIT("In Transit - Shipment is on its way to the destination"),
    AT_DELIVERY("At Delivery - Shipment has arrived at destination, unloading in progress"),
    DELAYED("Delayed - Shipment is behind schedule due to issues"),
    DELIVERED("Delivered - Shipment has reached its destination and been completed"),
    CANCELLED("Cancelled - Shipment has been terminated before completion");

    private final String description;

    ShipmentStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
