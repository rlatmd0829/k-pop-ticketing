package com.kpop.ticketing.presentation.reservation.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/shows")
public class ReservationController {

	@PostMapping("/{showId}/seats/{seatId}")
	public ResponseEntity<Void> createReservation() {
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
