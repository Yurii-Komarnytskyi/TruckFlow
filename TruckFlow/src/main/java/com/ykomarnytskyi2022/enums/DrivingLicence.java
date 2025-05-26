package com.ykomarnytskyi2022.enums;

public enum DrivingLicence {
	AM("Mopeds and light quadricycles (max speed 45 km/h)"),
    A1("Light motorcycles (up to 125 cc and 11 kW)"),
    A2("Medium motorcycles (up to 35 kW)"),
    A("Large motorcycles (unrestricted)"),
    B1("Quadricycles (heavier than light quadricycles)"),
    B("Cars (up to 3,500 kg, max 8 passengers + driver)"),
    BE("Cars with trailers (heavier combinations)"),
    C1("Small trucks (3,500 kg to 7,500 kg)"),
    C1E("Small trucks with trailers"),
    C("Large trucks (over 3,500 kg)"),
    CE("Large trucks with trailers"),
    D1("Small buses (up to 16 passengers + driver)"),
    D1E("Small buses with trailers"),
    D("Large buses (over 8 passengers + driver)"),
    DE("Large buses with trailers");

    private final String description;

    DrivingLicence(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
