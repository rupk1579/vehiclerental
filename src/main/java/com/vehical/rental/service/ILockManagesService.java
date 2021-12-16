package com.vehical.rental.service;

import com.vehical.rental.model.Vehicle;
import com.vehical.rental.model.VehicleLock;

import java.util.List;

public interface ILockManagesService {

    void setLockOnVehicle(Vehicle vehicle);

    void removeLockOnVehicle(String vehicleId);

    List<VehicleLock> getAllVehicleLock();

}
