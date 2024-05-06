package com.kpop.ticketing.presentation.reservation.usecase;

import com.kpop.ticketing.domain.common.annotation.DistributedLock;
import com.kpop.ticketing.domain.common.aspect.LockType;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.kpop.ticketing.domain.common.exception.CustomException;
import com.kpop.ticketing.domain.common.exception.ErrorCode;
import com.kpop.ticketing.domain.reservation.components.ReservationStore;
import com.kpop.ticketing.domain.reservation.model.Reservation;
import com.kpop.ticketing.domain.seat.model.Seat;
import com.kpop.ticketing.domain.seat.component.SeatReader;
import com.kpop.ticketing.domain.seat.component.SeatStore;
import com.kpop.ticketing.domain.user.model.User;
import com.kpop.ticketing.domain.user.components.UserReader;
import com.kpop.ticketing.domain.waittoken.components.WaitTokenReader;
import com.kpop.ticketing.domain.waittoken.model.WaitToken;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@Transactional
public class ReserveSeatUseCase {
	private final UserReader userReader;
	private final SeatReader seatReader;
	private final SeatStore seatStore;
	private final ReservationStore reservationStore;
	private final WaitTokenReader waitTokenReader;

	@DistributedLock(lockType = LockType.RESERVATION)
	public void execute(String token, Long seatId) {
		WaitToken waitToken = waitTokenReader.getWaitTokenByToken(token);

		User user = userReader.getUser(waitToken.getUser().getId());
		Seat seat = seatReader.getSeat(seatId);

		// 좌석 상태체크
		seat.validateSeatStatus();
		// 좌석 선점
		seat.hold();

		Reservation reservation = Reservation.create(seat, user);

		reservationStore.save(reservation);
		seatStore.save(seat);
		// 토큰 만료
		waitToken.expire();
	}
}
