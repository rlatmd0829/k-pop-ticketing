package com.kpop.ticketing.domain.payment.event;

import com.kpop.ticketing.domain.payment.model.Payment;

public class PaymentCompletedEvent {
    private final Payment payment;

    public PaymentCompletedEvent(Payment payment) {
        this.payment = payment;
    }

    public Payment getPayment() {
        return payment;
    }
}
