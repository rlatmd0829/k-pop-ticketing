package com.kpop.ticketing.presentation.seat.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kpop.ticketing.presentation.seat.dto.response.SeatListResponse;
import com.kpop.ticketing.presentation.seat.usecase.GetSeatsUseCase;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/shows")
@RequiredArgsConstructor
public class SeatController {

	private final GetSeatsUseCase getSeatsUseCase;

	@GetMapping("/{showId}/seats")
	public List<SeatListResponse> getSeats(
		@PathVariable Long showId
	) {
		return getSeatsUseCase.execute(showId);
	}
}
