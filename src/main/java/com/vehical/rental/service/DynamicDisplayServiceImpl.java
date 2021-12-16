package com.vehical.rental.service;

import com.vehical.rental.model.Branch;
import com.vehical.rental.model.Vehicle;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class DynamicDisplayServiceImpl implements IDisplayService {

    private ILockManagesService lockManagesService;

    public DynamicDisplayServiceImpl(ILockManagesService lockManagesService) {
        this.lockManagesService = lockManagesService;
    }

    @Override
    public void displayAllAvailableVehicles(Branch branch) {

        List<Vehicle> allVehicle = branch.getVehicles();
        List<Vehicle> bookedVehicles = branch.getVehicles().stream()
                .filter(vehicle -> vehicle.isBooked()).collect(Collectors.toList());
        allVehicle.removeAll(lockManagesService.getAllVehicleLock());
        allVehicle.removeAll(bookedVehicles);
        allVehicle.sort(Comparator.comparingDouble(Vehicle::getCost));
        allVehicle.forEach(vehicle -> {
            Double cost = (vehicle.getCost() + vehicle.getCost() * 10.0) / 100;
            System.out.println("Vehicle Number:" + vehicle.getVehicleNumber() + " Price:" + cost);
        });
    }
}
