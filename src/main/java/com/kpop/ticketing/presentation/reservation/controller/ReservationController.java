package com.kpop.ticketing.presentation.reservation.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kpop.ticketing.presentation.reservation.usecase.ReserveSeatUseCase;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/seats")
@RequiredArgsConstructor
public class ReservationController {

	private final ReserveSeatUseCase reserveSeatUseCase;

	@PostMapping("/{seatId}/reservations")
	public ResponseEntity<Void> create(
		@PathVariable Long seatId
	) {
		reserveSeatUseCase.execute(seatId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
