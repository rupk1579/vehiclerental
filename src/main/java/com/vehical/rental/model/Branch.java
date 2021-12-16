package com.vehical.rental.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class Branch {

    private String branchId;
    private String branchName;
    private Address address;
    List<Vehicle> vehicles;

    public Branch(String branchName, Address address) {
        this.branchId = UUID.randomUUID().toString();
        this.branchName = branchName;
        this.address = address;
        vehicles = new ArrayList<Vehicle>();
    }

    public String getBranchName() {
        return branchName;
    }

    public Address getAddress() {
        return address;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public String getBranchId() {
        return branchId;
    }
}
