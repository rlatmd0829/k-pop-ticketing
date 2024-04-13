package com.kpop.ticketing.domain.seat.repository;

import com.kpop.ticketing.domain.seat.model.Seat;

public interface SeatStoreRepository {
	void save(Seat seat);
}
