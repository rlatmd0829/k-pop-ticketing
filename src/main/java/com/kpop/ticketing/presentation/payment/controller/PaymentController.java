package com.kpop.ticketing.presentation.payment.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class PaymentController {

	@PostMapping("/{userId}/payments")
	public ResponseEntity<Void> createPayment() {
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
