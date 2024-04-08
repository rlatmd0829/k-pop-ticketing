package com.kpop.ticketing.domain.seat.infra;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.kpop.ticketing.domain.seat.Seat;
import com.kpop.ticketing.domain.seat.SeatReaderRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class SeatReaderRepositoryImpl implements SeatReaderRepository {
	private final SeatJpaRepository seatJpaRepository;

	public List<Seat> getSeats(Long showId) {
		return seatJpaRepository.getSeats(showId);
	}
}
