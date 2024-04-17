package com.kpop.ticketing.domain.seat.repository;

import java.util.List;
import java.util.Optional;

import com.kpop.ticketing.domain.seat.model.Seat;
import com.kpop.ticketing.domain.seat.model.SeatStatus;

public interface SeatReaderRepository {
	Optional<Seat> getSeat(Long seatId);
	List<Seat> getSeatsByShowIdAndStatus(Long showId, SeatStatus status);
	List<Seat> getSeatsByStatus(SeatStatus status);

}
