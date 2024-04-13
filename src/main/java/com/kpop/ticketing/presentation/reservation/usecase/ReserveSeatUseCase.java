package com.kpop.ticketing.presentation.reservation.usecase;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.kpop.ticketing.domain.reservation.components.ReservationStore;
import com.kpop.ticketing.domain.reservation.model.Reservation;
import com.kpop.ticketing.domain.seat.model.Seat;
import com.kpop.ticketing.domain.seat.component.SeatReader;
import com.kpop.ticketing.domain.seat.component.SeatStore;
import com.kpop.ticketing.domain.user.model.User;
import com.kpop.ticketing.domain.user.components.UserReader;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@Transactional
public class ReserveSeatUseCase {
	private final UserReader userReader;
	private final SeatReader seatReader;
	private final SeatStore seatStore;
	private final ReservationStore reservationStore;

	public void execute(Long seatId) {
		// TODO Token에서 userId 가져오기
		User user = userReader.getUser(1L);
		Seat seat = seatReader.getSeat(seatId);
		seat.reserve();
		// 생성은 여기서 하고 컴포넌트에 안보내느게 나을듯?
		Reservation reservation = Reservation.create(seat, user);

		reservationStore.store(reservation);
		seatStore.store(seat);
	}

}
