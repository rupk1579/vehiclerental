package com.vehical.rental.model;

public class Address {

    private String cityName;
    private String stateName;
    private String zipCode;

    public Address(String cityName, String stateName, String zipCode) {
        this.cityName = cityName;
        this.stateName = stateName;
        this.zipCode = zipCode;
    }
}
