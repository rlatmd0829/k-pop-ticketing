package com.kpop.ticketing.domain.seat.infrastructure;

import org.springframework.stereotype.Repository;

import com.kpop.ticketing.domain.seat.model.Seat;
import com.kpop.ticketing.domain.seat.repository.SeatStoreRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class SeatStoreRepositoryImpl implements SeatStoreRepository {
	private final SeatJpaRepository seatJpaRepository;

	public Seat save(Seat seat) {
		return seatJpaRepository.save(seat);
	}
}
