package com.vehical.rental.model;

import com.vehical.rental.enums.VehicleType;


import java.util.UUID;

public class Vehicle {

    private String vehicleId;
    private VehicleType vehicleType;
    private String vehicleNumber;
    private boolean isBooked;
    // default Price
    private Double cost;



    public Vehicle(VehicleType vehicleType, String vehicleNumber, Double cost) {
        this.vehicleId = UUID.randomUUID().toString();
        this.vehicleType = vehicleType;
        this.vehicleNumber = vehicleNumber;
        this.cost = cost;
        this.isBooked = false;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }


    public boolean isBooked() {
        return isBooked;
    }

    public Double getCost() {
        return cost;
    }

    public void setBooked(boolean booked) {
        isBooked = booked;
    }
}
