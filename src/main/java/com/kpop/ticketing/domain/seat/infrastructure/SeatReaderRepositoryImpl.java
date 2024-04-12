package com.kpop.ticketing.domain.seat.infrastructure;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.kpop.ticketing.domain.seat.model.Seat;
import com.kpop.ticketing.domain.seat.repository.SeatReaderRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class SeatReaderRepositoryImpl implements SeatReaderRepository {
	private final SeatJpaRepository seatJpaRepository;

	@Override
	public Optional<Seat> getSeat(Long seatId) {
		return seatJpaRepository.getSeat(seatId);
	}

	@Override
	public List<Seat> getSeats(Long showId) {
		return seatJpaRepository.getSeats(showId);
	}
}