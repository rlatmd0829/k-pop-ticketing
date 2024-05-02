package com.kpop.ticketing.domain.payment.repository;

import java.util.Optional;

import com.kpop.ticketing.domain.payment.model.Payment;

public interface PaymentReaderRepository {
	Optional<Payment> getPayment(Long paymentId);
	Optional<Payment> getPaymentByReservationId(Long reservationId);
}
