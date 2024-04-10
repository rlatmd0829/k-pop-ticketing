package com.kpop.ticketing.domain.show.infra;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.time.temporal.TemporalAmount;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import net.jqwik.api.Arbitraries;

import com.kpop.ticketing.domain.common.config.QueryDslConfiguration;
import com.kpop.ticketing.domain.concert.Concert;
import com.kpop.ticketing.domain.concert.infra.ConcertJpaRepository;
import com.kpop.ticketing.domain.show.Show;
import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.api.introspector.FieldReflectionArbitraryIntrospector;
import com.navercorp.fixturemonkey.jakarta.validation.plugin.JakartaValidationPlugin;

@DataJpaTest
@Import(QueryDslConfiguration.class)
class ShowJpaRepositoryTest {

	@Autowired
	private ShowJpaRepository showJpaRepository;

	@Autowired
	private ShowJpaRepositoryCustomImpl showJpaRepositoryCustomImpl;

	@Autowired
	private ConcertJpaRepository concertJpaRepository;

	private FixtureMonkey fixtureMonkey;

	@BeforeEach
	void setUp() {
		fixtureMonkey = FixtureMonkey.builder()
			.objectIntrospector(FieldReflectionArbitraryIntrospector.INSTANCE)
			.plugin(new JakartaValidationPlugin())
			.build();

		Concert concert = mock(Concert.class);
		concertJpaRepository.save(concert);

		List<Show> shows = fixtureMonkey.giveMeBuilder(Show.class)
			.set("concert", concert)
			.set("showTime", LocalDateTime.now().plusDays(1))
			.set("capacity", Arbitraries.integers().between(1, 100))
			.sampleList(10);

		for (Show show : shows) {
			System.out.println("!!!!!!!!");
			System.out.println(show.getConcert().getId());
			System.out.println(show.getConcert().getName());
			System.out.println(show.getShowTime());
			System.out.println(show.getCapacity());
		}

		showJpaRepository.saveAll(shows);
	}

	@Test
	@DisplayName("공연 조회 테스트")
	void getShowsTest() {
		// given
		// when
		List<Show> shows = showJpaRepositoryCustomImpl.getShows(1L, LocalDateTime.now());
		// then
		assertNotNull(shows);
		assertFalse(shows.isEmpty());
		assertEquals(10, shows.size());
	}
}
