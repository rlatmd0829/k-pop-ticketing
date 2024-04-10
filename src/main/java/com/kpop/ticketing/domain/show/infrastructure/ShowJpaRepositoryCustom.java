package com.kpop.ticketing.domain.show.infrastructure;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.kpop.ticketing.domain.show.model.Show;

public interface ShowJpaRepositoryCustom {

	Optional<Show> getShow(Long showId);

	List<Show> getShows(Long concertId, LocalDateTime now);
}
