package com.vehical.rental.service;

import com.vehical.rental.exception.TemporaryUnavailableException;
import com.vehical.rental.model.Vehicle;
import com.vehical.rental.model.VehicleLock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LockManagesServiceImpl implements ILockManagesService{

    private final int lockTimeout;
    private final Map<String, VehicleLock> vehicleLockMap;

    public LockManagesServiceImpl(int lockTimeout) {
        this.vehicleLockMap =  new HashMap<>();
        this.lockTimeout = lockTimeout;
    }

    @Override
    public synchronized void setLockOnVehicle(Vehicle vehicle){

        if(isAlreadyLocked(vehicle)) throw  new TemporaryUnavailableException();
        vehicleLockMap.put(vehicle.getVehicleId(),new VehicleLock(vehicle,lockTimeout));
    }

    @Override
    public synchronized void removeLockOnVehicle(String vehicleId) {
        vehicleLockMap.remove(vehicleId);
    }

    @Override
    public List<VehicleLock> getAllVehicleLock() {
        List<VehicleLock> vehicleLocks =  new ArrayList<>();
        for (Map.Entry<String,VehicleLock> vehicleLockEntry : vehicleLockMap.entrySet()){
            vehicleLocks.add(vehicleLockEntry.getValue());
        }
        return  vehicleLocks;
    }


    private boolean isAlreadyLocked(Vehicle vehicle) {
        if(vehicleLockMap.containsKey(vehicle.getVehicleId()) &&
                !vehicleLockMap.get(vehicle.getVehicleId()).isLockExpired()) return true;
        return  false;
    }

}
