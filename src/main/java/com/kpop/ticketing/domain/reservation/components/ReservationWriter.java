package com.kpop.ticketing.domain.reservation.components;

import org.springframework.stereotype.Service;

import com.kpop.ticketing.domain.reservation.repository.ReservationRepository;
import com.kpop.ticketing.domain.reservation.model.Reservation;
import com.kpop.ticketing.domain.seat.model.Seat;
import com.kpop.ticketing.domain.user.model.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReservationWriter {
	private final ReservationRepository reservationRepository;

	public void create(Seat seat, User user) {
		Reservation reservation = Reservation.create(seat, user);
		reservationRepository.save(reservation);
	}

}
