package com.kpop.ticketing.domain.reservation.infrastructure;

import java.util.Optional;

import com.kpop.ticketing.domain.reservation.model.Reservation;

public interface ReservationJpaRepositoryCustom {
	Optional<Reservation> getReservation(Long reservationId);
}
