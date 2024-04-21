package com.kpop.ticketing.domain.payment.infrastructure;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.kpop.ticketing.domain.payment.model.Payment;
import com.kpop.ticketing.domain.payment.repository.PaymentReaderRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class PaymentReaderRepositoryImpl implements PaymentReaderRepository {
	private final PaymentJpaRepository paymentJpaRepository;

	public Optional<Payment> getPayment(Long paymentId) {
		return paymentJpaRepository.getPayment(paymentId);
	}
}
