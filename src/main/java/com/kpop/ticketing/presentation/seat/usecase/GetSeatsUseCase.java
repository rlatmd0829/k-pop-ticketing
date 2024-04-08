package com.kpop.ticketing.presentation.seat.usecase;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kpop.ticketing.domain.seat.Seat;
import com.kpop.ticketing.domain.seat.SeatReader;
import com.kpop.ticketing.presentation.seat.dto.response.SeatListResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GetSeatsUseCase {
	private final SeatReader seatReader;

	public List<SeatListResponse> execute(Long showId) {
		List<Seat> seats = seatReader.getSeats(showId);
		return seats.stream().map(SeatListResponse::from).toList();
	}
}
