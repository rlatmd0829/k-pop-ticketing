package com.kpop.ticketing.domain.reservation.repository;

import com.kpop.ticketing.domain.reservation.model.Reservation;

public interface ReservationWriterRepository {
	void save(Reservation reservation);
}
