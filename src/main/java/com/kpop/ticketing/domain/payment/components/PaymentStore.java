package com.kpop.ticketing.domain.payment.components;

import org.springframework.stereotype.Service;

import com.kpop.ticketing.domain.payment.model.Payment;
import com.kpop.ticketing.domain.payment.repository.PaymentStoreRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentStore {
	private final PaymentStoreRepository paymentStoreRepository;

	public void save(Payment payment) {
		paymentStoreRepository.save(payment);
	}
}
