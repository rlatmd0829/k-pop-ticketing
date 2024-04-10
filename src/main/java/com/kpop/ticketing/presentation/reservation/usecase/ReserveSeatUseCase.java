package com.kpop.ticketing.presentation.reservation.usecase;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.kpop.ticketing.domain.reservation.component.ReservationWriter;
import com.kpop.ticketing.domain.seat.model.Seat;
import com.kpop.ticketing.domain.seat.component.SeatReader;
import com.kpop.ticketing.domain.seat.component.SeatWriter;
import com.kpop.ticketing.domain.user.model.User;
import com.kpop.ticketing.domain.user.component.UserReader;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@Transactional
public class ReserveSeatUseCase {
	private final UserReader userReader;
	private final SeatReader seatReader;
	private final SeatWriter seatWriter;
	private final ReservationWriter reservationWriter;

	public void execute(Long seatId) {
		// TODO Token에서 userId 가져오기
		User user = userReader.getUser(1L);
		Seat seat = seatReader.getSeat(seatId);
		reservationWriter.create(seat, user);
		seatWriter.reserveSeat(seat);
	}

}
