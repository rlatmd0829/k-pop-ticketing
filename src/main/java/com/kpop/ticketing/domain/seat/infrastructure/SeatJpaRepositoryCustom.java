package com.kpop.ticketing.domain.seat.infrastructure;

import java.util.List;
import java.util.Optional;

import com.kpop.ticketing.domain.seat.model.Seat;

public interface SeatJpaRepositoryCustom {
	Optional<Seat> getSeat(Long seatId);
	List<Seat> getSeats(Long showId);
}
