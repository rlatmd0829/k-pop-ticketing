package com.kpop.ticketing.domain.show;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShowReader {
	private final ShowReaderRepository showReaderRepository;

	public Show getShow(Long showId) {
		return showReaderRepository.getShow(showId).get();
	}

	public List<Show> getShows(Long concertId) {
		LocalDateTime now = LocalDateTime.now();
		return showReaderRepository.getShows(concertId, now);
	}
}
