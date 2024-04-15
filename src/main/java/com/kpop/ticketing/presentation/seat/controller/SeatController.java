package com.kpop.ticketing.presentation.seat.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kpop.ticketing.presentation.seat.dto.response.SeatListResponse;
import com.kpop.ticketing.presentation.seat.usecase.GetSeatsUseCase;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/shows")
@RequiredArgsConstructor
@Tag(name = "Seat", description = "공연 좌석 API")
public class SeatController {

	private final GetSeatsUseCase getSeatsUseCase;

	@Operation(summary = "Get Available Seats", description = "예약 가능한 좌석 리스트 조회", tags = "Seat")
	@GetMapping("/{showId}/seats")
	public List<SeatListResponse> getSeats(
		@RequestHeader String token,
		@PathVariable Long showId
	) {
		return getSeatsUseCase.execute(showId);
	}
}
