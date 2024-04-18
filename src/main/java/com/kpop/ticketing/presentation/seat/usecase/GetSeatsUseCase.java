package com.kpop.ticketing.presentation.seat.usecase;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.kpop.ticketing.domain.seat.model.Seat;
import com.kpop.ticketing.domain.seat.component.SeatReader;
import com.kpop.ticketing.domain.show.components.ShowReader;
import com.kpop.ticketing.domain.waittoken.components.WaitTokenReader;
import com.kpop.ticketing.presentation.seat.dto.response.SeatListResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GetSeatsUseCase {
	private final SeatReader seatReader;
	private final ShowReader showReader;

	public List<SeatListResponse> execute(Long showId) {
		showReader.getShow(showId);

		List<Seat> emptySeats = seatReader.getEmptySeatsForShow(showId);
		return emptySeats.stream().map(SeatListResponse::from).toList();
	}
}
