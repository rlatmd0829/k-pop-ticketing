package com.kpop.ticketing.presentation.reservation.usecase;

import org.springframework.stereotype.Service;

import com.kpop.ticketing.domain.reservation.ReservationWriter;
import com.kpop.ticketing.domain.seat.Seat;
import com.kpop.ticketing.domain.seat.SeatReader;
import com.kpop.ticketing.domain.seat.SeatWriter;
import com.kpop.ticketing.domain.user.User;
import com.kpop.ticketing.domain.user.UserReader;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
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
