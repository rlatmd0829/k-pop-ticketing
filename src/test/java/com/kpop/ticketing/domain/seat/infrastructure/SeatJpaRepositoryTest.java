package com.kpop.ticketing.domain.seat.infrastructure;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import com.kpop.ticketing.domain.common.config.QueryDslConfig;
import com.kpop.ticketing.domain.concert.infrastructure.ConcertJpaRepository;
import com.kpop.ticketing.domain.concert.model.Concert;
import com.kpop.ticketing.domain.seat.model.SeatStatus;
import com.kpop.ticketing.domain.seat.model.Seat;
import com.kpop.ticketing.domain.show.infrastructure.ShowJpaRepository;
import com.kpop.ticketing.domain.show.model.Show;
import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.api.introspector.FieldReflectionArbitraryIntrospector;
import com.navercorp.fixturemonkey.jakarta.validation.plugin.JakartaValidationPlugin;

@ActiveProfiles("test")
@DataJpaTest
@Import(QueryDslConfig.class)
class SeatJpaRepositoryTest {

	@Autowired
	private SeatJpaRepository seatJpaRepository;

	@Autowired
	private ShowJpaRepository showJpaRepository;

	@Autowired
	private ConcertJpaRepository concertJpaRepository;

	@Autowired
	private SeatJpaRepositoryCustomImpl seatJpaRepositoryCustomImpl;

	private FixtureMonkey fixtureMonkey;

	private Long showId;

	@BeforeEach
	void setUp() {
		fixtureMonkey = FixtureMonkey.builder()
			.objectIntrospector(FieldReflectionArbitraryIntrospector.INSTANCE)
			.plugin(new JakartaValidationPlugin())
			.build();

		Concert concert = fixtureMonkey.giveMeBuilder(Concert.class)
			.set("id", null)
			.sample();

		concertJpaRepository.save(concert);

		Show show = fixtureMonkey.giveMeBuilder(Show.class)
			.set("id", null)
			.set("capacity", 30)
			.set("showTime", LocalDateTime.now().plusDays(1))
			.set("concert", concert)
			.sample();

		showJpaRepository.save(show);
		showId = show.getId();

		List<Seat> emptySeats = fixtureMonkey.giveMeBuilder(Seat.class)
			.set("id", null)
			.set("show", show)
			.set("status", SeatStatus.EMPTY)
			.sampleList(10);
		seatJpaRepository.saveAll(emptySeats);

		List<Seat> holdSeats = fixtureMonkey.giveMeBuilder(Seat.class)
			.set("id", null)
			.set("show", show)
			.set("status", SeatStatus.HOLD)
			.sampleList(10);
		seatJpaRepository.saveAll(holdSeats);
	}

	@Test
	@DisplayName("빈 좌석 조회 테스트")
	void getEmptySeatsTest() {
		// given

		// when
		List<Seat> seats = seatJpaRepositoryCustomImpl.getSeatsByShowIdAndStatus(showId, SeatStatus.EMPTY);

		// then
		assertNotNull(seats);
		assertFalse(seats.isEmpty());
		assertEquals(10, seats.size());
	}

	@Test
	@DisplayName("대기 좌석 조회 테스트")
	void getHoldSeatsTest() {
		// given

		// when
		List<Seat> seats = seatJpaRepositoryCustomImpl.getSeatsByShowIdAndStatus(showId, SeatStatus.HOLD);

		// then
		assertNotNull(seats);
		assertFalse(seats.isEmpty());
		assertEquals(10, seats.size());
	}
}
