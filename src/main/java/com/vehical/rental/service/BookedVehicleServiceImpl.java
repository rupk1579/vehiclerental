package com.vehical.rental.service;


import com.vehical.rental.enums.BookingStatus;
import com.vehical.rental.exception.AlreadyBookedException;
import com.vehical.rental.model.BookingInfo;
import com.vehical.rental.model.Vehicle;
import com.vehical.rental.repo.InMemoryStorageRepo;

import java.util.HashMap;
import java.util.Map;

public class BookedVehicleServiceImpl implements IBookedVehicleService {

    private InMemoryStorageRepo inMemoryStorageRepo;

    private ILockManagesService lockMangedService;

    private final Map<String, BookingInfo> bookingInfoMap;

    public BookedVehicleServiceImpl(InMemoryStorageRepo inMemoryStorageRepo, ILockManagesService lockMangedService) {

        this.inMemoryStorageRepo = inMemoryStorageRepo;
        this.lockMangedService = lockMangedService;
        this.bookingInfoMap = new HashMap<>();
    }

    @Override
    public BookingInfo bookParticularVehicle(Vehicle vehicle, Double price) {

        if (isAlreadyBookingDone(vehicle)) throw new AlreadyBookedException();
        lockMangedService.setLockOnVehicle(vehicle);
        BookingInfo bookingInfo = buildBookingResponse(vehicle, price);
        bookingInfoMap.put(bookingInfo.getBookingId(), bookingInfo);
        return bookingInfo;

    }

    @Override
    public void dropVehicleToBranch(Vehicle vehicle) {
        inMemoryStorageRepo.getVehicleMap().get(vehicle.getVehicleId()).setBooked(false);
        lockMangedService.removeLockOnVehicle(vehicle.getVehicleId());

    }

    @Override
    public void confirmBooking(BookingInfo bookingInfo) {
        updateVehicleMap(inMemoryStorageRepo.getVehicleMap().get(bookingInfo.getVehicleId()));
        bookingInfoMap.get(bookingInfo.getBookingId()).setBookingStatus(BookingStatus.COMPLETED);
    }

    private void updateVehicleMap(Vehicle vehicle) {
        inMemoryStorageRepo.getVehicleMap().get(vehicle.getVehicleId()).setBooked(true);
    }

    private BookingInfo buildBookingResponse(Vehicle vehicle, Double price) {
        return new BookingInfo(vehicle.getVehicleNumber(), price, vehicle.getVehicleId());
    }

    private boolean isAlreadyBookingDone(Vehicle vehicle) {
        for (Map.Entry<String, BookingInfo> bookingInfoEntry : bookingInfoMap.entrySet()) {
            BookingInfo bookingInfo = bookingInfoEntry.getValue();
            if (bookingInfo.getVehicleId().equals(vehicle.getVehicleId())
                    && bookingInfo.getBookingStatus().equals(BookingStatus.COMPLETED)) return true;
        }
        return false;
    }
}
