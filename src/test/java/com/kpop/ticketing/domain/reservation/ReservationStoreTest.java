package com.kpop.ticketing.domain.reservation;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import com.kpop.ticketing.domain.reservation.components.ReservationStore;
import com.kpop.ticketing.domain.reservation.model.Reservation;
import com.kpop.ticketing.domain.reservation.repository.ReservationStoreRepository;
import com.kpop.ticketing.domain.seat.model.Seat;
import com.kpop.ticketing.domain.user.model.User;
import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.api.introspector.FieldReflectionArbitraryIntrospector;
import com.navercorp.fixturemonkey.jakarta.validation.plugin.JakartaValidationPlugin;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class ReservationStoreTest {
	@InjectMocks
	private ReservationStore reservationStore;

	@Mock
	private ReservationStoreRepository reservationStoreRepository;

	@Test
	@DisplayName("예약 생성 테스트")
	void storeReservationTest() {
		// given
		FixtureMonkey fixtureMonkey = FixtureMonkey.builder()
			.objectIntrospector(FieldReflectionArbitraryIntrospector.INSTANCE)
			.plugin(new JakartaValidationPlugin())
			.build();

		Reservation reservation = fixtureMonkey.giveMeOne(Reservation.class);
		when(reservationStoreRepository.save(reservation)).thenReturn(reservation);
		// when
		reservationStore.store(reservation);

		// then
		verify(reservationStoreRepository, times(1)).save(any());
	}

}
