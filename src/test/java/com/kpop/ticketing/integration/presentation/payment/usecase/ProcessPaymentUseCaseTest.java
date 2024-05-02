package com.kpop.ticketing.integration.presentation.payment.usecase;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.kpop.ticketing.domain.concert.infrastructure.ConcertJpaRepository;
import com.kpop.ticketing.domain.payment.infrastructure.PaymentJpaRepository;
import com.kpop.ticketing.domain.reservation.infrastructure.ReservationJpaRepository;
import com.kpop.ticketing.domain.seat.infrastructure.SeatJpaRepository;
import com.kpop.ticketing.domain.show.infrastructure.ShowJpaRepository;
import com.kpop.ticketing.domain.user.infrastructure.UserJpaRepository;
import com.kpop.ticketing.domain.user.model.User;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.junit.jupiter.api.AfterEach;
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

import org.springframework.transaction.annotation.Transactional;

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
	@Autowired
	private UserJpaRepository userJpaRepository;
	@Autowired
	private ReservationJpaRepository reservationJpaRepository;
	@Autowired
	private PaymentJpaRepository paymentJpaRepository;

	@AfterEach
	void tearDown() {
		paymentJpaRepository.deleteAll();
		reservationJpaRepository.deleteAll();
		userJpaRepository.deleteAll();
	}

	@Test
	@DisplayName("결제 처리 테스트")
	void processPaymentUseCaseTest() {
		// when
		processPaymentUseCase.execute(1L);
		Reservation reservation = reservationReader.getReservation(1L);
		Payment payment = paymentReader.getPaymentByReservationId(1L);

		// then
		assertThat(reservation.getStatus()).isEqualTo(ReservationStatus.COMPLETED);
		assertThat(payment.getAmount()).isEqualTo(reservation.getAmount());
		assertThat(payment.getStatus()).isEqualTo(PaymentStatus.COMPLETED);
	}

	@Test
	@DisplayName("결제 처리 테스트 - 동시성 테스트")
	// 동시성 테스트할때 @Transactional을 붙여주면 각 스레드가 별도의 트랜잭션을 사용하게 되어 원하는 결과를 얻을 수 없다.
	// SQL로 직접 값을 넣었고, 해당 값이 커밋되지 않은 경우에는 @Transactional 어노테이션으로 인해 다른 스레드에서 해당 값을 참조할 수 없을 수 있습니다.
	void processPaymentUseCaseConcurrencyTest() throws InterruptedException {
		// given
		int numberOfThreads = 10;
		ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
		CountDownLatch countDownLatch = new CountDownLatch(numberOfThreads);

		// when
		for (int i = 0; i < numberOfThreads; i++) {
			executorService.execute(() -> {
				try {
					processPaymentUseCase.execute(1L);
				} finally {
					countDownLatch.countDown();
				}
			});
		}
		countDownLatch.await();
		executorService.shutdown();

		// then
		assertThat(userJpaRepository.findById(1L).get().getBalance()).isEqualTo(0);
		assertThat(paymentJpaRepository.findAll().size()).isEqualTo(1);
	}
}
