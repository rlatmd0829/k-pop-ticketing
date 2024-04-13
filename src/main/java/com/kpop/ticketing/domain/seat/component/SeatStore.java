package com.kpop.ticketing.domain.seat.component;

import org.springframework.stereotype.Service;

import com.kpop.ticketing.domain.seat.model.Seat;
import com.kpop.ticketing.domain.seat.repository.SeatStoreRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SeatStore {
	private final SeatStoreRepository seatStoreRepository;

	public void store(Seat seat) {
		seatStoreRepository.save(seat);
	}
}
