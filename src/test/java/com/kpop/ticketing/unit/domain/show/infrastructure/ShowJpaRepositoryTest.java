package com.kpop.ticketing.unit.domain.show.infrastructure;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import net.jqwik.api.Arbitraries;

import com.kpop.ticketing.domain.common.config.QueryDslConfig;
import com.kpop.ticketing.domain.concert.model.Concert;
import com.kpop.ticketing.domain.concert.infrastructure.ConcertJpaRepository;
import com.kpop.ticketing.domain.show.infrastructure.ShowJpaRepository;
import com.kpop.ticketing.domain.show.infrastructure.ShowJpaRepositoryCustomImpl;
import com.kpop.ticketing.domain.show.model.Show;
import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.api.introspector.FieldReflectionArbitraryIntrospector;
import com.navercorp.fixturemonkey.jakarta.validation.plugin.JakartaValidationPlugin;

@ActiveProfiles("test")
@DataJpaTest
@Import(QueryDslConfig.class)
class ShowJpaRepositoryTest {

	@Autowired
	private ShowJpaRepository showJpaRepository;

	@Autowired
	private ShowJpaRepositoryCustomImpl showJpaRepositoryCustomImpl;

	@Autowired
	private ConcertJpaRepository concertJpaRepository;

	private FixtureMonkey fixtureMonkey;

	private Long concertId;

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
		concertId = concert.getId();

		List<Show> shows = fixtureMonkey.giveMeBuilder(Show.class)
			.set("id", null)
			.set("concert", concert)
			.set("showTime", LocalDateTime.now().plusDays(1))
			.set("capacity", Arbitraries.integers().between(1, 100))
			.sampleList(10);
		showJpaRepository.saveAll(shows);
	}

	@Test
	@DisplayName("공연 조회 테스트")
	void getShowsTest() {
		// given
		// when
		List<Show> shows = showJpaRepositoryCustomImpl.getShows(concertId, LocalDateTime.now());
		// then
		assertNotNull(shows);
		assertFalse(shows.isEmpty());
		assertEquals(10, shows.size());
	}
}
