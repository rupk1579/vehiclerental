package com.vehical.rental.service;


import com.vehical.rental.model.BookingInfo;
import com.vehical.rental.model.Vehicle;

import java.util.List;

public interface IBookedVehicleService {

    BookingInfo bookParticularVehicle(Vehicle vehicle, Double price);

    void dropVehicleToBranch(Vehicle vehicle);

    void confirmBooking(BookingInfo bookingInfo);



}
