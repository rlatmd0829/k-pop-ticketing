package com.kpop.ticketing.domain.reservation.infra;

import org.springframework.stereotype.Repository;

import com.kpop.ticketing.domain.reservation.Reservation;
import com.kpop.ticketing.domain.reservation.ReservationRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ReservationRepositoryImpl implements ReservationRepository {

	private final ReservationJpaRepository reservationJpaRepository;

	@Override
	public void save(Reservation reservation) {
		reservationJpaRepository.save(reservation);
	}
}
