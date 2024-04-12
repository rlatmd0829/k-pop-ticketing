package com.kpop.ticketing.domain.seat.infrastructure;

import org.springframework.stereotype.Repository;

import com.kpop.ticketing.domain.seat.model.Seat;
import com.kpop.ticketing.domain.seat.repository.SeatWriterRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class SeatWriterRepositoryImpl implements SeatWriterRepository {
	private final SeatJpaRepository seatJpaRepository;

	public void save(Seat seat) {
		seatJpaRepository.save(seat);
	}
}
