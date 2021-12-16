package com.vehical.rental.model;

import com.vehical.rental.enums.PaymentMode;
import com.vehical.rental.enums.PaymentStatus;

import java.util.UUID;

public class PaymentInfo {

    private String paymentId;
    private PaymentMode paymentMode;
    private PaymentStatus paymentStatus;

    public PaymentInfo(PaymentMode paymentMode, PaymentStatus paymentStatus) {
        this.paymentId = UUID.randomUUID().toString();
        this.paymentMode = paymentMode;
        this.paymentStatus = paymentStatus;
    }
}
