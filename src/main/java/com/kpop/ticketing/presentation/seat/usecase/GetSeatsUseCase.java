package com.kpop.ticketing.presentation.seat.usecase;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kpop.ticketing.domain.seat.Seat;
import com.kpop.ticketing.domain.seat.SeatReader;
import com.kpop.ticketing.domain.show.ShowReader;
import com.kpop.ticketing.presentation.seat.dto.response.SeatListResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GetSeatsUseCase {
	private final SeatReader seatReader;
	private final ShowReader showReader;

	public List<SeatListResponse> execute(Long showId) {
		// show가 있는지 이런식으로 체크하는게 맞나..?
		showReader.getShow(showId);

		List<Seat> seats = seatReader.getSeats(showId);
		return seats.stream().map(SeatListResponse::from).toList();
	}
}
