package com.vehical.rental.model;

import com.vehical.rental.enums.BookingStatus;

import java.util.Date;
import java.util.UUID;

public class BookingInfo {

    private String bookingId;
    private String vehicleNumber;
    private String vehicleId;
    private Double price;
    private BookingStatus bookingStatus;
    private Date createdDate;

    public BookingInfo(String vehicleNumber, Double price,String vehicleId) {
        this.bookingId = UUID.randomUUID().toString();
        this.vehicleNumber = vehicleNumber;
        this.price = price;
        this.vehicleId = vehicleId;
        this.bookingStatus = BookingStatus.CREATED;
        this.createdDate =  new Date();
    }

    public String getBookingId() {
        return bookingId;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public Double getPrice() {
        return price;
    }

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setBookingStatus(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
    }
}
