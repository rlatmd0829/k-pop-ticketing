package com.kpop.ticketing.integration.presentation.reservation.usecase;

import static com.mysema.commons.lang.Assert.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import com.kpop.ticketing.domain.seat.component.SeatReader;
import com.kpop.ticketing.domain.seat.model.Seat;
import com.kpop.ticketing.domain.seat.model.SeatStatus;
import com.kpop.ticketing.presentation.reservation.usecase.ReserveSeatUseCase;

import jakarta.transaction.Transactional;

@ActiveProfiles("test")
@SpringBootTest
@Sql(scripts = {
	"classpath:data/users.sql",
	"classpath:data/concerts.sql",
	"classpath:data/shows.sql",
	"classpath:data/seats.sql",
	"classpath:data/wait_tokens.sql"
})
@Transactional
class ReserveSeatUseCaseTest {
	@Autowired
	private ReserveSeatUseCase reserveSeatUseCase;

	@Autowired
	private SeatReader seatReader;

	@Test
	@DisplayName("좌석 예약 테스트")
	void reserveSeatUseCaseTest() {
		// when
		reserveSeatUseCase.execute("token", 1L);
		Seat seat = seatReader.getSeat(1L);

		// then
		assertThat(seat.getStatus()).isEqualTo(SeatStatus.HOLD);
	}
}
