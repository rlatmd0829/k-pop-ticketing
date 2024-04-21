package com.kpop.ticketing.domain.seat.infrastructure;

import java.util.List;
import java.util.Optional;

import com.kpop.ticketing.domain.seat.model.Seat;
import com.kpop.ticketing.domain.seat.model.SeatStatus;

public interface SeatJpaRepositoryCustom {
	Optional<Seat> getSeat(Long seatId);
	Optional<Seat> getSeatForUpdate(Long seatId);
	List<Seat> getSeatsByShowIdAndStatus(Long showId, SeatStatus status);
	List<Seat> getSeatsByStatus(SeatStatus status);
}
