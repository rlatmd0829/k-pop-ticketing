package com.kpop.ticketing.domain.reservation.infrastructure;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.kpop.ticketing.domain.reservation.model.Reservation;
import com.kpop.ticketing.domain.reservation.repository.ReservationReaderRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ReservationReaderRepositoryImpl implements ReservationReaderRepository {
	private final ReservationJpaRepository reservationJpaRepository;

	public Optional<Reservation> getReservation(Long reservationId) {
		return reservationJpaRepository.getReservation(reservationId);
	}


}
