package com.vehical.rental.service;

import com.vehical.rental.enums.BookingStatus;
import com.vehical.rental.enums.PaymentMode;
import com.vehical.rental.enums.PaymentStatus;
import com.vehical.rental.model.BookingInfo;
import com.vehical.rental.model.PaymentInfo;

public class PaymentService implements IPaymentService {
    private ILockManagesService lockManagesService;

    private  IBookedVehicleService bookedVehicleService;

    public PaymentService(ILockManagesService lockManagesService,IBookedVehicleService bookedVehicleService) {
        this.lockManagesService = lockManagesService;
        this.bookedVehicleService = bookedVehicleService;
    }

    @Override
    public PaymentInfo makeSuccessfulPayment(BookingInfo bookingInfo, PaymentMode paymentMode,PaymentStatus paymentStatus) {

        bookedVehicleService.confirmBooking(bookingInfo);
        return buildPaymentInfo(bookingInfo, paymentMode, PaymentStatus.SUCCESS);
    }

    @Override
    public PaymentInfo makeFailedPayment(BookingInfo bookingInfo, PaymentMode paymentMode, PaymentStatus paymentStatus) {
        lockManagesService.removeLockOnVehicle(bookingInfo.getVehicleId());
        return buildPaymentInfo(bookingInfo,paymentMode,PaymentStatus.FAILED);
    }


    private PaymentInfo buildPaymentInfo(BookingInfo bookingInfo, PaymentMode paymentMode, PaymentStatus paymentStatus) {
        return new PaymentInfo(paymentMode, paymentStatus);

    }
}
