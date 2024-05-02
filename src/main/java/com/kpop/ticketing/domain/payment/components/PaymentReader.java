package com.kpop.ticketing.domain.payment.components;

import org.springframework.stereotype.Service;

import com.kpop.ticketing.domain.common.exception.CustomException;
import com.kpop.ticketing.domain.common.exception.ErrorCode;
import com.kpop.ticketing.domain.payment.model.Payment;
import com.kpop.ticketing.domain.payment.repository.PaymentReaderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentReader {
	private final PaymentReaderRepository paymentReaderRepository;

	public Payment getPayment(Long paymentId) {
		return paymentReaderRepository.getPayment(paymentId).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_PAYMENT));
	}

	public Payment getPaymentByReservationId(Long reservationId) {
		return paymentReaderRepository.getPaymentByReservationId(reservationId).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_PAYMENT));
	}
}
