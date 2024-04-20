package com.kpop.ticketing.presentation.payment.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kpop.ticketing.presentation.payment.usecase.ProcessPaymentUseCase;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/reservations")
@RequiredArgsConstructor
public class PaymentController {
	private final ProcessPaymentUseCase processPaymentUseCase;

	@PostMapping("/{reservationId}/payments")
	public ResponseEntity<Void> create(
		@PathVariable Long reservationId
	) {
		processPaymentUseCase.execute(reservationId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
