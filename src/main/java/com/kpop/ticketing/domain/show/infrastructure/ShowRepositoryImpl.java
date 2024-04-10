package com.kpop.ticketing.domain.show.infrastructure;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.kpop.ticketing.domain.show.model.Show;
import com.kpop.ticketing.domain.show.repository.ShowRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ShowRepositoryImpl implements ShowRepository {
	private final ShowJpaRepository showJpaRepository;

	@Override
	public Optional<Show> getShow(Long showId) {
		return showJpaRepository.getShow(showId);
	}

	@Override
	public List<Show> getShows(Long concertId, LocalDateTime showTime) {
		return showJpaRepository.getShows(concertId, showTime);
	}
}
