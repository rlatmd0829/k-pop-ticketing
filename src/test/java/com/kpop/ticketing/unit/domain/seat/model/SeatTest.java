package com.kpop.ticketing.unit.domain.seat.model;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.kpop.ticketing.domain.common.exception.CustomException;
import com.kpop.ticketing.domain.common.exception.ErrorCode;
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

	@Test
	@DisplayName("좌석 예약 처리 테스트")
	void reserveTest() {
		Show show = mock(Show.class);
		Seat seat = Seat.create("10", 10000, show);
		seat.hold();

		assertEquals(SeatStatus.HOLD, seat.getStatus());
	}

	@Test
	@DisplayName("좌석 상태 검사 테스트")
	void validateSeatStatusTest() {
		Show show = mock(Show.class);
		Seat seat = Seat.create("10", 10000, show);
		seat.hold();

		assertThatThrownBy(seat::validateSeatStatus)
			.isInstanceOf(CustomException.class)
			.hasMessage(ErrorCode.DUPLICATED_RESERVATION_SEAT.getMessage());
	}

}
