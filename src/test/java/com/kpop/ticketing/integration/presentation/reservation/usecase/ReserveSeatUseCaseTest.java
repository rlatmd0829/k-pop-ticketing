package com.kpop.ticketing.integration.presentation.reservation.usecase;

import static org.assertj.core.api.AssertionsForClassTypes.*;

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

import com.kpop.ticketing.domain.concert.infrastructure.ConcertJpaRepository;
import com.kpop.ticketing.domain.reservation.infrastructure.ReservationJpaRepository;
import com.kpop.ticketing.domain.seat.component.SeatReader;
import com.kpop.ticketing.domain.seat.infrastructure.SeatJpaRepository;
import com.kpop.ticketing.domain.seat.model.Seat;
import com.kpop.ticketing.domain.seat.model.SeatStatus;
import com.kpop.ticketing.domain.show.infrastructure.ShowJpaRepository;
import com.kpop.ticketing.domain.user.infrastructure.UserJpaRepository;
import com.kpop.ticketing.domain.waittoken.infrastucture.WaitTokenJpaRepository;
import com.kpop.ticketing.presentation.reservation.usecase.ReserveSeatUseCase;

@ActiveProfiles("test")
@SpringBootTest
@Sql(scripts = {
	"classpath:data/users.sql",
	"classpath:data/concerts.sql",
	"classpath:data/shows.sql",
	"classpath:data/seats.sql",
	"classpath:data/wait_tokens.sql"
})
class ReserveSeatUseCaseTest {
	@Autowired
	private ReserveSeatUseCase reserveSeatUseCase;

	@Autowired
	private SeatReader seatReader;

	@Autowired
	private WaitTokenJpaRepository waitTokenJpaRepository;

	@Autowired
	private SeatJpaRepository seatJpaRepository;

	@Autowired
	private UserJpaRepository userJpaRepository;

	@Autowired
	private ConcertJpaRepository concertJpaRepository;

	@Autowired
	private ShowJpaRepository showJpaRepository;

	@Autowired
	private ReservationJpaRepository reservationJpaRepository;

	/*
	* 동시성 테스트 할 경우에는 스레드가 여러개가 돌아 sql에서 넣은 값이 롤백되어 원하는 결과가 안나오므로 @Transactional 을 사용하지 않는다.
	* 그래서 해당 클래스에 내용이 롤백이 안되어 다른 테스트 클래스에도 영향이 있으므로 tearDown 메소드를 사용하여 데이터를 삭제한다.
	* */
	@AfterEach
	void tearDown() {
		reservationJpaRepository.deleteAll();
		waitTokenJpaRepository.deleteAll();
		seatJpaRepository.deleteAll();
		showJpaRepository.deleteAll();
		concertJpaRepository.deleteAll();
		userJpaRepository.deleteAll();
	}

	@Test
	@DisplayName("좌석 예약 테스트")
	void reserveSeatUseCaseTest() {
		// when
		reserveSeatUseCase.execute("token", 1L);
		Seat seat = seatReader.getSeat(1L);

		// then
		assertThat(seat.getStatus()).isEqualTo(SeatStatus.HOLD);
	}

	@Test
	@DisplayName("좌석 예약 테스트 - 동시성 테스트")
	void reserveSeatUseCaseConcurrencyTest() throws InterruptedException {
		// given
		int numberOfThreads = 10;
		ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
		CountDownLatch countDownLatch = new CountDownLatch(numberOfThreads);

		// when
		for (int i = 0; i < numberOfThreads; i++) {
			executorService.execute(() -> {
				try {
					reserveSeatUseCase.execute("token", 1L);
				} finally {
					// 각 스레드가 자신의 작업을 완료했음을 표시하기 위해 호출됩니다.
					countDownLatch.countDown();
				}
			});
		}

		countDownLatch.await();
		executorService.shutdown();

		// then
		assertThat(reservationJpaRepository.findAll().size()).isEqualTo(1);
	}
}
