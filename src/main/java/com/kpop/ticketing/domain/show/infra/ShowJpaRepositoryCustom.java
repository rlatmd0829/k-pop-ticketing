package com.kpop.ticketing.domain.show.infra;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.kpop.ticketing.domain.show.Show;

public interface ShowJpaRepositoryCustom {

	Optional<Show> getShow(Long showId);

	List<Show> getShows(Long concertId, LocalDateTime now);
}
