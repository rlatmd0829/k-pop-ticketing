package com.kpop.ticketing.domain.seat;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
	@DisplayName("SeatReaderTest")
	void SeatReaderTest() {
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
