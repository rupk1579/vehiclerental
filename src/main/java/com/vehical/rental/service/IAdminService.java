package com.vehical.rental.service;

import com.vehical.rental.model.Branch;
import com.vehical.rental.model.Vehicle;

import java.util.List;

public interface IAdminService {

    void AddBranch(List<Branch> branches);

    void AddVehicle(Branch branch, List<Vehicle> vehicles);
}
