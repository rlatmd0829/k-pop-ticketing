package com.kpop.ticketing.domain.payment.infrastructure;

import org.springframework.stereotype.Repository;

import com.kpop.ticketing.domain.payment.model.Payment;
import com.kpop.ticketing.domain.payment.repository.PaymentStoreRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class PaymentStoreRepositoryImpl implements PaymentStoreRepository {

	private final PaymentJpaRepository paymentJpaRepository;

	public Payment save(Payment payment) {
		return paymentJpaRepository.save(payment);
	}
}
