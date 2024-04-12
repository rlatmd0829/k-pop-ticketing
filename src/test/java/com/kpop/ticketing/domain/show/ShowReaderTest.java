package com.kpop.ticketing.domain.show;

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

import com.kpop.ticketing.domain.show.components.ShowReader;
import com.kpop.ticketing.domain.show.model.Show;
import com.kpop.ticketing.domain.show.repository.ShowReaderRepository;
import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.api.introspector.FieldReflectionArbitraryIntrospector;
import com.navercorp.fixturemonkey.jakarta.validation.plugin.JakartaValidationPlugin;

@ExtendWith(MockitoExtension.class)
class ShowReaderTest {
	@InjectMocks
	private ShowReader showReader;

	@Mock
	private ShowReaderRepository showReaderRepository;

	@Test
	@DisplayName("공연 조회 테스트")
	void getShowTest() {
		// given
		Long showId = 1L;

		// when
		when(showReaderRepository.getShow(anyLong())).thenReturn(Optional.of(new Show()));

		// then
		assertNotNull(showReader.getShow(showId));
	}

	@Test
	@DisplayName("현재 날짜보다 이후인 공연 목록 조회 테스트")
	void getShowsTest() {
		// given
		FixtureMonkey fixtureMonkey = FixtureMonkey.builder()
			.objectIntrospector(FieldReflectionArbitraryIntrospector.INSTANCE)
			.plugin(new JakartaValidationPlugin())
			.build();

		List<Show> shows = fixtureMonkey.giveMe(Show.class, 3);

		// when
		when(showReaderRepository.getShows(anyLong(), any())).thenReturn(shows);

		// then
		assertNotNull(showReader.getShows(1L));
	}

}
