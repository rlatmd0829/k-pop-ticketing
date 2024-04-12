package com.kpop.ticketing.domain.seat.repository;

import com.kpop.ticketing.domain.seat.model.Seat;

public interface SeatWriterRepository {
	void save(Seat seat);
}
