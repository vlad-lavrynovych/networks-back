package com.example.networks.enums;

public enum NodeTypesEnum {
    NETWORK("NETWORK"),
    SUBSTATION("SUBSTATION"),
    TRANSFORMER("TRANSFORMER"),
    FEEDER("FEEDER"),
    RESOURCE("RESOURCE");

    private String value;

    NodeTypesEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
