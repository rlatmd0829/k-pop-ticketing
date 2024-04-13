package com.kpop.ticketing.domain.reservation.infrastructure;

import org.springframework.stereotype.Repository;

import com.kpop.ticketing.domain.reservation.model.Reservation;
import com.kpop.ticketing.domain.reservation.repository.ReservationStoreRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ReservationStoreRepositoryImpl implements ReservationStoreRepository {

	private final ReservationJpaRepository reservationJpaRepository;

	@Override
	public Reservation save(Reservation reservation) {
		return reservationJpaRepository.save(reservation);
	}
}
