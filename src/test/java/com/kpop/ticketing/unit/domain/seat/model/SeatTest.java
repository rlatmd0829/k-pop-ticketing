package com.kpop.ticketing.unit.domain.seat.model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.kpop.ticketing.domain.seat.model.Seat;
import com.kpop.ticketing.domain.seat.model.SeatStatus;
import com.kpop.ticketing.domain.show.model.Show;

class SeatTest {

	@Test
	@DisplayName("좌석 만료 처리 테스트 - hold 시간이 지나지 않았을 경우")
	void resetSeatIfHoldTimeExceededTest() {
		Show show = mock(Show.class);
		Seat seat = Seat.create("10", 10000, show);
		seat.hold();
		seat.resetSeatIfHoldTimeExceeded();

		Assertions.assertEquals(SeatStatus.HOLD, seat.getStatus());
	}

	@Test
	@DisplayName("좌석 만료 처리 테스트 - hold 시간이 지났을 경우")
	void resetSeatIfHoldTimeExceededTest_whenHoldTimeIsOver() {
		Show show = mock(Show.class);
		Seat seat = Seat.create("10", 10000, show);
		seat.hold();
		seat.setHoldTime(seat.getHoldTime().minusMinutes(15));
		seat.resetSeatIfHoldTimeExceeded();

		assertEquals(SeatStatus.EMPTY, seat.getStatus());
	}

}
