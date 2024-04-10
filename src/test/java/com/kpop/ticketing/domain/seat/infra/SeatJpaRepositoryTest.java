package com.kpop.ticketing.domain.seat.infra;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import com.kpop.ticketing.domain.common.config.QueryDslConfig;
import com.kpop.ticketing.domain.concert.Concert;
import com.kpop.ticketing.domain.concert.infra.ConcertJpaRepository;
import com.kpop.ticketing.domain.seat.Seat;
import com.kpop.ticketing.domain.seat.enumclass.SeatStatus;
import com.kpop.ticketing.domain.show.Show;
import com.kpop.ticketing.domain.show.infra.ShowJpaRepository;
import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.api.introspector.FieldReflectionArbitraryIntrospector;
import com.navercorp.fixturemonkey.jakarta.validation.plugin.JakartaValidationPlugin;

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

	@BeforeEach
	void setUp() {
		fixtureMonkey = FixtureMonkey.builder()
			.objectIntrospector(FieldReflectionArbitraryIntrospector.INSTANCE)
			.plugin(new JakartaValidationPlugin())
			.build();

		Concert concert = fixtureMonkey.giveMeBuilder(Concert.class)
			.set("id", 1L)
			.sample();

		concertJpaRepository.save(concert);

		Show show = fixtureMonkey.giveMeBuilder(Show.class)
			.set("id", 1L)
			.set("capacity", 30)
			.set("showTime", LocalDateTime.now())
			.set("concert", concert)
			.sample();

		showJpaRepository.save(show);

		List<Seat> seats = fixtureMonkey.giveMeBuilder(Seat.class)
			.set("show", show)
			.set("status", SeatStatus.EMPTY)
			.sampleList(10);
		seatJpaRepository.saveAll(seats);
	}

	@Test
	@DisplayName("좌석 저장 테스트")
	void createSeatTest() {
		Seat seat = fixtureMonkey.giveMeOne(Seat.class);

		Seat savedSeat = seatJpaRepository.save(seat);

		// then
		assertNotNull(savedSeat);
	}

	@Test
	@DisplayName("좌석 조회 테스트")
	void getSeatsTest() {
		// given

		// when
		List<Seat> seats = seatJpaRepositoryCustomImpl.getSeats(1L);

		// then
		assertNotNull(seats);
		assertFalse(seats.isEmpty());
		assertEquals(10, seats.size());
	}

}
