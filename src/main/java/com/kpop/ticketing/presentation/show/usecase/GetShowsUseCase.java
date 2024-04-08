package com.kpop.ticketing.presentation.show.usecase;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kpop.ticketing.domain.seat.Seat;
import com.kpop.ticketing.domain.seat.SeatReader;
import com.kpop.ticketing.domain.show.Show;
import com.kpop.ticketing.domain.show.ShowReader;
import com.kpop.ticketing.presentation.show.dto.response.ShowListResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class GetShowsUseCase {
	private final ShowReader showReader;

	public List<ShowListResponse> execute(Long concertId) {
		List<Show> seats = showReader.getShows(concertId);
		return seats.stream().map(ShowListResponse::from).toList();
	}
}
