package com.kpop.ticketing.domain.show.component;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.kpop.ticketing.domain.common.exception.CustomException;
import com.kpop.ticketing.domain.common.exception.ErrorCode;
import com.kpop.ticketing.domain.show.repository.ShowRepository;
import com.kpop.ticketing.domain.show.model.Show;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShowReader {
	private final ShowRepository showRepository;

	public Show getShow(Long showId) {
		return showRepository.getShow(showId).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_SHOW));
	}

	public List<Show> getShows(Long concertId) {
		LocalDateTime now = LocalDateTime.now();
		return showRepository.getShows(concertId, now);
	}
}
