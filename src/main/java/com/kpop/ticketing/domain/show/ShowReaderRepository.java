package com.kpop.ticketing.domain.show;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ShowReaderRepository {
	Optional<Show> getShow(Long showId);
	List<Show> getShows(Long showId, LocalDateTime showTime);
}
