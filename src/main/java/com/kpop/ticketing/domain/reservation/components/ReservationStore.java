package com.kpop.ticketing.domain.reservation.components;

import org.springframework.stereotype.Service;

import com.kpop.ticketing.domain.reservation.model.Reservation;
import com.kpop.ticketing.domain.reservation.repository.ReservationStoreRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReservationStore {
	private final ReservationStoreRepository reservationStoreRepository;

	public Reservation save(Reservation reservation) {
		return reservationStoreRepository.save(reservation);
	}

}
