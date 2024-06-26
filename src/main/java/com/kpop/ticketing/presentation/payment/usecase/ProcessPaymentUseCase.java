package com.kpop.ticketing.presentation.payment.usecase;

import com.kpop.ticketing.domain.common.annotation.DistributedLock;
import com.kpop.ticketing.domain.common.aspect.LockType;
import com.kpop.ticketing.domain.payment.event.PaymentCompletedEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com.kpop.ticketing.domain.payment.components.PaymentStore;
import com.kpop.ticketing.domain.payment.model.Payment;
import com.kpop.ticketing.domain.reservation.components.ReservationReader;
import com.kpop.ticketing.domain.reservation.model.Reservation;
import com.kpop.ticketing.domain.user.components.UserReader;
import com.kpop.ticketing.domain.user.model.User;

import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@Transactional
public class ProcessPaymentUseCase {
	private final UserReader userReader;
	private final PaymentStore paymentStore;
	private final ReservationReader reservationReader;
	private final ApplicationEventPublisher eventPublisher;

	@DistributedLock(lockType = LockType.POINT)
	public void execute(Long reservationId) {
		Reservation reservation = reservationReader.getReservation(reservationId);
		User user = userReader.getUser(reservation.getUser().getId());

		// 유저 잔액 감소
		user.withdrawBalance(reservation.getAmount());

		// 좌석 결제까지 완료
		reservation.complete();

		Payment payment = Payment.create(reservation.getAmount(), user, reservation);
		paymentStore.save(payment);

		// 결제 완료 이벤트 발행
		eventPublisher.publishEvent(new PaymentCompletedEvent(payment));
	}
}
