package com.vehical.rental.repo;

import com.vehical.rental.model.Branch;
import com.vehical.rental.model.Vehicle;

import java.util.HashMap;
import java.util.Map;

public class InMemoryStorageRepo {

    private Map<String, Branch>  branchMap;
    private Map<String,Vehicle>   vehicleMap;
    private Map<String,Map<String,Double>> priceMap;

    private static InMemoryStorageRepo instance = null;

    private InMemoryStorageRepo(){
        branchMap =  new HashMap<>();
        vehicleMap = new HashMap<>();
        priceMap = new HashMap<>();
    }

    public static InMemoryStorageRepo getInstance(){
        if(instance != null) return instance;
        synchronized (InMemoryStorageRepo.class){
            if(instance == null) {
                instance = new InMemoryStorageRepo();
            }
            return instance;
        }

    }

    public  Map<String, Branch> getBranchMap() {
        return branchMap;
    }

    public  Map<String, Vehicle> getVehicleMap() {
        return vehicleMap;
    }
}
