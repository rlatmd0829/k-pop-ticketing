package com.kpop.ticketing.integration.presentation.seat.usecase;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.kpop.ticketing.domain.concert.components.ConcertStore;
import com.kpop.ticketing.domain.concert.model.Concert;
import com.kpop.ticketing.domain.seat.component.SeatReader;
import com.kpop.ticketing.domain.seat.component.SeatStore;
import com.kpop.ticketing.domain.seat.model.Seat;
import com.kpop.ticketing.domain.show.components.ShowStore;
import com.kpop.ticketing.domain.show.model.Show;
import com.kpop.ticketing.presentation.seat.usecase.ScheduleSeatUseCase;

@ActiveProfiles("test")
@SpringBootTest
class ScheduleSeatUseCaseTest {

	@Autowired
	private ScheduleSeatUseCase scheduleSeatUseCase;

	@Autowired
	private SeatReader seatReader;

	@Autowired
	private SeatStore seatStore;

	@Autowired
	private ShowStore showStore;

	@Autowired
	private ConcertStore concertStore;

	@Test
	@DisplayName("좌석 만료 스케줄러 테스트")
	void setScheduleSeatUseCaseTest() {
		// given
		Concert concert = Concert.create("concert");
		concertStore.save(concert);
		Show show = Show.create(LocalDateTime.now(), 30, concert);
		showStore.save(show);

		for (int i = 0; i < 100; i++) {
			Seat seat = Seat.create("seat" + i, 10000, show);
			seat.hold();
			// 10개만 만료시간 지나게 설정
			if (i < 10) {
				seat.setHoldTime(seat.getHoldTime().minusMinutes(15));
			}
			seatStore.save(seat);
		}

		// when
		scheduleSeatUseCase.execute();
		List<Seat> holdSeats = seatReader.getHoldSeats();
		// then
		assertEquals(90, holdSeats.size());
	}
}
