package com.vehical.rental.model;

import java.time.Instant;
import java.util.Date;

public class VehicleLock {

    private Date lockDate;
    private Vehicle vehicle;
    private int lockTimeOut;

    public VehicleLock(Vehicle vehicle,int lockTimeOut) {
        this.vehicle = vehicle;
        this.lockDate =  new Date();
        this.lockTimeOut = lockTimeOut;
    }
    public boolean isLockExpired(){
        Instant lockInstant = lockDate.toInstant().plusSeconds(lockTimeOut);
        Instant currentInstant = new Date().toInstant();
        return lockInstant.isBefore(currentInstant);
    }

}
