package com.kpop.ticketing.domain.reservation.infrastructure;

import org.springframework.stereotype.Repository;

import com.kpop.ticketing.domain.reservation.model.Reservation;
import com.kpop.ticketing.domain.reservation.repository.ReservationWriterRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ReservationWriterRepositoryImpl implements ReservationWriterRepository {

	private final ReservationJpaRepository reservationJpaRepository;

	@Override
	public void save(Reservation reservation) {
		reservationJpaRepository.save(reservation);
	}
}
