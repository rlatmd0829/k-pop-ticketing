package com.kpop.ticketing.integration.presentation.payment.usecase;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import com.kpop.ticketing.domain.payment.components.PaymentReader;
import com.kpop.ticketing.domain.payment.model.Payment;
import com.kpop.ticketing.domain.payment.model.PaymentStatus;
import com.kpop.ticketing.domain.reservation.components.ReservationReader;
import com.kpop.ticketing.domain.reservation.model.Reservation;
import com.kpop.ticketing.domain.reservation.model.ReservationStatus;
import com.kpop.ticketing.presentation.payment.usecase.ProcessPaymentUseCase;

@SpringBootTest
@ActiveProfiles("test")
@Sql(scripts = {
	"classpath:data/users.sql",
	"classpath:data/concerts.sql",
	"classpath:data/shows.sql",
	"classpath:data/seats.sql",
	"classpath:data/reservations.sql",
})
class ProcessPaymentUseCaseTest {
	@Autowired
	private ProcessPaymentUseCase processPaymentUseCase;
	@Autowired
	private ReservationReader reservationReader;
	@Autowired
	private PaymentReader paymentReader;

	@Test
	@DisplayName("결제 처리 테스트")
	void processPaymentUseCaseTest() {
		// when
		processPaymentUseCase.execute(1L);
		Reservation reservation = reservationReader.getReservation(1L);
		Payment payment = paymentReader.getPayment(1L);

		// then
		assertThat(reservation.getStatus()).isEqualTo(ReservationStatus.COMPLETED);
		assertThat(payment.getAmount()).isEqualTo(reservation.getAmount());
		assertThat(payment.getStatus()).isEqualTo(PaymentStatus.COMPLETED);
	}
}
