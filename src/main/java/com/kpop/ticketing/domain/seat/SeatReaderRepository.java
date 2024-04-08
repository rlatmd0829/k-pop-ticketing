package com.kpop.ticketing.domain.seat;

import java.util.List;

public interface SeatReaderRepository {
	List<Seat> getSeats(Long showId);
}
