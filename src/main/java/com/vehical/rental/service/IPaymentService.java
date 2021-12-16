package com.vehical.rental.service;

import com.vehical.rental.enums.PaymentMode;
import com.vehical.rental.enums.PaymentStatus;
import com.vehical.rental.model.BookingInfo;
import com.vehical.rental.model.PaymentInfo;

public interface IPaymentService {

    PaymentInfo makeSuccessfulPayment(BookingInfo bookingInfo, PaymentMode paymentMode,PaymentStatus paymentStatus);

    PaymentInfo makeFailedPayment(BookingInfo bookingInfo, PaymentMode paymentMode, PaymentStatus paymentStatus);


}
