package com.kpop.ticketing.domain.payment.infrastructure;

import java.util.Optional;

import com.kpop.ticketing.domain.payment.model.Payment;

public interface PaymentJpaRepositoryCustom {
	Optional<Payment> getPayment(Long paymentId);
	Optional<Payment> getPaymentByReservationId(Long reservationId);
}
