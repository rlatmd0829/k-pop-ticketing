package com.kpop.ticketing.domain.show.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.kpop.ticketing.domain.show.model.Show;

public interface ShowReaderRepository {
	Optional<Show> getShow(Long showId);
	List<Show> getShows(Long showId, LocalDateTime showTime);
}
