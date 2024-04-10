package com.kpop.ticketing.domain.seat;

import java.util.List;
import java.util.Optional;

public interface SeatRepository {
	Optional<Seat> getSeat(Long seatId);
	List<Seat> getSeats(Long showId);
	void save(Seat seat);
}
