package com.kpop.ticketing.domain.reservation;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kpop.ticketing.domain.seat.Seat;
import com.kpop.ticketing.domain.user.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ReservationWriter {
	private final ReservationRepository reservationRepository;

	public void create(Seat seat, User user) {
		Reservation reservation = Reservation.create(seat, user);
		reservationRepository.save(reservation);
	}

}
