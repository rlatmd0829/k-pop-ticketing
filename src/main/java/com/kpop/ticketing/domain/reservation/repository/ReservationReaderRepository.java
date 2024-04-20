package com.kpop.ticketing.domain.reservation.repository;

import java.util.Optional;

import com.kpop.ticketing.domain.reservation.model.Reservation;

public interface ReservationReaderRepository {
	Optional<Reservation> getReservation(Long reservationId);
}
