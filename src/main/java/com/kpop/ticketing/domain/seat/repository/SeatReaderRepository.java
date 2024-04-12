package com.kpop.ticketing.domain.seat.repository;

import java.util.List;
import java.util.Optional;

import com.kpop.ticketing.domain.seat.model.Seat;

public interface SeatReaderRepository {
	Optional<Seat> getSeat(Long seatId);
	List<Seat> getSeats(Long showId);

}