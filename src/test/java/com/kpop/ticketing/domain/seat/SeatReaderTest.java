package com.kpop.ticketing.domain.seat;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.kpop.ticketing.domain.common.exception.CustomException;
import com.kpop.ticketing.domain.common.exception.ErrorCode;
import com.kpop.ticketing.domain.seat.component.SeatReader;
import com.kpop.ticketing.domain.seat.model.Seat;
import com.kpop.ticketing.domain.seat.repository.SeatReaderRepository;
import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.api.introspector.FieldReflectionArbitraryIntrospector;
import com.navercorp.fixturemonkey.jakarta.validation.plugin.JakartaValidationPlugin;

@ExtendWith(MockitoExtension.class)
class SeatReaderTest {
	@InjectMocks
	private SeatReader seatReader;

	@Mock
	private SeatReaderRepository seatReaderRepository;

	@Test
	@DisplayName("좌석 조회 테스트")
	void getSeatTest_success() {
		// given
		Long seatId = 1L;

		// when
		when(seatReaderRepository.getSeat(anyLong())).thenReturn(Optional.of(new Seat()));

		// then
		assertNotNull(seatReader.getSeat(seatId));
	}

	@Test
	@DisplayName("좌석 조회 실패 테스트 - 좌석이 없을 경우")
	void getSeatTest_fail_whenSeatNotExist() {
		// given
		Long seatId = 999L;

		// when
		when(seatReaderRepository.getSeat(anyLong())).thenReturn(Optional.empty());

		// then
		assertThatThrownBy(() -> seatReader.getSeat(seatId))
			.isInstanceOf(CustomException.class)
			.hasMessage(ErrorCode.NOT_FOUND_SEAT.getMessage());
	}

	@Test
	@DisplayName("예약 가능한 좌석 목록 조회 테스트")
	void getSeatsTest_success() {
	    // given
		FixtureMonkey fixtureMonkey = FixtureMonkey.builder()
			.objectIntrospector(FieldReflectionArbitraryIntrospector.INSTANCE)
			.plugin(new JakartaValidationPlugin())
			.build();

		List<Seat> seats = fixtureMonkey.giveMe(Seat.class, 3);

	    // when
	    when(seatReaderRepository.getSeats(anyLong())).thenReturn(seats);

	    // then
		assertNotNull(seatReader.getSeats(1L));

	}
}
