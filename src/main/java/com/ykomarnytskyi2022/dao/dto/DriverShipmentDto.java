package com.ykomarnytskyi2022.dao.dto;

import jakarta.validation.constraints.NotNull;

public record DriverShipmentDto (@NotNull Long shipmentId, @NotNull Long driverId, boolean isAccepted) {
}
