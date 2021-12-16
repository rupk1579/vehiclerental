package com.vehical.rental.service;

import com.vehical.rental.exception.InvalidParameterException;
import com.vehical.rental.model.Branch;
import com.vehical.rental.model.Vehicle;
import com.vehical.rental.repo.InMemoryStorageRepo;

import java.util.List;

public class AdminServiceImpl  implements IAdminService{

    private InMemoryStorageRepo inMemoryStorageRepo;

    public AdminServiceImpl(InMemoryStorageRepo inMemoryStorageRepo) {
        this.inMemoryStorageRepo = inMemoryStorageRepo;
    }

    public void AddBranch(List<Branch> branches) {
        branches.forEach(branch -> {
            inMemoryStorageRepo.getBranchMap().put(branch.getBranchId(),branch);
            AddVehicle(branch,branch.getVehicles());
        });
    }

    public void AddVehicle(Branch branch, List<Vehicle> vehicles) {
        if(!inMemoryStorageRepo.getBranchMap().containsKey(branch.getBranchId()))
           inMemoryStorageRepo.getBranchMap().put(branch.getBranchId(),branch);
        inMemoryStorageRepo.getBranchMap().get(branch.getBranchId()).getVehicles().addAll(vehicles);
        vehicles.forEach(vehicle -> {
            inMemoryStorageRepo.getVehicleMap().put(vehicle.getVehicleId(),vehicle);
        });

    }
}
