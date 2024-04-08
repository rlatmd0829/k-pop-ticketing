package com.kpop.ticketing.domain.seat.infra;

import java.util.List;

import com.kpop.ticketing.domain.seat.Seat;

public interface SeatJpaRepositoryCustom {

	List<Seat> getSeats(Long showId);
}
