package com.kpop.ticketing.domain.payment.repository;

import com.kpop.ticketing.domain.payment.model.Payment;

public interface PaymentStoreRepository {
	Payment save(Payment payment);
}
