package com.kpop.ticketing.presentation.seat.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/shows")
public class SeatController {

	@GetMapping("/{showId}/seats")
	public String getSeats() {
		return """
			[
			  {
			    "seatId" : "1",
			    "seatNumber" : "10"
			  },
			  {
			    "seatId" : "2",
			    "seatNumber" : "15"
			  },
			  {
			    "seatId" : "3",
			    "seatNumber" : "47"
			  }
			]
			""";
	}
}
