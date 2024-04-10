package com.kpop.ticketing.domain.reservation;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.kpop.ticketing.domain.reservation.component.ReservationWriter;
import com.kpop.ticketing.domain.reservation.repository.ReservationRepository;
import com.kpop.ticketing.domain.seat.model.Seat;
import com.kpop.ticketing.domain.user.model.User;
import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.api.introspector.FieldReflectionArbitraryIntrospector;
import com.navercorp.fixturemonkey.jakarta.validation.plugin.JakartaValidationPlugin;

@ExtendWith(MockitoExtension.class)
class ReservationWriterTest {
	@InjectMocks
	private ReservationWriter reservationWriter;

	@Mock
	private ReservationRepository reservationRepository;

	@Test
	@DisplayName("createTest")
	void create() {
		// given
		FixtureMonkey fixtureMonkey = FixtureMonkey.builder()
			.objectIntrospector(FieldReflectionArbitraryIntrospector.INSTANCE)
			.plugin(new JakartaValidationPlugin())
			.build();

		User user = fixtureMonkey.giveMeOne(User.class);
		Seat seat = fixtureMonkey.giveMeOne(Seat.class);

		// when
		reservationWriter.create(seat, user);

		// then
		verify(reservationRepository, times(1)).save(any());
	}

}
