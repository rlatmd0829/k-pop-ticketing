package com.kpop.ticketing.domain.seat;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SeatReader {
	private final SeatReaderRepository seatReaderRepository;

	public List<Seat> getSeats(Long showId) {
		return seatReaderRepository.getSeats(showId);
	}
}
