package com.vehical.rental.factory;

import com.vehical.rental.model.Branch;
import com.vehical.rental.model.Vehicle;
import com.vehical.rental.service.DynamicDisplayServiceImpl;
import com.vehical.rental.service.FixedDisplayService;
import com.vehical.rental.service.IDisplayService;
import com.vehical.rental.service.ILockManagesService;

public class DisplayFactory {

    private ILockManagesService lockManagesService;

    public DisplayFactory(ILockManagesService lockManagesService) {
        this.lockManagesService = lockManagesService;
    }

    public IDisplayService getDisplayMethod(Branch branch) {
        boolean isEightPer = isBookingIncreasedEightyPer(branch);
        if (isEightPer) return new DynamicDisplayServiceImpl(lockManagesService);
        return new FixedDisplayService(lockManagesService);
    }

    private boolean isBookingIncreasedEightyPer(Branch branch) {
        int totalVehicle = branch.getVehicles().size();
        int totalBookedVehicle = 0;
        for (Vehicle vehicle : branch.getVehicles()) {
            if (vehicle.isBooked()) totalBookedVehicle++;
        }
        if ((totalBookedVehicle * 100) /totalVehicle  >= 80) return true;
        return false;
    }
}
